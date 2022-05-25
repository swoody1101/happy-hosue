package com.ssafy.vue.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.BoardDto;
import com.ssafy.vue.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "게시판 글목록 및 페이지 정보", notes = "페이지 정보 및 해당 페이지 게시글의 정보를 반환한다.", response = Object.class)
	@GetMapping({"/list/{page}/{keyword}", "/list/{page}"})
	public ResponseEntity<Map<String, Object>> retrieveBoard(@PathVariable int page, @PathVariable(required = false) String keyword) {
		if(keyword == null) keyword="";
		logger.debug("retrieveBoard 호출");
		return new ResponseEntity<Map<String, Object>>(boardService.selectBoardListByPage(page, keyword), HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 글목록", notes = "공지사항 최신순으로 5개 반환한다.", response = List.class)
	@GetMapping("/notice")
	ResponseEntity<List<BoardDto>> retrieveNotice() {
		logger.debug("retrieveNotice 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.selectNotice(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 상세정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/detail/{bno}")
	ResponseEntity<BoardDto> detailBoard(@PathVariable int bno) {
		logger.debug("detailBoard 호출");
		return new ResponseEntity<BoardDto>(boardService.selectBoardOne(bno), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@PostMapping("/write")
	ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
		logger.debug("writeBoard 호출");
		if (boardService.createBoard(board))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@PutMapping("/modify")
	ResponseEntity<String> modifyBoard(@RequestBody BoardDto board) {
		logger.debug("modifyBoard 호출");
		if (boardService.modifyBoard(board))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@DeleteMapping("/delete/{bno}")
	ResponseEntity<String> deleteBoard(@PathVariable int bno) {
		logger.debug("deleteBoard 호출");
		if (boardService.deleteBoard(bno))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
