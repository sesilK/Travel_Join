package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CommentDto;
import com.app.dto.JoinDto;
import com.app.dto.MarkDto;
import com.app.dto.ReviewDto;
import com.app.dto.ReviewImgDto;
import com.app.service.review.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/review") //글목록 페이지 요청
	public String reviewBbs() {
		
		return "reviewBbs";
	}
	
	@PostMapping("/reviewTotalCount") //글목록 총 데이터 개수 요청
	@ResponseBody
	public int reviewTotalCount(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
	
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);

		String searchType = reviewDto.getDestination();
	    String searchCondition = reviewDto.getPlanInfo();
	    String keyword = reviewDto.getContent();
	    
	    if(reviewDto.getContent() == null || reviewDto.getContent().equals("")) {
	    	keyword ="";
	    }
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchCondition", searchCondition);
		map.put("keyword", keyword);
		
		int reviewCount = reviewService.findReviewCount(map);
				
		return reviewCount;
	}
	
	@PostMapping("/review") //글목록 데이터 요청
	@ResponseBody
	public List<ReviewDto> reviewBbs_process(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);

		String searchType = reviewDto.getDestination();
	    String searchCondition = reviewDto.getPlanInfo();
	    String keyword = reviewDto.getContent();
	    String page = reviewDto.getPage();
	    String dataPerPage = reviewDto.getNick();
	    
		if (keyword == null /* || keyword.equals("") */) {
	    	keyword ="";
	    }
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchCondition", searchCondition);
		map.put("keyword", keyword);
		map.put("page", page);
		map.put("dataPerPage", dataPerPage);
		
		List<ReviewDto> reviewList = reviewService.findReviewList(map);
		
		return reviewList;
	}
	
	@GetMapping("/reviewWrite")	//글작성 페이지 요청
	public String reviewWrite(Model model, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("userId");
		
		List<JoinDto> joinList = reviewService.findJoinList(sessionId);	//여행목록 불러오기
		model.addAttribute("joinList", joinList);
		
		return "reviewWrite";
	}
	
	@GetMapping("/reviewWriteTemp")	//글작성 페이지 임시저장 불러오기
	@ResponseBody
	public ReviewDto reviewWriteTemp(HttpSession session) {
		
		String sessionId = (String) session.getAttribute("userId");
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(sessionId); //임시저장된 글 반환

		return temp;
	}
	
	@PostMapping("/reviewWrite") //글 작성 버튼 클릭시 폼 전송 과정
	@ResponseBody
	public String reviewWrite_process(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		
		String sessionId = (String) session.getAttribute("userId");
		
		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		
		reviewDto.setUserId(sessionId);
		reviewDto.setDeleteAt("N");
		
		int result = reviewService.createReview(reviewDto); //글 등록
		int reviewId = reviewService.returnReviewId(reviewDto); //글 번호
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(sessionId); //임시저장된 글이 있는지 확인
		if(result == 1 && temp != null) { //글 등록 성공했을 때 임시저장된 글이 남아있으면,
			reviewService.removeTemporaryReview(sessionId); //임시저장된 글 삭제
		}
		List<String> imageFileNameList = reviewDto.getImageFileNameList(); //이미지파일명 리스트
		
		ReviewImgDto reviewImgDto = new ReviewImgDto();	//이미지정보 객체 생성
		reviewImgDto.setReviewId(reviewId); //글번호 set
		
		for(int i=0; i<imageFileNameList.size(); i++) {
			reviewImgDto.setFileName(imageFileNameList.get(i)); //파일명 set
			reviewService.uploadReviewImage(reviewImgDto); //이미지파일명 저장
		}
		
		return ""+reviewId;	//글번호 전달
	}
	
	@PostMapping("/temporarySave") //글 임시저장
	@ResponseBody
	public String temporarySave(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		
		String sessionId = (String) session.getAttribute("userId");
		
		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		reviewDto.setUserId(sessionId);

		int result = 0;
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(sessionId); //임시저장된 글이 있는지 확인
		if(temp != null) { //있으면
			result = reviewService.modifyTemporaryReview(reviewDto); //임시저장된 내용을 변경
		} else if (temp == null) { //없으면
			result = reviewService.saveTemporaryReview(reviewDto); //작성된 것을 임시저장
		}
		
		if(result == 1) { //임시저장 성공
			return "true";
		}
		return "false"; //임시저장 실패
	}
	
	@GetMapping("/reviewView") //글상세 페이지 요청
	public String reviewView(Model model, @RequestParam int reviewId, HttpSession session) throws JsonProcessingException {
		
		ReviewDto reviewDto = new ReviewDto();
		String sessionId = (String) session.getAttribute("userId");
		
		reviewDto.setReviewId(reviewId);
		reviewDto.setUserId(sessionId); //조회수 증가 확인용
		
		reviewService.increaseViews(reviewDto); //조회수 증가
		
		ReviewDto item = reviewService.findReview(reviewId); //해당글 불러오기
		
		if(!(item.getDeleteAt().equals("Y")) && item.getReportCount() <= 5 ) { //삭제나 신고누적 해당 X
			List<CommentDto> commentList = reviewService.findCommentList(reviewId); //댓글목록 불러오기
			model.addAttribute("commentList", commentList);
			model.addAttribute("item", item);			
			
			return "reviewView";
		} else {
			return "redirect:/notExist";
		}	
	}
	
	@GetMapping("/reviewModify") //글수정 페이지 요청
	public String reviewModify(Model model, @RequestParam int reviewId, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("userId");
		
		ReviewDto item = reviewService.findReview(reviewId); //수정할 글 찾기
		String userId = item.getUserId();
		int planId = item.getPlanId();
		
		JoinDto beforeJoin = reviewService.findJoinInfo(planId);//수정전 여행 정보

		if(sessionId.equals(userId) &&  //글 작성자id 로그인id 일치
			!(item.getDeleteAt().equals("Y")) && item.getReportCount() <= 5) { //삭제나 신고누적 해당 X
			List<JoinDto> joinList = reviewService.findJoinList(sessionId);	//여행목록 불러오기
			joinList.add(beforeJoin); //여행목록에 수정전 여행도 추가
			model.addAttribute("joinList", joinList);
			model.addAttribute("reviewId", reviewId);
			return "reviewModify";
		} else {
			return "redirect:/notExist";
		}
	}
	
	@PostMapping("/reviewLoad") //글수정 페이지 요청
	@ResponseBody
	public ReviewDto reviewLoad(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		ReviewDto item = reviewService.findReview(reviewDto.getReviewId()); //수정할 글 찾기
		
		return item;
	}
	
	@PostMapping("/reviewModify") //글 수정 버튼 클릭시 폼 전송 과정
	@ResponseBody
	public String reviewModify_process(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		int reviewId = reviewDto.getReviewId(); //글 번호
		
		String sessionId = (String) session.getAttribute("userId");
		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		
		reviewService.modifyReview(reviewDto); //글 수정
		reviewService.removeReviewImage(reviewId); //이미지파일명 삭제
		List<String> imageFileNameList = reviewDto.getImageFileNameList(); //이미지파일명 리스트
		ReviewImgDto reviewImgDto = new ReviewImgDto();	//이미지정보 객체 생성
		reviewImgDto.setReviewId(reviewId); //글번호 set
		for(int i=0; i<imageFileNameList.size(); i++) {
			reviewImgDto.setFileName(imageFileNameList.get(i)); //파일명 set
			reviewService.uploadReviewImage(reviewImgDto); //이미지파일명 저장
		}
				
		return ""+reviewId;	//글번호 전달
	}
	
	@GetMapping("/notExist") //존재하지 않거나 삭제/신고된 글 또는 수정권한 없는 페이지 접근 시
	public String notExist() {
		return "notExist";
	}
	
	@PostMapping("/reviewViewTitle") //글 확인 (글제목 클릭)
	@ResponseBody
	public String reviewView_process(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		
		ReviewDto item = reviewService.findReview(reviewDto.getReviewId());

		if(item == null) {	//존재하지 않는 글
			return "not";
		} else if(item.getDeleteAt().equals("Y")) {	//삭제된 글
			return "deleted";
		} else if(item.getReportCount() > 5) {	//신고 누적
			return "reported";
		} else {
			return "view";
		}
	}
	
	@PostMapping("/reviewMark") //추천/신고 버튼 클릭
	@ResponseBody
	public int reviewLike(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		MarkDto markDto = objectMapper.readValue(requestBody, MarkDto.class);
		
		String sessionId = (String) session.getAttribute("userId");
		if(sessionId == null) {	//로그인 안 되어있으면 idNull
			System.out.println("sessionId Null");
			return -2;
		}
		
		MarkDto isNull =	//추천/신고 여부 확인 
				reviewService.CheckReviewMark(markDto.getReviewId(), markDto.getUserId(), markDto.getSort());
		
		if (isNull == null) { //추천/신고한 적 없으면
			int count = reviewService.reviewMark(markDto.getReviewId(), markDto.getUserId(), markDto.getSort()); //추천하기
			return count; //추천/신고 성공 (추천/신고 횟수 반환)
		} else {
			return -1;	//추천/신고 실패
		}
	}
	
	@PostMapping("/deleteReview") //글 삭제
	@ResponseBody
	public String deleteReview(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		String sessionId = (String) session.getAttribute("userId");

		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		int reviewId = reviewDto.getReviewId();
		String userId = reviewDto.getUserId();
		
		if(sessionId.equals(userId)) { //글 작성자id 로그인id 일치
			reviewService.blindReview(reviewId); //글 삭제처리 (안보이게)
			return "true";	//성공
		} else {
			return "false";	//실패
		}
	}
	
	@PostMapping("/comment") //댓글 등록
	@ResponseBody
	public List<CommentDto> comment(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CommentDto commentDto = objectMapper.readValue(requestBody, CommentDto.class);
		
		String sessionId = (String) session.getAttribute("userId");

		if(sessionId == null) {	//로그인 안 되어있으면 idNull
			System.out.println("sessionId Null");
			return null;
		}
		commentDto.setUserId(sessionId);
		int reviewId = commentDto.getReviewId();

		reviewService.createComment(commentDto); //댓글 등록
		
		List<CommentDto> commentList = reviewService.findCommentList(reviewId); //댓글목록 불러오기

		return commentList;	//성공
	}
	
	@PostMapping("/updateComment") //댓글 수정
	@ResponseBody
	public List<CommentDto> updateComment(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CommentDto commentDto = objectMapper.readValue(requestBody, CommentDto.class);
		
		String sessionId = (String) session.getAttribute("userId");
		if(sessionId == null) {	//로그인 안 되어있으면 idNull
			System.out.println("sessionId Null");
			return null;
		}
		
		reviewService.modifyComment(commentDto); //댓글 수정
		
		int reviewId = commentDto.getReviewId();
		List<CommentDto> commentList = reviewService.findCommentList(reviewId); //댓글목록 불러오기

		return commentList;	//성공
	}
	
	@PostMapping("/deleteComment") //댓글 삭제
	@ResponseBody
	public Map<String, List<CommentDto>> deleteComment(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		CommentDto commentDto = objectMapper.readValue(requestBody, CommentDto.class);
		Map<String, List<CommentDto>> map = new HashMap<String, List<CommentDto>>();
		String key = null; 
		
		String sessionId = (String) session.getAttribute("userId");
		int commentId = commentDto.getCommentId();
		int reviewId = commentDto.getReviewId();
		String userId = commentDto.getUserId();

		if(sessionId == null) {	//로그인 안 되어있으면 idNull
			System.out.println("sessionId Null");
			key = "idNull";
		} else if(sessionId.equals(userId)) { //로그인된 아이디가 댓글작성자와 같으면
			reviewService.blindComment(commentId, reviewId); //댓글 삭제
			key = "true";
		} else {
			key = "false";
		}

		List<CommentDto> commentList = reviewService.findCommentList(reviewId); //댓글목록 불러오기
		map.put(key, commentList);

		return map;	//성공
	}
	
	//리뷰 작성시 첨부한 이미지 파일을 경로에 저장하기
	@PostMapping(value="/uploadReviewImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadReviewImageFile(@RequestParam("file") MultipartFile multipartFile, 
											HttpServletRequest request, HttpSession session)  {
		
		
		String sessionId = (String) session.getAttribute("userId");
		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		
		JsonObject jsonObject = new JsonObject();
		
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//String fileRoot = contextRoot+"resources\\image\\review\\"; // 내부경로 저장
		String fileRoot = "C:\\plan_garlic\\images\\review\\"; //외부경로 저장
		
		System.out.println(fileRoot); // -> server.xml에 저장경로 추가해야함
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		//String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + "_" + originalFileName/* + extension */;	//저장될 파일명
		
		File targetFile = new File(fileRoot + savedFileName);
		File parentDir = targetFile.getParentFile(); // 부모 디렉토리 경로 추출

		if (!parentDir.exists()) { // 부모 디렉토리가 존재하지 않는 경우 디렉토리 생성
		    boolean created = parentDir.mkdirs();
		    if (!created) {
		        jsonObject.addProperty("responseCode", "error");
		        jsonObject.addProperty("errorMessage", "디렉토리 생성 실패");
		        return jsonObject.toString();
		    }
		}
		if (!targetFile.exists()) {
		    try {
		        targetFile.createNewFile();
		    } catch (IOException e) {
		        jsonObject.addProperty("responseCode", "error");
		        jsonObject.addProperty("errorMessage", "파일 생성 실패");
		        e.printStackTrace();
		        return jsonObject.toString();
		    }
		}
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/images/review/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}

	@PostMapping(value = "/deleteReviewImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteReviewImageFile(@RequestParam("file") String fileName, 
										  HttpServletRequest request, HttpSession session) {
		
		String sessionId = (String) session.getAttribute("userId");
		if(sessionId == null) {	//로그인 안 되어있으면
			System.out.println("sessionId Null");
			return "idNull";
		}
		
	    // 폴더 위치
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//String fileRoot = contextRoot+"resources\\image\\review\\"; //내부 경로
		String fileRoot = "C:\\plan_garlic\\images\\review\\"; //외부 경로
	    
	    // 해당 파일 삭제
	    Path path = Paths.get(fileRoot, fileName);
	    try {
	        Files.delete(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "true";
	}

	
}
