package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.dto.ReturnDealData;

@Mapper
public interface HouseDealMapper {

	int insert(HouseDeal house);

	List<ReturnDealData> selectAll();

	List<ReturnDealData> selectAllByArea(String area);

	List<ReturnDealData> selectAllByKeyword(String area, String keyword);

	String getAddress(String area, String jibun);

}
