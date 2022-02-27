package com.koreait.vehicleservice.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity boardEntity);
    List<BoardEntity> selBoardList();
    BoardVo selBoard(BoardEntity boardEntity);
    int delBoard(BoardEntity boardEntity);
    int modBoard(BoardEntity boardEntity);
}
