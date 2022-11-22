package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.TempHouseData;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
    private List<TempHouseData> infos;
    private TempHouseData info;
    private String temp;

    public List<TempHouseData> getHouseDeals() {
        return infos;
    }

    public SAXHandler() {
        infos = new ArrayList<>();
    }

    public void startElement(String uri, String localName, String qName, Attributes att) {
        if (qName.equals("item")) {
            info = new TempHouseData();
            infos.add(info);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (info != null) {
            if (qName.equals("거래금액")) {
                temp = temp.replaceAll(",", "");
                info.setPrice(Integer.parseInt(temp.trim()));
            } else if (qName.equals("건축년도")) {
                info.setBuiltYear(Integer.parseInt(temp));
            } else if (qName.equals("법정동")) {
                info.setDong(temp.trim());
            } else if (qName.equals("아파트")) {
                info.setAptName(temp);
            } else if (qName.equals("년")) {
                info.setDealYear(Integer.parseInt(temp));
            } else if (qName.equals("월")) {
                info.setDealMonth(Integer.parseInt(temp));
            } else if (qName.equals("일")) {
                info.setDealDay(Integer.parseInt(temp));
            } else if (qName.equals("전용면적")) {
                info.setArea(Double.parseDouble(temp));
            } else if (qName.equals("지번")) {
                info.setJibun(temp);
            } else if (qName.equals("지역코드")) {
                info.setCode(temp);
            } else if (qName.equals("층")) {
                info.setFloor(Integer.parseInt(temp));
            }
        }
    }

    public void characters(char[] ch, int start, int length) {
        temp = new String(ch, start, length);
    }

}
