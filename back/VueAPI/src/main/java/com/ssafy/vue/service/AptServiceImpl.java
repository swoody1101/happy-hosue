package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.dto.HouseInfo;
import com.ssafy.vue.dto.ReturnDealData;
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
	public List<ReturnDealData> dealInfo(String area) {
		return hdMapper.selectAllByArea(area);
	}

	@Override
	public List<ReturnDealData> dealInfo(String area, String keyword) {
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

	@Override
	public int insert(HouseDeal house) {
		return hdMapper.insert(house);
	}

	@Override
	public int insert(HouseInfo hi) {
		return hiMapper.insert(hi);
	}

	@Override
	public HouseInfo select(String aptCode) {
		return hiMapper.select(aptCode);
	}

	@Override
	public String selectDong(String dong, String code5) {
		return areaMapper.selectDong(dong, code5);
	}

}
