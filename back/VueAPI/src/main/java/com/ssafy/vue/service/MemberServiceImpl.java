package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean insert(MemberDto memberDto) {
		return memberMapper.insert(memberDto) == 1;
	}

	@Override
	public boolean insertSocial(MemberDto memberDto) {
		return memberMapper.insertSocial(memberDto) == 1;
	}

	@Override
	public MemberDto selectOne(String userid, String userpwd) {
		return memberMapper.selectOne(userid, userpwd);
	}

	@Override
	public MemberDto findById(String userid) {
		return memberMapper.findById(userid);
	}

	@Override
	public boolean update(MemberDto memberDto) {
		return memberMapper.update(memberDto) == 1;
	}

	@Override
	public boolean delete(String userid, String userpwd) {
		return memberMapper.delete(userid, userpwd) == 1;
	}

}
