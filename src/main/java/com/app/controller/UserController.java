package com.app.controller;

import com.app.dto.UserDto;
import com.app.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/register")
    public String register_proc(Model model, @ModelAttribute UserDto userDto, BindingResult bindingResult) {
        // 회원가입 성공하면 로그인 페이지로 이동
        if (userService.registerUser(userDto, bindingResult)) {
            model.addAttribute("success", "회원가입 완료!");
            return "login";
        }

        return "register";
    }

    /**
     * 아이디 중복체크 rest api
     */
    @PostMapping("/api/register")
    @ResponseBody
    public boolean register_api(@RequestParam String userId) {
        boolean result = false;
        result = userService.idCheck(userId);
        return result;
    }

    // 로그인
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login_proc(@ModelAttribute UserDto userDto, HttpSession session) {

        boolean result = userService.login(userDto, session);

        if (result) {
            session.setAttribute("userId", userDto.getUserId());
            return "redirect:/home";
        } else {
            // alert ~~~
            return "redirect:/login";
        }
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("userId");
        return "redirect:/home";
    }

    // 회원정보수정 페이지
    @GetMapping("/myinfo")
    public String myinfo(HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        UserDto userInfo = userService.getUserInfo(userId);
        session.setAttribute("userDto", userInfo);

        return "myinfo";
    }

    // 회원정보 수정요청
    @PostMapping("/myinfo")
    @ResponseBody
    public String myinfo_proc(@RequestBody String requestBody, HttpSession session) throws JsonMappingException, JsonProcessingException {

    	String userId = session.getAttribute("userId").toString(); // 세션에서 아이디 받기
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = objectMapper.readValue(requestBody, UserDto.class); //바꿀 정보 담겨있음
        userDto.setUserId(userId); // 디티오에 아이디 넣어주기
        System.out.println(userDto);
        int result = userService.updateUser(userDto);
        if (result == 1) {
            return "true";
        } else {
            return "false";
        }
    }

    // 프로필사진 수정요청
    @PostMapping("/change_profile")
    @ResponseBody
    public String changeProfileImage(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {

        String fileRoot = "C:\\plan_garlic\\images\\profile\\"; //외부경로로 저장을 희망할때.
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String savedFileName = UUID.randomUUID() + "_" + originalFileName/* + extension */;	//저장될 파일명
        File targetFile = new File(fileRoot + savedFileName);

        //파일 저장
        InputStream fileStream = multipartFile.getInputStream();
        FileUtils.copyInputStreamToFile(fileStream, targetFile);
        //----------------------------------------------------//
        UserDto userDto = new UserDto();
        userDto.setUserId(session.getAttribute("userId").toString());
        userDto.setFileName(savedFileName);

        // DB저장 로직 후 성공시 true 주기
        int result = userService.update_user_profile(userDto);

        if(result > 0) {
        	session.setAttribute("profileImage", savedFileName);
            return "true";
        }
        return "false";
    }

}
