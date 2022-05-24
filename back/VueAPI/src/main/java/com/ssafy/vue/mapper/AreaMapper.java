package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.AreaInfo;

@Mapper
public interface AreaMapper {

	int insert(AreaInfo area);

	String selectDong(String dong, String code5);

	List<AreaInfo> selectAll();

	List<String> siguList();

	List<String> gugunList(String siguName);

	List<String> dongList(String siguName, String gugunName);

	String getAreaCodeBySi(String si);

	String getAreaCodeBySiGu(String si, String gu);

	String getAreaCodeBySiGuDong(String si, String gu, String dong);

	List<String> getSiguCode();
}
