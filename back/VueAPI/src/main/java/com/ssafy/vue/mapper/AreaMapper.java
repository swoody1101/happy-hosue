package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.AreaInfo;

public interface AreaMapper {

	int insert(AreaInfo area);

	String selectDong(String dong, String code5);

	List<AreaInfo> selectAll();

	List<String> siguList();

	List<String> gugunList(String siguName);

	List<String> dongList(String siguName, String gugunName);

	String getAreaCode(String si);

	String getAreaCode(String si, String gu);

	String getAreaCode(String si, String gu, String dong);

}
