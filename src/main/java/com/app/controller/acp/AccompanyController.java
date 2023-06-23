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

import com.app.service.acpBoard.AcpBoardService;

@Controller
public class AccompanyController {

    @Autowired
    private AcpBoardService acpBoardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        List<Board> boards = acpBoardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String boardView(@PathVariable("id") Long id, Model model) {
        Board board = acpBoardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping("/create")
    public String createBoardForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "board/create";
    }

    @PostMapping("/create")
    public String createBoard(@Valid BoardForm boardForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/create";
        }
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        acpBoardService.createBoard(board);
        return "redirect:/board/list";
    }
	
	
	
	@RequestMapping("/acp")
	public String acp() {
		return "acp-board";
	}
}
