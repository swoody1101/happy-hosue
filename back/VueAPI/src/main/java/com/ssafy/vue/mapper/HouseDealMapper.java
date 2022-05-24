package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.dto.TempDealData;

@Mapper
public interface HouseDealMapper {

	int insert(HouseDeal house);

	List<TempDealData> selectAll();

	List<TempDealData> selectAllByArea(String area);

	List<TempDealData> selectAllByKeyword(String area, String keyword);

	String getAddress(String area, String jibun);

}
