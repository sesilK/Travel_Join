package com.app.controller.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.like.LikeDto;
import com.app.dto.review.ReviewDto;
import com.app.service.review.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/reviewBbs") //글목록 페이지 (게시판)
	public String ViewAndSearchReviewBbs(Model model, @RequestParam Map<String, String> map) {
		
		List<ReviewDto> reviewList = reviewService.findReviewList(map);
		model.addAttribute("reviewList", reviewList);
		
		return "reviewBbs";
	}
	
	@GetMapping("/reviewWrite")	//글작성 페이지
	public String reviewWrite() {
		
		return "reviewWrite";
	}
	
	@PostMapping("/reviewWrite") //글 작성버튼 클릭
	public String reviewWrite_process(@ModelAttribute ReviewDto reviewDto) {
		
		reviewService.createReview(reviewDto);
		
		return "redirect:/reviewBbs";
	}
	
	@GetMapping("/reviewView") //글제목 클릭 -> 글상세 페이지
	public String reviewView(Model model, @RequestParam int reviewId) {
		
		reviewService.increaseViews(reviewId); //조회수 증가
		
		String userId = "admin"; // 아이디 정보 세션에서 가져오게 수정
		LikeDto likeDto = reviewService.CheckIfRecommended(reviewId, userId);	//추천여부 확인
		
	    if (likeDto != null) {
	        model.addAttribute("alertMessage", "이미 추천한 리뷰입니다.");
	    } else if (likeDto == null) {
	        model.addAttribute("alertMessage", "추천하였습니다.");
	    }
		
		ReviewDto item = reviewService.findReview(reviewId);
		model.addAttribute("item", item);
		
		return "reviewView";
	}
	
	@PostMapping("/reviewView") //추천 버튼 누르기
	public String reviewLike_process(Model model, @RequestParam int reviewId) {

		String userId = "admin"; // 아이디 정보 세션에서 가져오게 수정
		LikeDto likeDto = reviewService.CheckIfRecommended(reviewId, userId);	//추천여부 확인
		
		if (likeDto == null) {
			reviewService.reviewRecommend(reviewId, userId);	//추천하기
			model.addAttribute("alertMessage", "이미 추천한 리뷰입니다.");
		}
		
		ReviewDto item = reviewService.findReview(reviewId);
		model.addAttribute("item", item);
		
		return "reviewView";				
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
