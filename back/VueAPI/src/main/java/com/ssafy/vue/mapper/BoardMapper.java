package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> selectBoardList(@Param("start") int start, @Param("count") int count, @Param("keyword") String keyword);
	
	List<BoardDto> selectNotice(@Param("start") int start, @Param("count") int count);
	
	BoardDto selectBoardOne(int bno);
	
	int selectTotalBoardCount();
	
	int createBoard(BoardDto board);

	int modifyBoard(BoardDto board);
	
	int modifyReadCount(int bno);
	
	int deleteBoard(int bno);
	
}
