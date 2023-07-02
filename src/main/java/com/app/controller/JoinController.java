package com.app.controller;
import java.io.File;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.script.Script;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.join.JoinDto;
import com.app.dto.party.PartyDto;
import com.app.service.party.PartyService;
import com.app.service.user.join.JoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import oracle.jdbc.driver.Message;


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
	public String joinMaking(@RequestBody String requestBody) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		JoinDto joinDto = objectMapper.readValue(requestBody, JoinDto.class);
		
		String sessionId = "admin";
		joinDto.setUserId(sessionId);
		
		int makingResult = joinService.boardMaking(joinDto); // 게시글 DB저
		
		int getBoardNum = joinService.getBoardNum(joinDto);
		
		PartyDto partyDto = new PartyDto(getBoardNum, sessionId);
		int result = partyService.joinParty(partyDto);
		
		joinDto.setPlanId(getBoardNum);
		for(String IMG : joinDto.getImageFileNameList()) {
			joinDto.setFileName(IMG);
			joinService.boardImgList(joinDto);
		}
		
		return "joinMaking";
	}

}
	
	
