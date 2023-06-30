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


@Controller
public class BoardController {

	@Autowired
	BoardService boardService;	
	@Autowired
	PartyService partyService;
	@Autowired
	UserService userService;

<<<<<<< HEAD

	@GetMapping("/detail") // 글상세 페이지 요청 
	public String detail(//@RequestParam("planId") int planId, 
							Model model) {
		
		int planId = 3;
		BoardDto item = boardService.findPostById(planId);
	    model.addAttribute("item", item);

	 // --- 동행 신청한 멤버 조회 ---
	    
	    String userId = "ab";
	 //   String userId = item.getUserId();
	    List<BoardDto> partyMembers = boardService.myTeamDetail(userId);
	    model.addAttribute("partyMembers", partyMembers);

	    return "post_detail";
	}

	@PostMapping("/joinParty") // 동행신청하기  plan_id 에 user_id insert  
	public String joinParty(@ModelAttribute BoardDto boardDto, Model model) {
		
	    String userId = "user123"; // 로그인한 사용자의 아이디 //test용
	    boardDto.setUserId(userId);	//test용
	    
	    model.addAttribute("userId", boardDto.getUserId());
	 //   model.addAttribute("planId", boardDto.getPlanId());
	    partyService.joinParty(boardDto);

	    return "redirect:/detail";
	}
	

/*
	@GetMapping("/partyMembers")  //plan_id에 동행신청한 user_id 조회 
	public String partyMembers(@RequestParam("planId") int planId, Model model) {
		List<PartyDto> partyMembers = boardService.getPartyMembersByPlanId(planId);
		model.addAttribute("partyMembers", partyMembers);
=======
	
	
	@GetMapping("/detail") // 글상세 페이지 요청 
	public String detail(@RequestParam("planId") int planId,
							Model model	) {
		
//		String nowId = request.getSession().getId(); // 세션 ID 값으로 설정
//		String nowId = "ab"; // 테스트용 		
		
		boardService.plusView(planId);
		BoardDto item = boardService.findPostById(planId);		
		model.addAttribute("item", item);		

		//조회수 올리기 
		
>>>>>>> dcb72b8b0c0a421d62435665e8382dc0b8dd380b
		return "post_detail";
	}
*/

}