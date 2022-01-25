package com.project.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.memo.user.bo.UserBO;
import com.project.memo.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		Map<String, String> resultmap = new HashMap<>();
		int count = userBO.addUser(loginId, password, name, email);
		
		if(count == 1) {	//성공 -> 결과를 json 형태로 return
			resultmap.put("result", "success");
		} else {
			resultmap.put("result", "fail");
		}
		
		return resultmap;
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> singIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {					// request를 받아오기 위해 추가
		Map<String, String>	resultmap = new HashMap<>();
		
		User user = userBO.getUser(loginId, password);
		if(user != null) {	// 로그인 성공 = 객체 상태가 됨
			resultmap.put("result", "success");
			
			// User model의 일부를 key:value 형태로 저장
			HttpSession session = request.getSession();
			
			// id, loginId, name -> 로그인이 된 상태일 때 상태를 화면에 표시할 때
			session.setAttribute("userId", user.getId());	// userId에 user.getId 저장
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		} else {			// 로그인 실패 = 객체 상태가 안됨
			resultmap.put("result", "fail");
		}
		return resultmap;
	}
}
