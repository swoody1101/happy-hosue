package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnADto : QnA 게시판 글 하나의 정보", description = "글의 상세 정보를 나타낸다.")
public class QnADto {

	@ApiModelProperty(value = "글번호", example = "3")
	private int qna_no;
	@ApiModelProperty(value = "그룹 번호")
	private int group_no;
	@ApiModelProperty(value = "정렬 기준")
	private int group_order;
	@ApiModelProperty(value = "글 깊이")
	private int depth;
	@ApiModelProperty(value = "글 제목")
	private String title;
	@ApiModelProperty(value = "작성자")
	private String writer;
	@ApiModelProperty(value = "글 내용")
	private String content;

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qno_no) {
		this.qna_no = qno_no;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getGroup_order() {
		return group_order;
	}

	public void setGroup_order(int group_order) {
		this.group_order = group_order;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
