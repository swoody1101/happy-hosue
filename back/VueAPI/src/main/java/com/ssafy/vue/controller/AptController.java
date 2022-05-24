package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.service.AptService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/apt")
public class AptController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AptService aptService;

	@ApiOperation(value = "시구코드 반환", notes = "시구코드를 반환한다.", response = Object.class)
	@GetMapping("/siguCode")
	public ResponseEntity<List<String>> siguList() {
		logger.debug("siguList 호출");
		return new ResponseEntity<List<String>>(aptService.siguList(), HttpStatus.OK);
	}
}
