package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.QnADto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnAMapper {

    List<QnADto> list(@Param("start") int start, @Param("count") int count);

    List<QnADto> selectGroup(int group_no);

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
