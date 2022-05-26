package com.ssafy.vue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.QnADto;
import com.ssafy.vue.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnAMapper qnaMapper;

	@Override
	public QnADto selectOne(int qna_no) {
		return qnaMapper.selectOne(qna_no);
	}

	@Override
	public boolean modify(QnADto qnaDto) {
		return qnaMapper.modify(qnaDto) == 1;
	}

	@Override
	public boolean delete(int qna_no) {
		return qnaMapper.delete(qna_no) == 1;
	}

	@Override
	public boolean createOriginal(QnADto qnaDto) {
		return qnaMapper.createOriginal(qnaDto) == 1;
	}

	@Override
	public int getGroupNo(int qna_no) {
		return qnaMapper.getGroupNo(qna_no);
	}

	@Override
	public int getDepth(int qna_no) {
		return qnaMapper.getDepth(qna_no);
	}

	@Override
	public boolean createReply(QnADto qnaDto) {
		return qnaMapper.createReply(qnaDto) == 1;
	}

	@Override
	public int getOrder(int qna_no) {
		return qnaMapper.getOrder(qna_no);
	}

	@Override
	public String getTitle(int qna_no) {
		return qnaMapper.getTitle(qna_no);
	}

	@Override
	public int updateGroupOrder(int group_order, int group_no) {
		return qnaMapper.updateGroupOrder(group_order, group_no);
	}

	public Map<String, Object> makePage(int page) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("page", page);

		final int count = 10;

		int totalCount = qnaMapper.selectTotalCount();
		int totalPage = totalCount / count + (totalCount % count > 0 ? 1 : 0);
		result.put("totalPage", totalPage);

		int startPage = (page - 1) / count * count + 1;
		result.put("startPage", startPage);

		int endPage = Math.min(startPage + count - 1, totalPage);
		result.put("endPage", endPage);

		List<QnADto> qnaList = qnaMapper.list((page - 1) * count, count);
		result.put("qnaList", qnaList);

		return result;
	}

	@Override
	public List<QnADto> selectGroup(int group_no) {
		return qnaMapper.selectGroup(group_no);
	}

}
