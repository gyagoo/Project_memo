package com.project.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.memo.post.dao.PostDAO;
import com.project.memo.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		return postDAO.insertPost(userId, subject, content, file);
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


