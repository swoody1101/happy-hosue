package com.ssafy.happyhouse.dto;

public class TempDealData {
	private String aptName;
	private String address;
	private String dealDate;
	private String price;
	private String area;
	private String builtYear;

	public TempDealData(String aptName, String address, String dealDate, String price, String area, String builtYear) {
		this.aptName = aptName;
		this.address = address;
		this.dealDate = dealDate;
		this.price = price;
		this.area = area;
		this.builtYear = builtYear;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}

	@Override
	public String toString() {
		return "TempDealData [aptName=" + aptName + ", address=" + address + ", dealDate=" + dealDate + ", price="
				+ price + ", area=" + area + ", builtYear=" + builtYear + "]";
	}

}
