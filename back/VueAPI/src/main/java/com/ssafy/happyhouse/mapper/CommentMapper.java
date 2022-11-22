package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.CommentDto;

@Mapper
public interface CommentMapper {

	List<CommentDto> selectCommentList(int bno, @Param("start") int start, @Param("count") int count);

	CommentDto selectCommentOne(int cno);
	
	int selectTotalCommentCountByBoard(int bno);

	// comment 생성시 참조할 기존 cgroupNo
	int selectGroupNo();
	
	int createComment(CommentDto comment);
	
//	// bno는 게시글, cgroupNo는 selectGroupNo() + 1
//	int createCommentOriginal(CommentDto comment);
//
//	// bno는 게시글, cgroupNo 윗댓글 참조
//	int createCommentReply(CommentDto comment);

	// 새로운 답글이 상단에 위치하기 위해 새로운 답글의 cgroupOrder는 원글 + 1, 기존 답귿들 cgroupOrder 수정
	int updateCgroupOrder(int cgroupNo, int cgroupOrder);

	int modifyComment(CommentDto comment);

	int deleteComment(int cno);

}
