package com.ssafy.vue.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.dto.HouseInfo;
import com.ssafy.vue.dto.TempHouseData;
import com.ssafy.vue.mapper.AreaMapper;
import com.ssafy.vue.mapper.HouseDealMapper;
import com.ssafy.vue.mapper.HouseInfoMapper;
import com.ssafy.vue.service.AptService;

public class HouseSAXParser {

	private static AptService aptService;
	private static HouseInfoMapper hid;
	private static AreaMapper aid;
	private static HouseDealMapper hdd;

	public HouseSAXParser(AptService aptService, HouseInfoMapper hid, AreaMapper aid, HouseDealMapper hdd) {
		this.aptService = aptService;
		this.hid = hid;
		this.aid = aid;
		this.hdd = hdd;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(hid);
		System.out.println(aid);
		System.out.println(hdd);
		System.out.println(aptService);
		for (int i = 2022; i <= 2022; i++) {
			for (int j = 4; j <= 4; j++) {
				String date = i + (j < 10 ? "0" : "") + j;
				for (String a : aptService.getSiguCode()) {
					System.out.println(a + " " + date);
					loadData(a, date);
				}
			}
		}
	}

	public static void loadData(String LAWD_CD, String DEAL_YMD) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); // URL
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=Sq4oqOHyVdl88G5hOP0n7IXt1uUzX%2Bg2cogUrBYxDmXifrgK1AAQU4iKNKofWlbyNjGldX8m9gyuL6F3luBROg%3D%3D");// 서비스키
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(LAWD_CD, "UTF-8")); /* 지역코드 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(DEAL_YMD, "UTF-8")); /* 날짜 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-tupe", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());

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
		}
		br.close();
		conn.disconnect();

		SAXParserFactory factory = SAXParserFactory.newInstance();
		// sb에 xml파일이 string형식으로 저장되어있다.
		try {

			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse(new InputSource(new StringReader(sb.toString())), handler);
			List<TempHouseData> houseDeals = handler.getHouseDeals();
			for (TempHouseData temp : houseDeals) {
				System.out.println(temp);
				String ac = aid.selectDong(temp.getDong(), temp.getCode());
				HouseInfo tmpHI = new HouseInfo(ac + temp.getJibun(), temp.getAptName(), ac, temp.getJibun(),
						temp.getBuiltYear(), temp.getArea(), temp.getFloor(), 0, 0);
				HouseDeal tmpHD = new HouseDeal(tmpHI.getAptCode(), temp.getPrice(), temp.getDealYear(),
						temp.getDealMonth(), temp.getDealDay());
				hid.insert(tmpHI);
				hdd.insert(tmpHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
