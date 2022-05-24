package com.ssafy.vue.dto;

public class CommentDto {
	private int cno;
	private int bno;
	private int cgroupNo;
	private int cgroupOrder;
	private int cdepth;
	private String cwriter;
	private String ccontent;
	private String cregtime;

	public CommentDto() {}
	public CommentDto(int bno, String cwriter, String ccontent) {
		this.bno = bno;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
	}
	public CommentDto(int cno, int bno, String cwriter, String ccontent, String cregtime) {
		this.cno = cno;
		this.bno = bno;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.cregtime = cregtime;
	}	
	public CommentDto(int cno, int bno, int cgroupNo, int cgroupOrder, int cdepth, String cwriter, String ccontent,
			String cregtime) {
		this.cno = cno;
		this.bno = bno;
		this.cgroupNo = cgroupNo;
		this.cgroupOrder = cgroupOrder;
		this.cdepth = cdepth;
		this.cwriter = cwriter;
		this.ccontent = ccontent;
		this.cregtime = cregtime;
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getCgroupNo() {
		return cgroupNo;
	}
	public void setCgroupNo(int cgroupNo) {
		this.cgroupNo = cgroupNo;
	}
	public int getCgroupOrder() {
		return cgroupOrder;
	}
	public void setCgroupOder(int cgroupOrder) {
		this.cgroupOrder = cgroupOrder;
	}
	public int getCdepth() {
		return cdepth;
	}
	public void setCdepth(int cdepth) {
		this.cdepth = cdepth;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCregtime() {
		return cregtime;
	}
	public void setCregtime(String cregtime) {
		this.cregtime = cregtime;
	}
	
	@Override
	public String toString() {
		return "CommentDto [cno=" + cno + ", bno=" + bno + ", cgroupNo=" + cgroupNo + ", cgroupOrder=" + cgroupOrder
				+ ", cdepth=" + cdepth + ", cwriter=" + cwriter + ", ccontent=" + ccontent + ", cregtime=" + cregtime
				+ "]";
	}	
}
