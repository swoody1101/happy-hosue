package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.TempDealData;
import com.ssafy.vue.mapper.AreaMapper;
import com.ssafy.vue.mapper.HouseDealMapper;
import com.ssafy.vue.mapper.HouseInfoMapper;

@Service
public class AptServiceImpl implements AptService {

	@Autowired
	AreaMapper areaMapper;
	@Autowired
	HouseDealMapper hdMapper;
	@Autowired
	HouseInfoMapper hiMapper;

	@Override
	public List<String> siguList() {
		return areaMapper.siguList();
	}

	@Override
	public List<String> gugunList(String siguName) {
		return areaMapper.gugunList(siguName);
	}

	@Override
	public List<String> dongList(String siguName, String gugunName) {
		return areaMapper.dongList(siguName, gugunName);
	}

	@Override
	public List<TempDealData> dealInfo(String area) {
		return hdMapper.selectAllByArea(area);
	}

	@Override
	public List<TempDealData> dealInfo(String area, String keyword) {
		return hdMapper.selectAllByKeyword(area, keyword);
	}

	@Override
	public String getAreaCode(String si) {
		return areaMapper.getAreaCodeBySi(si);
	}

	@Override
	public String getAreaCode(String si, String gu) {
		return areaMapper.getAreaCodeBySiGu(si, gu);
	}

	@Override
	public String getAreaCode(String si, String gu, String dong) {
		return areaMapper.getAreaCodeBySiGuDong(si, gu, dong);
	}

	@Override
	public List<String> getSiguCode() {
		return areaMapper.getSiguCode();
	}

}
