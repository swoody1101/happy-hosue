package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public Map<String, Object> selectBoardListByPage(int page, String keyword) {
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("page", page);
		
		final int count = 10;
		
		int totalCount = boardMapper.selectTotalBoardCount();
		int totalPage = totalCount / count + (totalCount % count > 0 ? 1 : 0);
		boardMap.put("totalPage", totalPage);
		
		int startPage = page - 5;
		if(startPage < 1) startPage = 1;
		boardMap.put("startPage", startPage);
		
		int endPage = page + 4;
		if(endPage > totalPage) endPage = totalPage;
		boardMap.put("endPage", endPage);
		
		List<BoardDto> boardList = boardMapper.selectBoardList((page - 1) * count, count, keyword);
		boardMap.put("boardList", boardList);
		
		return boardMap;
	}

	@Override
	public List<BoardDto> selectNotice() {
		List<BoardDto> noticeList = boardMapper.selectNotice(0, 5);
		return noticeList;
	}

	@Override
	public BoardDto selectBoardOne(int bno) {
		return boardMapper.selectBoardOne(bno);
	}

	@Override
	public boolean createBoard(BoardDto board) {
		return (boardMapper.createBoard(board) == 1);
	}

	@Override
	public boolean modifyBoard(BoardDto board) {
		return (boardMapper.modifyBoard(board) == 1);
	}
	
	@Override
	public boolean modifyReadCount(int bno) {
		return (boardMapper.modifyReadCount(bno) == 1);
	}

	@Override
	public boolean deleteBoard(int bno) {
		return (boardMapper.deleteBoard(bno) == 1);
	}

	
}
