package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.ReturnDealData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseDealMapper {

    int insert(HouseDeal house);

    List<ReturnDealData> selectAll();

    List<ReturnDealData> selectAllByArea(String area);

    List<ReturnDealData> selectAllByKeyword(String area, String keyword);

    String getAddress(String area, String jibun);
}
