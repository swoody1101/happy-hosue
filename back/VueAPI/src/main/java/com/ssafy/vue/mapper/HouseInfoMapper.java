package com.ssafy.vue.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseInfo;

@Mapper
public interface HouseInfoMapper {
	int insert(HouseInfo hi);

	HouseInfo select(String aptCode);

}
