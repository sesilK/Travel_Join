package com.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.UserDto;

public class LoginInterceptor implements HandlerInterceptor {
	
	//Controller 진입하기 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		//컨트롤러로 넘어가기전 처리할 로직
		
		HttpSession session =  request.getSession(false);
		//Session 있나? 
		//Session 안에 로그인 정보가 있나?
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
			//인터셉터 return false
			//이 다음 진행 X  더이상 다음 controller 처리로 넘어가지말고 여기서 끝.
		}
		
		//이전에 로그인 성공시 session.setAttribute("loginUser", user);
		UserDto user = (UserDto) session.getAttribute("loginUser");
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		
		//getSession 있나 없나 -> 없어? -> 만듬
		//getSession(false) 있나 없나 -> 없다? -> null (안만들고)
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	//요청 -> preHandle -> Controller(service, dao, db) 
	//    -> postHandle -> view -> afterCompletion -> 응답
	
	//Controller 처리 후 View 처리 전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	//View 처리 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
