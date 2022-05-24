package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.TempDealData;

public interface AptService {
	List<String> siguList();

	List<String> gugunList(String siguName);

	List<String> dongList(String siguName, String gugunName);

	List<TempDealData> dealInfo(String area);

	List<TempDealData> dealInfo(String area, String keyword);

	String getAreaCode(String si);

	String getAreaCode(String si, String gu);

	String getAreaCode(String si, String gu, String dong);
	
	List<String> getSiguCode();

}
