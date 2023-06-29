package com.app.controller.board;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.board.BoardDto;
import com.app.dto.party.PartyDto;
import com.app.service.board.BoardService;
import com.app.service.party.PartyService;
import com.app.service.user.UserService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;	
	@Autowired
	PartyService partyService;
	@Autowired
	UserService userService;

	
	
	@GetMapping("/detail") // 글상세 페이지 요청 
	public String detail(//HttpServletRequest request,
							Model model	) {
		
//		String nowId = request.getSession().getId(); // 세션 ID 값으로 설정
		String nowId = "ab"; // 테스트용 		
		BoardDto item = boardService.findPostById(nowId);		
		model.addAttribute("item", item);		

		return "post_detail";
	}
		

	  @RequestMapping("/joinParty") // 동행신청하기 	plan_id 에 user_id insert  
	    public String joinParty(//@RequestParam("planId") int planId,
	                            //@RequestParam("userId") String userId,
	                            Model model) {
	        PartyDto partyDto = new PartyDto();
//	        partyDto.setPlanId(planId);
//	        partyDto.setUserId(userId);	        
	        int planId = 2;  // 임시로 입력한 값
	        
	        model.addAttribute("planId", planId);        
	        partyService.joinParty(partyDto);
       
	        return "redirect:/party/post_detail?planId" + planId;
	    }
	
	  
	  @GetMapping("/partyMembers")  //plan_id에 동행신청한 user_id 조회 
	  public String partyMembers(@RequestParam("planId") int planId, Model model) {
	      List<PartyDto> partyMembers = boardService.getPartyMembersByPlanId(planId);
	      model.addAttribute("partyMembers", partyMembers);
	      return "post_detail";
	  }
	  
	
	
	
	
	
	
}