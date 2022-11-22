package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.HouseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HouseInfoMapper {

    int insert(HouseInfo hi);

    HouseInfo select(String aptCode);
}
