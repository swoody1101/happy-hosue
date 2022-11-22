package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "회원가입", response = List.class)
    @PostMapping("/join")
    public String join(@RequestBody MemberDto member) {
        member.setRole("ROLE_USER");
        memberService.insert(member);
        return "회원가입 완료";
    }

    @ApiOperation(value = "회원정보 수정", response = List.class)
    @PutMapping("/modify")
    public MemberDto modify(@RequestBody MemberDto member) {
        member.setRole("ROLE_USER");
        memberService.update(member);
        return member;
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/secession")
    public void secession(String userid, String userpwd) {
        System.out.println("id: " + userid + ", userpwd: " + userpwd);
        memberService.delete(userid, userpwd);
    }

    @ApiOperation(value = "ID 중복체크", response = Boolean.class)
    @PostMapping("/idCheck")
    public boolean idCheck(@RequestBody String userid) {
        return memberService.idCheck(userid);
    }

    @ApiOperation(value = "이메일 반환", response = String.class)
    @PostMapping("/getEmail")
    public String getEmail(@RequestBody MemberDto member) {
        System.out.println("###" + member.getUserid());
        System.out.println(
                "userid: " + member.getUserid() + "mail:" + memberService.findById(member.getUserid()).getEmail());
        return memberService.findById(member.getUserid()).getEmail();
    }

    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            System.out.println(memberDto);
            MemberDto loginUser = memberService.login(memberDto);
            if (loginUser != null) {
                String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject

                logger.debug("로그인 토큰정보 : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
    @GetMapping("/info/{userid}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
                MemberDto memberDto = memberService.userInfo(userid);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
