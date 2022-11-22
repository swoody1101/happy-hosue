package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "메일 인증")
	@PostMapping("/send")
	public String sendMail(String id, String address) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("rkarud1324@naver.com"); // NAVER, DAUM, NATE일 경우 넣어줘야 함
		simpleMessage.setTo(id + "@" + address);
		simpleMessage.setSubject("HAPPYHOUSE 이메일 인증");

		char[] passwords = new char[36];
		for (int i = 0; i < 10; i++) {
			passwords[i] = (char) ((int) '0' + i);
		}
		for (int i = 10; i < 36; i++) {
			passwords[i] = (char) ((int) 'A' + i - 10);
		}

		int pwlength = 6;
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < pwlength; i++) {
			int rand = (int) (Math.random() * 36);
			password.append(passwords[rand]);
		}

		simpleMessage.setText("인증번호: " + password.toString());
		javaMailSender.send(simpleMessage);

		return password.toString();
	}

	@ApiOperation(value = "새로운 비밀번호 발송")
	@PostMapping("/newPw")
	public void newPw(String userid, String address) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("rkarud1324@naver.com"); // NAVER, DAUM, NATE일 경우 넣어줘야 함
		simpleMessage.setTo(address);
		simpleMessage.setSubject("HAPPYHOUSE 새로운 비밀번호");

		char[] passwords = new char[62];
		for (int i = 0; i < 10; i++) {
			passwords[i] = (char) ((int) '0' + i);
		}
		for (int i = 10; i < 36; i++) {
			passwords[i] = (char) ((int) 'A' + i - 10);
		}

		for (int i = 36; i < 62; i++) {
			passwords[i] = (char) ((int) 'a' + i - 36);
		}

		int pwlength = 12;
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < pwlength; i++) {
			int rand = (int) (Math.random() * 36);
			password.append(passwords[rand]);
		}

		memberService.updatePwd(userid, password.toString());
		simpleMessage.setText("새로운 비밀번호: " + password.toString());
		javaMailSender.send(simpleMessage);
	}
}
