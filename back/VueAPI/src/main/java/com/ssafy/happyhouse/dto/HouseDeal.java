package com.ssafy.happyhouse.dto;

public class HouseDeal {
    private int no;
    private String aptCode;
    private int price;
    private int dealYear;
    private int dealMonth;
    private int dealDay;

    public HouseDeal(int no, String aptCode, int price, int dealYear, int dealMonth, int dealDay) {
        this.no = no;
        this.aptCode = aptCode;
        this.price = price;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
    }

    public HouseDeal(String aptcode, int price, int dealyear, int dealmonth, int dealday) {
        this.aptCode = aptcode;
        this.price = price;
        this.dealYear = dealyear;
        this.dealMonth = dealmonth;
        this.dealDay = dealday;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDealYear() {
        return dealYear;
    }

    public void setDealYear(int dealYear) {
        this.dealYear = dealYear;
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

    @Override
    public String toString() {
        return "HouseDeal{" + "no=" + no + ", aptCode='" + aptCode + '\'' + ", price=" + price + ", dealYear="
                + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + '}';
    }
}
