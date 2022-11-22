package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardDto> selectBoardList(@Param("start") int start, @Param("count") int count, @Param("keyword") String keyword);

    List<BoardDto> selectNotice(@Param("start") int start, @Param("count") int count);

    BoardDto selectBoardOne(int bno);

    int selectTotalBoardCount();

    int createBoard(BoardDto board);

    int modifyBoard(BoardDto board);

    int modifyReadCount(int bno);

    int deleteBoard(int bno);
}
