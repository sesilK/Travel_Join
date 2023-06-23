package com.app.controller.acp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.acpBoard.AcpBoardDto;
import com.app.service.acpBoard.AcpBoardService;

@Controller
public class AccompanyController {

    @Autowired
    private AcpBoardService acpBoardService;

	/*
	 * @RequestMapping(value = "/board/remove", method = RequestMethod.GET) public
	 * String remove(@RequestParam("id") String id, RedirectAttributes rttr) throws
	 * Exception { acpBoardService.delete(id); rttr.addFlashAttribute("msg",
	 * "success"); return "redirect:/acp-board/listAll"; }
	 * 
	 * @RequestMapping(value = "/board/modify", method = RequestMethod.POST) public
	 * String modify(AcpBoardDto acpBoardDto,Model model ,RedirectAttributes rttr)
	 * throws Exception { acpBoardService.update(acpBoardDto);
	 * rttr.addFlashAttribute("msg","success"); return
	 * "redirect:/acp-board/listAll"; }
	 * 
	 * @RequestMapping(value = "/board/modify", method = RequestMethod.GET) public
	 * void modify(@RequestParam("id")String id,Model model) throws Exception {
	 * model.addAttribute(acpBoardService.read(id));
	 * //model.addAttribute("boardDto",service.read(bno)); }
	 * 
	 * @RequestMapping(value = "/board/read", method = RequestMethod.GET) public
	 * void read(@RequestParam("id")String id,Model model) throws Exception {
	 * model.addAttribute(acpBoardService.read(id));
	 * //model.addAttribute("boardDto",service.read(bno)); }
	 * 
	 * @RequestMapping(value = "/board/register", method = RequestMethod.GET) public
	 * void register(Model model) throws Exception { }
	 * 
	 * @RequestMapping(value = "/board/register", method = RequestMethod.POST)
	 * public String register(AcpBoardDto acpBoardDto,Model model,RedirectAttributes
	 * rttr) throws Exception { acpBoardService.create(acpBoardDto);
	 * rttr.addFlashAttribute("msg","success"); return
	 * "redirect:/acp-board/listAll"; }
	 * 
	 * @RequestMapping(value = "/board/listAll", method = RequestMethod.GET) public
	 * void listAll(Model model) throws Exception { //DB에 있는 모든데이터를 화면에 보여주기
	 * 
	 * //DB에 있는 모든데이터를 읽어다주는 service를 실해서 //모델의 list 에 담는다.
	 * model.addAttribute("acp-board",acpBoardService.listAll()); }
	 */
	
	
	@RequestMapping("/acp")
	public String acp() {
		return "acp-board";
	}
}
