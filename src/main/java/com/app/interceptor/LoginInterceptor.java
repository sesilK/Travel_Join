package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	//Controller 진입하기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		HttpSession session = request.getSession(false); //현재 세션이 존재하지 않을 경우 새로운 세션을 생성하지 않고 null을 반환
				
		if(session == null) { // 세션이 없으면
			response.sendRedirect(request.getContextPath() + "/login");	//로그인 페이지로 보냄
			return false;
		}
		
		String userId = (String)session.getAttribute("userId");
		//UserDto user = (UserDto) session.getAttribute("userId"); //세션 userId 속성을 가져와 UserDto 객체로 캐스팅 -> 오류남

		if(userId == null || userId =="") { // 세션의 userId 속성이 없으면
			response.sendRedirect(request.getContextPath() + "/login"); //로그인 페이지로 보냄
			return false;
		}
		
		//if(user.rank.equals()) {
		//}
		
		
		//위의 경우들에 해당하지 않으면, 컨트롤러의 메서드를 실행시키고 true를 반환함
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	//Controller 처리 후 View 처리 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	//View 처리 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
