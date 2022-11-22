package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto> selectCommentList(int bno, @Param("start") int start, @Param("count") int count);

    CommentDto selectCommentOne(int cno);

    int selectTotalCommentCountByBoard(int bno);

    int selectGroupNo();

    int createComment(CommentDto comment);

    int updateCgroupOrder(int cgroupNo, int cgroupOrder);

    int modifyComment(CommentDto comment);

    int deleteComment(int cno);
}
