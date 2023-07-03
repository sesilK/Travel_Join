package com.app.controller;

import com.app.dto.UserDto;
import com.app.service.user.UserService;
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
    public String register(HttpSession session) {
        // 로그인 완료 상태면 홈으로 이동
        if(session.getAttribute("userId") != null) {
            return "redirect:/home";
        }
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
    public String login(Model model, HttpSession session) {
        // 로그인 완료 상태면 홈으로 이동
        if(session.getAttribute("userId") != null) {
            return "redirect:/home";
        }
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

        session.invalidate();
        return "redirect:/home";
    }

    // 회원정보 수정 전 비밀번호 체크 페이지
    @GetMapping("/myinfo/before")
    public String myinfoBefore(HttpSession session, Model model) {
        model.addAttribute("userId", session.getAttribute("userId"));
        return "myinfoCheck";
    }

    // 비밀번호 체크 post
    @PostMapping("/myinfo/before")
    public String myinfoBefore_proc(@ModelAttribute UserDto userDto, HttpSession session) {

        boolean result = userService.login(userDto, session);
        if (result) {
            session.setAttribute("passCheck", true);
            return "redirect:/myinfo";
        }
        return "redirect:/myinfo/before";
    }

    // 회원정보수정 페이지
    @GetMapping("/myinfo")
    public String myinfo(HttpSession session, Model model) {

        // 비밀번호 체크 했다면 정보수정 페이지로 이동
        if (session.getAttribute("passCheck") != null) {
            String userId = (String) session.getAttribute("userId");
            UserDto userInfo = userService.getUserInfo(userId);
            session.setAttribute("userDto", userInfo);
            session.removeAttribute("passCheck"); // 비밀번호 체크여부 초기화
            return "myinfo";

        } else {
            // 비밀번호 체크 안했다면, 비밀번호 확인 페이지로
            return "redirect:/myinfo/before";
        }
    }


    // 회원정보 수정요청
    @PostMapping("/myinfo")
    @ResponseBody
    public String myinfo_proc(@RequestBody UserDto userDto, HttpSession session, BindingResult bindingResult) {

        // 세션에서 아이디 받아서 넘기기
        String userId = session.getAttribute("userId").toString();
        userDto.setUserId(userId);

        int result = userService.updateUser(userDto, bindingResult);
        if (result > 0) {
            return "true";
        } else if (result == -1) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else {
            return "false";
        }
    }

    // 프로필사진 수정요청
    @PostMapping("/change_profile")
    @ResponseBody
    public String changeProfileImage(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {

        String fileRoot = "C:\\plan_garlic\\images\\profile\\"; //외부경로로 저장을 희망할때.
        String originalFileName = multipartFile.getOriginalFilename();    //오리지날 파일명
        String savedFileName = UUID.randomUUID() + "_" + originalFileName/* + extension */;    //저장될 파일명
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

        if (result > 0) {
            session.removeAttribute("profileImage");
            session.setAttribute("profileImage", savedFileName);
            return "true";
        }
        return "false";
    }


    // 회원탈퇴 요청
    @PostMapping("/drop")
    @ResponseBody
    public String drop(HttpSession session) {

        String userId = session.getAttribute("userId").toString(); // 세션에서 아이디 받기

        UserDto userDto = new UserDto(); //바꿀 정보 담겨있음
        userDto.setUserId(userId); // 디티오에 아이디 넣어주기
        userDto.setStatus(-1); // 회원탈퇴 상태인 -1 넣기 테이블의 user_status 컬럼
        int result = userService.update_user_status(userDto);
        if (result > 0) { // 업데이트에 성공하면 1
            session.invalidate();
            return "true";
        }

        return "false";
    }

}
