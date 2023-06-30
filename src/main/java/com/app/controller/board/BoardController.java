package com.app.controller.board;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.dto.board.BoardDto;
import com.app.dto.join.JoinDto;
import com.app.dto.party.PartyDto;
import com.app.service.board.BoardService;
import com.app.service.party.PartyService;
import com.app.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;	
	@Autowired
	PartyService partyService;
	@Autowired
	UserService userService;


	@GetMapping("/detail") // 글상세 페이지 요청 
	public String detail(@RequestParam("planId") int planId, 
							Model model) {
		
//		String nowId = request.getSession().getId(); // 세션 ID 값으로 설정
//		String nowId = "ab"; // 테스트용 		
		
		boardService.plusView(planId);
		BoardDto item = boardService.findPostById(planId);		
		model.addAttribute("item", item);
		
		List<PartyDto> list = partyService.myTeamDetail(planId);
		model.addAttribute("CurrPersonnel", list.size());

		
		return "post_detail";
	}

	  @PostMapping("/joinParty") // 동행신청하기 	plan_id 에 user_id insert\
	  @ResponseBody
	    public String joinParty(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {
        
		  ObjectMapper objectMapper = new ObjectMapper();
		  PartyDto partyDto = objectMapper.readValue(requestBody, PartyDto.class);
			
	      //아이디는 세션에서 받아오고
		  String userId = (String) session.getAttribute("userId");
		  
		  partyDto.setUserId(userId); //dto에 세션id 세팅
		  partyDto.setUserId("admin"); //dto에 세션id 세팅
		  int planId = partyDto.getPlanId(); //게시글 번호
		  
		  int result = partyService.checkStatus(partyDto); //여행 참가여부 조회		  
		  System.out.println(result);
		  
		  if(result == 0) {
			  partyService.joinParty(partyDto); //party 테이블에 inserst
			  partyService.addMember(planId); //join 테이블에 update
			  return "true";
		  } else {
			  return "false";
		  }
		  
	    }
	    
		  @PostMapping("/joinDead") // 동행신청하기 	plan_id 에 user_id insert\
		  @ResponseBody
		    public String joinDead(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
	        
			  ObjectMapper objectMapper = new ObjectMapper();
			  PartyDto partyDto = objectMapper.readValue(requestBody, PartyDto.class);
			  
			  int result = partyService.joinDead(partyDto); //여행 모집 마감		  
			  
			  if(result == 1) { //마감성공
				  return "true";
			  } else {			//마감실패
				  return "false";
			  }
			  
		    }
		  
		  @PostMapping("/joinDelete") // 동행신청하기 	plan_id 에 user_id insert\
		  @ResponseBody
		    public String joinDelete(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
			  System.out.println("joinDelete 버튼 눌림");
			  ObjectMapper objectMapper = new ObjectMapper();
			  PartyDto partyDto = objectMapper.readValue(requestBody, PartyDto.class);
			  
			  int result = partyService.joinDelete(partyDto); //여행 모집 마감		  
			  
			  if(result == 1) { //삭제성공
				  return "true";
			  } else {			//삭제실패
				  return "false";
			  }
			  
		    }
/*
 * @RequestMapping("/joinParty_process") // 동행신청 프로세스 public String
 * joinPartyProcess(@RequestBody PartyDto partyDto, Model model) throws
 * JsonMappingException, JsonProcessingException {
 * 
 * // 필요한 데이터 추출 int planId = partyDto.getPlanId(); String userId =
 * partyDto.getUserId();
 * 
 * // PartyDto에 데이터 설정 partyDto.setPlanId(planId); partyDto.setUserId(userId);
 * 
 * // PartyService를 사용하여 동행 신청 처리 partyService.joinParty(partyDto);
 * 
 * // 모델에 필요한 데이터 추가 model.addAttribute("planId", planId);
 * 
 * return "redirect:/detail"; }
 */
	
	
/*
 * @GetMapping("/partyMembers") //plan_id에 동행신청한 user_id 조회 public String
 * partyMembers(@RequestParam("planId") int planId, Model model) {
 * List<PartyDto> partyMembers = boardService.getPartyMembersByPlanId(planId);
 * model.addAttribute("partyMembers", partyMembers); return "post_detail"; }
 */
	  
	
	
	
	
	
	



}