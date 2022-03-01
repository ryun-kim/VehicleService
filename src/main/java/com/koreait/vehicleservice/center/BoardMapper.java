package com.koreait.vehicleservice.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity boardEntity);
    List<BoardEntity> selBoardList(BoardDto dto);
    BoardVo selBoard(BoardEntity boardEntity);
    int delBoard(BoardEntity boardEntity);
    int modBoard(BoardEntity boardEntity);
    ResultVo selMaxPageVal(BoardDto dto);
    int addHits(BoardEntity boardEntity);
    BoardPrevNextVo selPrevNext(BoardVo vo);
}
