package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.ReturnDealData;
import com.ssafy.happyhouse.service.AptService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/apt")
public class AptController {

    public static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private AptService aptService;

    @ApiOperation(value = "시구코드 반환", notes = "시구코드를 반환한다.", response = Object.class)
    @ResponseBody
    @GetMapping("/sidoCode")
    public ResponseEntity<List<String>> sidoList() {
        logger.debug("sidoList 호출");
        return new ResponseEntity<List<String>>(aptService.siguList(), HttpStatus.OK);
    }

    @ApiOperation(value = "구군코드 반환", notes = "구군코드를 반환한다.", response = Object.class)
    @GetMapping("/gugunCode")
    @ResponseBody
    public ResponseEntity<List<String>> gugunList(String siguName) {
        logger.debug("gugunCode 호출");
        return new ResponseEntity<List<String>>(aptService.gugunList(siguName), HttpStatus.OK);
    }

    @ApiOperation(value = "동코드 반환", notes = "동코드 반환한다.", response = Object.class)
    @GetMapping("/dongCode")
    @ResponseBody
    public ResponseEntity<List<String>> dongList(String siguName, String gugunName) {
        logger.debug("dongList 호출");
        return new ResponseEntity<List<String>>(aptService.dongList(siguName, gugunName), HttpStatus.OK);
    }

    @ApiOperation(value = "검색 거래정보 반환", notes = "검색어가 있을 때 거래정보 반환한다.", response = Object.class)
    @GetMapping("/dealInfo")
    @ResponseBody
    public ResponseEntity<List<ReturnDealData>> dealInfo(String siguName, String gugunName, String dongName,
                                                         String keyword) {
        logger.debug("dealInfo 호출");
        String area = aptService.getAreaCode(siguName, gugunName, dongName);
        if (keyword == null)
            return new ResponseEntity<List<ReturnDealData>>(aptService.dealInfo(area), HttpStatus.OK);
        return new ResponseEntity<List<ReturnDealData>>(aptService.dealInfo(area, keyword), HttpStatus.OK);
    }
}
