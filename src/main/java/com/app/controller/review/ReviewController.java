package com.app.controller.review;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.review.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.dto.review.ReviewImgDto;
import com.app.service.review.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/reviewBbs") //글목록 페이지 요청
	public String ViewAndSearchReviewBbs(Model model, @RequestParam Map<String, String> map) {
		
		List<ReviewDto> reviewList = reviewService.findReviewList(map);
		model.addAttribute("reviewList", reviewList);
		
		return "reviewBbs";
	}
	
	@GetMapping("/reviewWrite")	//글작성 페이지 요청
	public String reviewWrite(Model model) {
		
		String userId = "admin";  //   <- id 세션에서 가져오게 수정
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(userId); //임시저장된 글이 있는지 확인
		if(temp != null) { //있으면
			model.addAttribute("temp", temp); //model에 담아서 전달
		} else { //없으면
			ReviewDto nullDto = new ReviewDto(); //null 전달
			nullDto.setTitle(null);
			nullDto.setContent(null);
			model.addAttribute("temp", nullDto);
		}
		
		return "reviewWrite";
	}
	
	@PostMapping("/reviewWrite") //글작성 폼 전송 완료 후 페이지 이동
	public String reviewWrite_(@ModelAttribute ReviewDto reviewDto) {
		
		String userId = "admin";  //   <- id 세션에서 가져오게 수정
		reviewDto.setUserId(userId);
		reviewDto.setDeleteAt("N");
		ReviewDto resultDto = reviewService.returnReview(reviewDto); //글 반환
		System.out.println(resultDto);
		int reviewId = resultDto.getReviewId(); //글 번호
		
		return "redirect:/reviewView?reviewId=" + reviewId;
	}
	
	@PostMapping("/reviewWrite_process") //글 작성 버튼 클릭시 폼 전송 과정
	@ResponseBody
	public String reviewWrite_process(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		
		String userId = "admin";  //   <- id 세션에서 가져오게 수정
		reviewDto.setUserId(userId);
		
		int result = reviewService.createReview(reviewDto); //글 등록
		ReviewDto resultDto = reviewService.returnReview(reviewDto); //글 반환
		int reviewId = resultDto.getReviewId(); //글 번호
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(userId); //임시저장된 글이 있는지 확인
		if(result == 1 && temp != null) { //글 등록 성공했을 때 임시저장된 글이 남아있으면,
			reviewService.removeTemporaryReview(userId); //임시저장된 글 삭제
		}
		List<String> imageFileNameList = reviewDto.getImageFileNameList(); //이미지파일명 리스트
		
		ReviewImgDto reviewImgDto = new ReviewImgDto();	//이미지정보 객체 생성
		reviewImgDto.setReviewId(reviewId); //글번호 set
		
		for(int i=0; i<imageFileNameList.size(); i++) {
			reviewImgDto.setFileName(imageFileNameList.get(i)); //파일명 set
			reviewService.uploadReviewImage(reviewImgDto); //이미지파일명 저장
		}
		String reviewIdStr = String.valueOf(reviewId);
		System.out.println(reviewIdStr);
		
		return "/reviewView?reviewId=" + reviewIdStr; //이동할 url 전달 (글 상세페이지)
	}
	
	@PostMapping("/temporarySave") //글 임시저장
	@ResponseBody
	public String temporarySave(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ReviewDto reviewDto = objectMapper.readValue(requestBody, ReviewDto.class);
		
		String userId = "admin";  //   <- id 세션에서 가져오게 수정
		reviewDto.setUserId(userId);

		int result = 0;
		
		ReviewDto temp = reviewService.CheckIfTemporarySaved(userId); //임시저장된 글이 있는지 확인
		if(temp != null) { //있으면
			result = reviewService.modifyTemporaryReview(reviewDto); //임시저장된 내용을 변경
		} else if (temp == null) { //없으면
			result = reviewService.saveTemporaryReview(reviewDto); //작성된 것을 임시저장
		}
		
		if(result == 1) { //임시저장 성공
			return "true";
		}
		return "false";
	}
	
	@GetMapping("/reviewView") //글상세 페이지 요청 (글제목 클릭)
	public String reviewView(Model model, @RequestParam int reviewId) {
		
		ReviewDto reviewDto = new ReviewDto();
		String userId = "admin";  //   <- id 세션에서 가져오게 수정
		
		reviewDto.setReviewId(reviewId);
		reviewDto.setUserId(userId);
		
		reviewService.increaseViews(reviewDto); //조회수 증가
		
		ReviewDto item = reviewService.findReview(reviewId);
		model.addAttribute("item", item);
		
		
		return "reviewView";
	}
	
	@PostMapping("/reviewView") //추천 버튼 클릭
	@ResponseBody
	public String reviewLike_process(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		LikeDto likeDto = objectMapper.readValue(requestBody, LikeDto.class);
		
		
		LikeDto isNull =	//추천 여부 확인 
				reviewService.CheckIfRecommended(likeDto.getReviewId(), likeDto.getUserId());
		
		if (isNull == null) { //추천한 적 없으면
			reviewService.reviewRecommend(likeDto.getReviewId(), likeDto.getUserId()); //추천하기
			return "true";	//추천 성공
		} else {
			return "false";	//추천 실패
		}			
	}
	
	/*
	 * @PostMapping("/reviewView") //댓글 달기 public String reviewView_process(Model
	 * model, @RequestParam int reviewId) {
	 * 
	 * ReviewDto item = reviewService.findReview(reviewId);
	 * model.addAttribute("item", item);
	 * 
	 * return "reviewView"; }
	 */
	
	//리뷰 작성시 첨부한 이미지 파일을 경로에 저장하기
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, 
											HttpServletRequest request)  {
		JsonObject jsonObject = new JsonObject();
		
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//String fileRoot = contextRoot+"resources/fileupload/"; // 내부경로 저장을 희망할때.
		String fileRoot = "C:\\summernote_image\\"; //외부경로로 저장을 희망할때.
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		//String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + "_" + originalFileName/* + extension */;	//저장될 파일명
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}

	@RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public void deleteSummernoteImageFile(@RequestParam("file") String fileName) {
	    // 폴더 위치
	    //String filePath = realPath + "/upload_image/image/fileupload/tmp/";
		String fileRoot = "C:\\summernote_image\\";
	    
	    // 해당 파일 삭제
	    deleteFile(fileRoot, fileName);
	}

	// 파일 삭제
	private void deleteFile(String fileRoot, String fileName) {
	    Path path = Paths.get(fileRoot, fileName);
	    try {
	        Files.delete(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
