package com.koreait.vehicleservice.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity boardEntity);
    int insCmtBoard(BoardCmtEntity cmtEntity);
    List<BoardEntity> selBoardList(BoardDto dto);
    BoardVo selBoard(BoardEntity boardEntity);
    BoardCmtEntity selCmtBoard(BoardCmtEntity boardEntity);
    int delBoard(BoardEntity boardEntity);
    int modBoard(BoardEntity boardEntity);
    int modBoardIsAnw(BoardCmtEntity cmtEntity);
    ResultVo selMaxPageVal(BoardDto dto);
    int addHits(BoardEntity boardEntity);
    BoardPrevNextVo selPrevNext(BoardVo vo);
}
