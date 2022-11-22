package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardService {
	
	Map<String, Object> selectBoardListByPage(int page, String keyword);
	
	List<BoardDto> selectNotice();
	
	BoardDto selectBoardOne(int bno);
	
	boolean createBoard(BoardDto board);

	boolean modifyBoard(BoardDto board);
	
	boolean modifyReadCount(int bno);
	
	boolean deleteBoard(int bno);
	
	

}
