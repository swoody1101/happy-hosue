package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.ReturnDealData;

@Mapper
public interface HouseDealMapper {

	int insert(HouseDeal house);

	List<ReturnDealData> selectAll();

	List<ReturnDealData> selectAllByArea(String area);

	List<ReturnDealData> selectAllByKeyword(String area, String keyword);

	String getAddress(String area, String jibun);

}
