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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.QnADto;
import com.ssafy.vue.service.QnAService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qService;

	@ApiOperation(value = "페이지에 맞는 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/list")
	public Map<String, Object> qnaList(@RequestParam(value = "p", defaultValue = "1") int page) {
		logger.debug("qnaList - 호출");
		return qService.makePage(page);
	}

	@ApiOperation(value = "글 번호에 해당하는 글의 정보를 반환한다.", response = QnADto.class)
	@GetMapping("/detail/{qna_no}")
	public ResponseEntity<QnADto> selectOne(@PathVariable int qna_no) {
		logger.debug("selectOne - 호출");
		return new ResponseEntity<>(qService.selectOne(qna_no), HttpStatus.OK);
	}

	@ApiOperation(value = "글 번호에 해당하는 그룹 번호를 반환한다.", response = Integer.class)
	@GetMapping("/group/{qna_no}")
	public ResponseEntity<Integer> groupNo(@PathVariable int qna_no) {
		logger.debug("getGroupNo - 호출");
		return new ResponseEntity<>(qService.getGroupNo(qna_no), HttpStatus.OK);
	}

	@ApiOperation(value = "그룹에 해당하는 글의 정보를 반환한다.", response = QnADto.class)
	@GetMapping("/detail/list/{group_no}")
	public ResponseEntity<List<QnADto>> selectGroup(@PathVariable int group_no) {
		logger.debug("selectGroup - 호출");
		return new ResponseEntity<>(qService.selectGroup(group_no), HttpStatus.OK);
	}

	@ApiOperation(value = "새 글 쓰기. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/writenew")
	public ResponseEntity<String> createOriginal(@RequestBody QnADto qnaDto) {
		logger.debug("createOriginal - 호출");
		if (qService.createOriginal(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "답글 쓰기. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/writereply/{qna_no}")
	public ResponseEntity<String> createReply(@PathVariable("qna_no") int qno, @RequestBody QnADto qnaDto) {
		logger.debug("createReply - 호출");
		qnaDto.setGroup_no(qService.getGroupNo(qno));
		qnaDto.setDepth(qService.getDepth(qno) + 1);
		qService.updateGroupOrder(qService.getOrder(qno) + 1, qService.getGroupNo(qno));
		qnaDto.setGroup_order(qService.getOrder(qno) + 1);
		qnaDto.setTitle("  └ RE: " + qService.getTitle(qno));

		if (qService.createReply(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글 번호에 해당하는 글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/modify/{qna_no}")
	public ResponseEntity<String> modify(@PathVariable("qna_no") int qno, @RequestBody QnADto qnaDto) {
		logger.debug("modify- 호출");
		logger.debug("" + qnaDto);
		qnaDto.setQna_no(qno);
		if (qService.modify(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글 번호에 해당하는 글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/delete/{qna_no}")
	public ResponseEntity<String> deleteBook(@PathVariable("qna_no") int qna_no) {
		logger.debug("deleteBook - 호출");
		if (qService.delete(qna_no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
