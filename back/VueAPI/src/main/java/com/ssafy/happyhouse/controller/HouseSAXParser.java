package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.TempHouseData;
import com.ssafy.happyhouse.mapper.AreaMapper;
import com.ssafy.happyhouse.mapper.HouseDealMapper;
import com.ssafy.happyhouse.mapper.HouseInfoMapper;
import com.ssafy.happyhouse.service.AptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/data")
public class HouseSAXParser {

    @Autowired
    private AptService aptService;
    @Autowired
    private HouseInfoMapper hid;
    @Autowired
    private AreaMapper aid;
    @Autowired
    private HouseDealMapper hdd;

    @PostMapping("/input")
    public void inputData() throws IOException {
        for (int i = 2022; i <= 2022; i++) {
            for (int j = 1; j <= 1; j++) {
                String date = i + (j < 10 ? "0" : "") + j;
                for (String a : aptService.getSiguCode()) {
                    System.out.println(a + " " + date);
                    loadData(a, date);
                }
            }
        }
    }

    private void loadData(String LAWD_CD, String DEAL_YMD) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(
                "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); // URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                + "=Sq4oqOHyVdl88G5hOP0n7IXt1uUzX%2Bg2cogUrBYxDmXifrgK1AAQU4iKNKofWlbyNjGldX8m9gyuL6F3luBROg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(LAWD_CD, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(DEAL_YMD, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-tupe", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader br;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            System.out.println("line");
            System.out.println(line);
        }
        br.close();
        conn.disconnect();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {

            SAXParser parser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(new InputSource(new StringReader(sb.toString())), handler);
            List<TempHouseData> houseDeals = handler.getHouseDeals();
            for (TempHouseData temp : houseDeals) {
                System.out.println(temp);
                String ac = aid.selectDong(temp.getDong(), temp.getCode());
                HouseInfo tmpHI = new HouseInfo(ac + temp.getJibun(), temp.getAptName(), ac, temp.getJibun(),
                        temp.getBuiltYear(), temp.getArea(), temp.getFloor());
                HouseDeal tmpHD = new HouseDeal(tmpHI.getAptCode(), temp.getPrice(), temp.getDealYear(),
                        temp.getDealMonth(), temp.getDealDay());
                System.out.println("tmpHi: " + tmpHI);
                hid.insert(tmpHI);
                hdd.insert(tmpHD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
