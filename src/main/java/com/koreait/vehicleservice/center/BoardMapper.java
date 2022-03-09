package com.koreait.vehicleservice.center;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity boardEntity);
    int insNoticeBoard(NoticeBoardEntity noticeBoardEntity);
    int insCmtBoard(BoardCmtEntity cmtEntity);
    List<BoardEntity> selBoardList(BoardDto dto);
    List<BoardEntity> selHomeBoardList();
    List<NoticeBoardEntity> selNoticeBoardList(NoticeBoardDto dto);
    List<NoticeBoardEntity> selNoticeHomeBoardList();
    BoardVo selBoard(BoardEntity boardEntity);
    NoticeBoardVo selNoticeBoard(NoticeBoardEntity noticeBoardEntity);
    BoardCmtEntity selCmtBoard(BoardCmtEntity boardEntity);
    int delBoard(BoardEntity boardEntity);
    int modBoard(BoardEntity boardEntity);
    int modBoardIsAnw(BoardCmtEntity cmtEntity);
    ResultVo selMaxPageVal(BoardDto dto);
    ResultVo selMaxPageVal2(NoticeBoardDto dto);
    int addHits(BoardEntity boardEntity);
    int addNoticeHits(NoticeBoardEntity ntity);
    BoardPrevNextVo selPrevNext(BoardVo vo);
}
