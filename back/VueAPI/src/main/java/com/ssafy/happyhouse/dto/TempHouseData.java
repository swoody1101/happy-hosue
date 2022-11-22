package com.ssafy.happyhouse.dto;

public class TempHouseData {
	private int price; // 거래가격
	private int builtYear; // 건축년도
	private int dealYear; // 거래년도
	private String dong; // 동명
	private String aptName; // 아파트명
	private int dealMonth; // 거래월
	private int dealDay; // 거래일
	private double area; // 전용면적
	private String jibun; // 지번
	private String code; // 5자리 코드
	private int floor; // 층수

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "TempHouseData{" + "price=" + price + ", builtYear=" + builtYear + ", dealYear=" + dealYear + ", dong='"
				+ dong + '\'' + ", aptName='" + aptName + '\'' + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay
				+ ", area=" + area + ", jibun='" + jibun + '\'' + ", code='" + code + '\'' + ", floor=" + floor + '}';
	}
}
