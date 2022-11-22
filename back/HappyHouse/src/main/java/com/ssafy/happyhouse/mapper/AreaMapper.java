package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.AreaInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {

    int insert(AreaInfo area);

    String selectDong(String dongName, String siguCode);

    List<AreaInfo> selectAll();

    List<String> siguList();

    List<String> gugunList(String siguName);

    List<String> dongList(String siguName, String gugunName);

    String getAreaCodeBySi(String si);

    String getAreaCodeBySiGu(String si, String gu);

    String getAreaCodeBySiGuDong(String si, String gu, String dong);

    List<String> getSiguCode();

    String getAreaName(String areaCode);
}
