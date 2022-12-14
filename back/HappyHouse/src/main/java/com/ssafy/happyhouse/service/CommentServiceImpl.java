package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String, Object> selectCommentListByPage(int bno, int page) {
        Map<String, Object> commentMap = new HashMap<String, Object>();
        commentMap.put("page", page);

        final int count = 10;

        int totalCount = commentMapper.selectTotalCommentCountByBoard(bno);
        int totalPage = totalCount / count + (totalCount % count > 0 ? 1 : 0);
        commentMap.put("totalPage", totalPage);

        int startPage = page - 5;
        if (startPage < 1) startPage = 1;
        commentMap.put("startPage", startPage);

        int endPage = page + 4;
        if (endPage > totalPage) endPage = totalPage;
        commentMap.put("endPage", endPage);

        List<CommentDto> commentList = commentMapper.selectCommentList(bno, (page - 1) * count, count);
        commentMap.put("commentList", commentList);

        return commentMap;
    }

    @Override
    public boolean createComment(int cno, CommentDto comment) {
        if (cno == 0) {
            comment.setCgroupNo(commentMapper.selectGroupNo() + 1);
            comment.setCgroupOder(0);
            comment.setCdepth(0);
            return commentMapper.createComment(comment) == 1;
        } else {
            CommentDto pComment = commentMapper.selectCommentOne(cno);
            commentMapper.updateCgroupOrder(pComment.getCgroupNo(), pComment.getCgroupOrder());
            if (commentMapper.updateCgroupOrder(pComment.getCgroupNo(), pComment.getCgroupOrder()) != 0) {
                comment.setBno(pComment.getBno());
                comment.setCgroupNo(pComment.getCgroupNo());
                comment.setCgroupOder(pComment.getCgroupOrder() + 1);
                comment.setCdepth(pComment.getCdepth() + 1);

                StringBuilder ccontent = new StringBuilder();
                for (int i = 0; i < comment.getCdepth(); i++)
                    ccontent.append("  ??? RE: ");
                ccontent.append(comment.getCcontent());
                comment.setCcontent(ccontent.toString());
                return commentMapper.createComment(comment) == 1;
            }
            return false;
        }
    }

    @Override
    public boolean modifyComment(CommentDto comment) {
        StringBuilder ccontent = new StringBuilder();
        for (int i = 0; i < comment.getCdepth(); i++)
            ccontent.append("  ??? RE: ");
        ccontent.append(comment.getCcontent());
        comment.setCcontent(ccontent.toString());
        return commentMapper.modifyComment(comment) == 1;
    }

    @Override
    public boolean deleteComment(int cno) {
        return commentMapper.deleteComment(cno) == 1;
    }
}
