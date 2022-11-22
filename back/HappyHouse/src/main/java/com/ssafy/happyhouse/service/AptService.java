package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.ReturnDealData;

public interface AptService {

	List<String> siguList();

	List<String> gugunList(String siguName);

	List<String> dongList(String siguName, String gugunName);

	List<ReturnDealData> dealInfo(String area);

	List<ReturnDealData> dealInfo(String area, String keyword);

	String getAreaCode(String si);

	String getAreaCode(String si, String gu);

	String getAreaCode(String si, String gu, String dong);

	List<String> getSiguCode();

	int insert(HouseDeal house);

	int insert(HouseInfo hi);

	HouseInfo select(String aptCode);

	String selectDong(String dong, String code5);
}
