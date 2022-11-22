package com.ssafy.happyhouse.dto;

public class LikeDealDto {
    private String mid;
    private int dealNo;

    public LikeDealDto() {
    }

    public LikeDealDto(String mid, int dealNo) {
        super();
        this.mid = mid;
        this.dealNo = dealNo;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getDealNo() {
        return dealNo;
    }

    public void setDealNo(int dealNo) {
        this.dealNo = dealNo;
    }

    @Override
    public String toString() {
        return "LikeDeal [mid=" + mid + ", dealNo=" + dealNo + "]";
    }
}
