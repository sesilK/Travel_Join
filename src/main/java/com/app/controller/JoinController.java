package com.app.controller;

import com.app.dto.JoinDto;
import com.app.service.party.PartyService;
import com.app.service.user.join.JoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@Controller
public class JoinController {

	@Autowired
	JoinService joinService;
	
	@Autowired
	PartyService partyService;
	
	@GetMapping("/join_view") // 여행게시판목록 요청
	public String Join_View(Model model) {
		
		List<JoinDto> list = joinService.JoinViews();
		model.addAttribute("items", list);

		return "join_view";
	}
	
	@GetMapping("/join_making") //여행게시글 생성페이지 요청 
	public String join_making(HttpServletRequest request) {
		
		return "join_making";			
		
	}
	
	@ResponseBody //이미지 에디터에 업로드시 저장요청
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, 
											HttpServletRequest request)  {
		JsonObject jsonObject = new JsonObject();
		
		
		String fileRoot = "C:\\plan_garlic\\images\\join\\"; //외부경로로 저장을 희망할때.
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		
		String savedFileName = UUID.randomUUID() + "_" + originalFileName/* + extension */;	//저장될 파일명
		
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			//----------------------------------------------------//
			//파일 저장
			InputStream fileStream = multipartFile.getInputStream();	
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	
			//----------------------------------------------------//
			//파일을 열기위한 호출
			jsonObject.addProperty("url", "\\images\\join\\"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
			//----------------------------------------------------//	
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject.toString();
	}
	
	@PostMapping("/joinmaking_process") //여행게시글 작성
	@ResponseBody
	public String joinMaking(@RequestBody String requestBody, HttpSession session) throws JsonProcessingException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		JoinDto joinDto = objectMapper.readValue(requestBody, JoinDto.class);
		
		String sessionId = session.getAttribute("userId").toString(); // 세션 로그인정보 가져오기
		joinDto.setUserId(sessionId); // dto에 주입
		
		int makingResult = joinService.boardMaking(joinDto); // 게시글 DB저장

		return "joinMaking";
	}

}
	
	
