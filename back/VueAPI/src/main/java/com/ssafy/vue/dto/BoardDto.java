package com.ssafy.vue.dto;

public class BoardDto {
	private int bno;
	private String bwriter;
	private String btitle;
	private String bcontent;
	private int breadCount;
	private String bregtime;
	private boolean bnotice;
	
	public BoardDto() {}
	public BoardDto(String bwriter, String btitle, String bcontent, boolean bnotice) {
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bnotice = bnotice;
	}
	public BoardDto(int bno, String bwriter, String btitle, String bcontent, int breadCount, String bregtime,
			boolean bnotice) {
		this.bno = bno;
		this.bwriter = bwriter;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.breadCount = breadCount;
		this.bregtime = bregtime;
		this.bnotice = bnotice;
	}



	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getBreadCount() {
		return breadCount;
	}
	public void setBreadCount(int breadCount) {
		this.breadCount = breadCount;
	}
	public String getBregtime() {
		return bregtime;
	}
	public void setBregtime(String bregtime) {
		this.bregtime = bregtime;
	}
	public void setBnotice(boolean bnotice) {
		this.bnotice = bnotice;
	}
	public boolean isBnotice() {
		return bnotice;
	}
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", bwriter=" + bwriter + ", btitle=" + btitle + ", bcontent=" + bcontent
				+ ", breadCount=" + breadCount + ", bregtime=" + bregtime + "]";
	}
	
}
