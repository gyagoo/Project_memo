package com.project.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.memo.common.EncryptUtils;
import com.project.memo.user.dao.UserDAO;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	//비밀번호 암호화
//	String encPassword = EncryptUtils.md5(password);
	// 회원가입(signUp)
	public int addUser(String loginId, String password, String name, String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
}
