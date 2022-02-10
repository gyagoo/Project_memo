package com.project.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.memo.common.FileManagerService;
import com.project.memo.post.dao.PostDAO;
import com.project.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		// 파일을 컴퓨터 (서버)에 저장하고, 클라이언트가(브라우저) 가 접근 가능한 주소를 만들어 낸다.  
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, subject, content, filePath);
	}
	// 객체에서 값을 가져와 List 형태로 return
	public List<Post> getPostList(int userId) {	// 특정 사용자의 메모만 가져오기 위한 parameter
		return postDAO.selectPostList(userId); 
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	// 삭제
	public int deletePost(int postId) {
		return postDAO.deletePost(postId);
	}
}

/*
 	파일은 직접 저장하는 것이 아닌 경로를 저장해서 url 형태로 사용할 수 있도록 해야 함
 */


