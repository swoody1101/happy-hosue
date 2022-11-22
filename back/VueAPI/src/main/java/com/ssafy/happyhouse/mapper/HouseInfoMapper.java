package com.ssafy.happyhouse.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseInfo;

@Mapper
public interface HouseInfoMapper {
	int insert(HouseInfo hi);

	HouseInfo select(String aptCode);

}
