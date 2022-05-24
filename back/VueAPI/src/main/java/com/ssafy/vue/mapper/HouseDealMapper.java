package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.HouseDeal;

public interface HouseDealMapper {

	int insert(HouseDeal house);

	List<HouseDeal> selectAll();

}
