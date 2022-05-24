package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	public boolean insert(MemberDto memberDto);

	public boolean insertSocial(MemberDto memberDto);

	public MemberDto selectOne(String userid, String userpwd);

	public MemberDto findById(String userid);

	public boolean update(MemberDto memberDto);

	public boolean delete(String userid, String userpwd);
}
