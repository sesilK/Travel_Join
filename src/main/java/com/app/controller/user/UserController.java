package com.app.controller.user;

import com.app.dto.UserDto;
import com.app.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

        boolean result = userService.login(userDto);

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

    //수정요청
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

}
