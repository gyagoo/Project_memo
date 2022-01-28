package com.project.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.memo.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,			// 파일 전달 받기 -> 파일을 저장하고 관리하는 class를 따로 만들어 관리
			HttpServletRequest request) {

		/*
		 * post table에 저장
		 * 작성자가 누군지 저장 -> 나중에 내가 쓴 글을 확인할 수 있음
		 * 현재 로그인한 사용자인 user table id (pk) -> Controller
		 * 
		 * 로그인 한 상태에서만 session에 저장된 값을 가져올 수 있음
		 * setAttribute로 저장될 때 type이 그대로 저장됨
		 * 알아서 Integer로 저장 
		 */
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");	// casting
		
		int count = postBO.addPost(userId, subject, content, file);
		Map<String, Object>	postmap	= new HashMap<>();
		
		if(count == 1) {
			postmap.put("result", "success");
		} else {
			postmap.put("reuslt", "fail");
		}
		
		return postmap;
	}
	// 삭제
	@GetMapping("/delete")
	public Map<String, String> postDelete(@RequestParam("postId") int postId) {
		Map<String, String>	postmap = new HashMap<>();
		int count = postBO.deletePost(postId);
		
		if(count == 1) {
			postmap.put("result", "success");
		} else {
			postmap.put("result", "fail");
		}
		
		return postmap;
	}
	
}
