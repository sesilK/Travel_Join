package com.app.controller.board;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;
import com.app.service.board.BoardService;
import com.app.service.party.PartyService;
import com.app.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
							Model model	) {
		
//		String nowId = request.getSession().getId(); // 세션 ID 값으로 설정
//		String nowId = "ab"; // 테스트용 		
		
		boardService.plusView(planId);
		BoardDto item = boardService.findPostById(planId);		
		model.addAttribute("item", item);		

		//조회수 올리기 
		
		return "post_detail";
	}
		

	  @PostMapping("/joinParty") // 동행신청하기 	plan_id 에 user_id insert  
	    public String joinParty(@ModelAttribute BoardDto boardDto,
	                            Model model) {
        
	        
	        model.addAttribute("planId", boardDto.getPlanId());        
	        partyService.joinParty(boardDto);
       
	        return "redirect:/party/post_detail?planId=" + boardDto.getPlanId();
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
	
	
	  
	  @GetMapping("/partyMembers")  //plan_id에 동행신청한 user_id 조회 
	  public String partyMembers(@RequestParam("planId") int planId, Model model) {
	      List<PartyDto> partyMembers = boardService.getPartyMembersByPlanId(planId);
	      model.addAttribute("partyMembers", partyMembers);
	      return "post_detail";
	  }
	  
	
	
	
	
	
	
}