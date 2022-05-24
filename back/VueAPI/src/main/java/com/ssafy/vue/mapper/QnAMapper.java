package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.QnADto;

@Mapper
public interface QnAMapper {

	List<QnADto> list(@Param("start") int start, @Param("count") int count);

	QnADto selectOne(int qna_no);

	int createOriginal(QnADto qnaDto);

	int createReply(QnADto qnaDto);

	int modify(QnADto qnaDto);

	int delete(int qna_no);

	int getGroupNo(int qna_no);

	int getDepth(int qna_no);

	int getOrder(int qna_no);

	int selectTotalCount();

	String getTitle(int qna_no);

	int updateGroupOrder(int group_order, int group_no);

}
