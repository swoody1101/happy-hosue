package com.ssafy.vue.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;

	public MemberDto userInfo(String userid) throws SQLException;

	int insert(MemberDto memberDto);

	int insertSocial(MemberDto memberDto);

	MemberDto selectOne(String userid, String userpwd);

	MemberDto findById(String userid);

	int update(MemberDto memberDto);

	int delete(String userid, String userpwd);

	public String idCheck(String userid);

}