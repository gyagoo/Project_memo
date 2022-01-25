package com.project.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.memo.common.EncryptUtils;
import com.project.memo.user.dao.UserDAO;
import com.project.memo.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	// 회원가입(signUp)
	public int addUser(String loginId, String password, String name, String email) {

		// 비밀번호 암호화
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encPassword, name, email);
	}
	
	// 로그인(signIn) -> select한 유저 정보를 쓸 것이기 때문에 
	public User getUser(String loginId, String password) {
		// 입력 비밀번호 암호화 -> 암호화된 비밀번호끼리 비교할 수 있도록		
		return userDAO.selectUser(loginId, EncryptUtils.md5(password));
	}
	
}
