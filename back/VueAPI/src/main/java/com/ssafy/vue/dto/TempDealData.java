package com.ssafy.vue.dto;

public class TempDealData {
	private int dealNo;
	private String aptName;
	private String address;
	private String dealDate;
	private String price;
	private String area;
	private String builtYear;
	private int floor;

	public TempDealData(int dealNo, String aptName, String address, String dealDate, String price, String area,
			String builtYear, int floor) {
		this.dealNo = dealNo;
		this.aptName = aptName;
		this.address = address;
		this.dealDate = dealDate;
		this.price = price;
		this.area = area;
		this.builtYear = builtYear;
		this.floor = floor;
	}

	public int getDealNo() {
		return dealNo;
	}

	public void setDealNo(int dealNo) {
		this.dealNo = dealNo;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
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
		return "TempDealData [dealNo=" + dealNo + ", aptName=" + aptName + ", address=" + address + ", dealDate="
				+ dealDate + ", price=" + price + ", area=" + area + ", builtYear=" + builtYear + ", floor=" + floor
				+ "]";
	}

}
