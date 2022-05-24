package com.ssafy.vue.service;

import java.util.Map;

import com.ssafy.vue.dto.CommentDto;

public interface CommentService {

	Map<String, Object> selectCommentListByPage(int bno, int page);

	boolean createComment(int cno, CommentDto comment);
	
//	boolean createCommentOriginal(CommentDto comment);
//
//	boolean createCommentReply(int cno, CommentDto comment);

	boolean modifyComment(CommentDto comment);

	boolean deleteComment(int cno);

}
