package com.app.controller.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.like.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.service.review.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public String reviewWrite() {
		
		return "reviewWrite";
	}
	
	@PostMapping("/reviewWrite") //글 작성버튼 클릭
	public String reviewWrite_process(@ModelAttribute ReviewDto reviewDto) {
		
		reviewService.createReview(reviewDto);
		
		return "redirect:/reviewBbs";
	}
	
	@GetMapping("/reviewView") //글상세 페이지 요청 (글제목 클릭)
	public String reviewView(Model model, @RequestParam int reviewId) {
		
		reviewService.increaseViews(reviewId); //조회수 증가
		
		ReviewDto item = reviewService.findReview(reviewId);
		model.addAttribute("item", item);
		
		return "reviewView";
	}
	
	@PostMapping("/reviewView") //추천 버튼 클릭
	@ResponseBody //RestController 처럼 단순 string 텍스트를 반환하는 형태로 수행
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
}
