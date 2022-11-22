package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.CommentDto;

import java.util.Map;

public interface CommentService {

    Map<String, Object> selectCommentListByPage(int bno, int page);

    boolean createComment(int cno, CommentDto comment);

    boolean modifyComment(CommentDto comment);

    boolean deleteComment(int cno);
}
