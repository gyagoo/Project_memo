package com.project.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signup_view")
	public String signupView() {
		return "user/signUp";
	}
	
	@GetMapping("/signin_view")
	public String signinView() {
		return "user/signIn";
	}

	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		// session에 사용자 정보가 있는지로 로그인 상태를 확인
		// session에 사용자 정보를 지우면 logout
		HttpSession session = request.getSession();
		
		// 세션에 회원 정보 제거
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		// mapping url과 연결 location이 다르게 하는 것은 좋지 못하다 -> redirect
		return "redirect:/user/signin_view";
	}
}
