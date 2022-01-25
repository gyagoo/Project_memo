package com.project.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.memo.user.bo.UserBO;

@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/user/sign_up")
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
}
