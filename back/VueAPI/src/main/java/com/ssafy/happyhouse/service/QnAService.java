package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.QnADto;

import java.util.List;
import java.util.Map;

public interface QnAService {

    Map<String, Object> makePage(int page);

    QnADto selectOne(int qna_no);

    boolean createOriginal(QnADto qnaDto);

    boolean createReply(QnADto qnaDto);

    boolean modify(QnADto qnaDto);

    boolean delete(int qna_no);

    int getGroupNo(int qna_no);

    int getDepth(int qna_no);

    int getOrder(int qna_no);

    String getTitle(int qna_no);

    int updateGroupOrder(int group_order, int group_no);

    List<QnADto> selectGroup(int group_no);
}
