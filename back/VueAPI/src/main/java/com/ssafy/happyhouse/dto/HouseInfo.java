package com.ssafy.happyhouse.dto;

public class HouseInfo {
    private String aptCode;
    private String aptName;
    private String areaCode;
    private String jibun;
    private int builtYear;
    private double area;
    private int floor;

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
