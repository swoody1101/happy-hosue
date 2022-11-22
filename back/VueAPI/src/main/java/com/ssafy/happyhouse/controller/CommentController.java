package com.ssafy.happyhouse.controller;

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

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.service.CommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	
	@ApiOperation(value = "게시판 댓글 목록 및 페이지 정보", notes = "페이지 정보 및 해당 페이지 게시글의 댓글 정보를 반환.", response = Object.class)
	@GetMapping("/list/{bno}/{page}")
	public ResponseEntity<Map<String, Object>> retrieveComment(@PathVariable int bno, @PathVariable int page) {
		logger.debug("retrieveComment 호출");
		return new ResponseEntity<Map<String, Object>>(commentService.selectCommentListByPage(bno, page), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 댓글등록", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@PostMapping("/write/{cno}")
	ResponseEntity<String> writeComment(@PathVariable int cno, @RequestBody CommentDto comment){
		logger.debug("writeComment 호출");
		if (commentService.createComment(cno, comment))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 댓글정보 수정", notes = "댓글 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@PutMapping("/modify")
	ResponseEntity<String> modifyComment(@RequestBody CommentDto comment) {
		logger.debug("modifyComment 호출");
		if (commentService.modifyComment(comment))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 댓글삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@ResponseBody
	@DeleteMapping("/delete/{cno}")
	ResponseEntity<String> deleteComment(@PathVariable int cno) {
		logger.debug("deleteComment 호출");
		if (commentService.deleteComment(cno))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
