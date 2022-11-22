package com.ssafy.happyhouse.dto;

public class AreaInfo {
	private String areaCode; // PK, 시군구코드
	private String sidoCode; // 시도코드
	private String sidoName; // 시도이름
	private String gugunCode; // 구군코드
	private String gugunName; // 구군이름
	private String dongCode; // 동코드
	private String dongName; // 동이름

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	@Override
	public String toString() {
		return "AreaInfo [areaCode=" + areaCode + ", sidoCode=" + sidoCode + ", sidoName=" + sidoName + ", gugunCode="
				+ gugunCode + ", gugunName=" + gugunName + ", dongCode=" + dongCode + ", dongName=" + dongName + "]";
	}
}
