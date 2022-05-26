package com.ssafy.vue.dto;

public class HouseInfo {
	private String aptCode; // 시코드+구코드+동코드
	private String aptName; // 아파트명
	private String areaCode; // 시군구코드
	private String jibun; // 지번
	private int builtYear; // 건축년도
	private double area; // 전용면적
	private int floor; // 층수

	public HouseInfo(String aptCode, String aptName, String areaCode, String jibun, int builtYear, double area,
			int floor) {
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.areaCode = areaCode;
		this.jibun = jibun;
		this.builtYear = builtYear;
		this.area = area;
		this.floor = floor;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "HouseInfo{" + "aptCode='" + aptCode + '\'' + ", aptName='" + aptName + '\'' + ", areaCode='" + areaCode
				+ '\'' + ", jibun='" + jibun + '\'' + ", builtYear=" + builtYear + ", area=" + area + ", floor=" + floor
				+ '}';
	}

}
