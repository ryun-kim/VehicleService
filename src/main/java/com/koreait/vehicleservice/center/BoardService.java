package com.koreait.vehicleservice.center;


import com.koreait.vehicleservice.MyUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired private BoardMapper mapper;
    @Autowired private MyUserUtils userUtils;

    public int insBoard(BoardEntity boardEntity){
        boardEntity.setWriteriuser(userUtils.getLoginUserPk());
        return mapper.insBoard(boardEntity);
    }

    public int insNoticeBoard(NoticeBoardEntity noticeBoardEntity){
        noticeBoardEntity.setWriteriuser(userUtils.getLoginUserPk());
        return mapper.insNoticeBoard(noticeBoardEntity);
    }

    public int insCmtBoard(BoardCmtEntity cmtEntity){
        cmtEntity.setWriterNm(userUtils.getLoginUser().getNm());
        mapper.modBoardIsAnw(cmtEntity);
        return mapper.insCmtBoard(cmtEntity);
    }

    public List<BoardEntity> selBoardList(BoardDto dto){
        int startIdx = (dto.getCurrentPage() - 1) * dto.getRecordCount();
        if(startIdx < 0){
            startIdx = 0;
        }
        dto.setStartIdx(startIdx);
        return mapper.selBoardList(dto);
    }

    public List<NoticeBoardEntity> selNoticeBoardList(NoticeBoardDto dto){
        int startIdx = (dto.getCurrentPage() - 1) * dto.getRecordCount();
        if(startIdx < 0){
            startIdx = 0;
        }
        dto.setStartIdx(startIdx);
        return mapper.selNoticeBoardList(dto);
    }

    public BoardVo selBoard(int quesiboard){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setQuesiboard(quesiboard);
        BoardVo detail = mapper.selBoard(boardEntity);
        int hitsResult = mapper.addHits(boardEntity);
        if(hitsResult == 1){ //detail로 들어갔을때 올려진 hits가 바로보이게하기위해
            detail.setHits(detail.getHits() + 1);
        }

        return detail;
    }

    public NoticeBoardVo selNoticeBoard(int iboard){
        NoticeBoardEntity entity = new NoticeBoardEntity();
        entity.setIboard(iboard);
        NoticeBoardVo detail = mapper.selNoticeBoard(entity);
        int hitsResult = mapper.addNoticeHits(entity);
        if(hitsResult == 1){ //detail로 들어갔을때 올려진 hits가 바로보이게하기위해
            detail.setHits(detail.getHits() + 1);
        }
        return detail;
    }

    public BoardCmtEntity selCmtBoard(int quesiboard){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setQuesiboard(quesiboard);
        return mapper.selCmtBoard(entity);
    }

    public BoardPrevNextVo selPrevNext(BoardVo vo){
        return mapper.selPrevNext(vo);
    }

    public void delBoard(int quesiboard){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setQuesiboard(quesiboard);
        boardEntity.setWriteriuser(userUtils.getLoginUserPk());
        mapper.delBoard(boardEntity);
    }

    public int modBoard(BoardEntity boardEntity){
        boardEntity.setWriteriuser(userUtils.getLoginUserPk());
        return mapper.modBoard(boardEntity);
    }

    public ResultVo selMaxPageVal(BoardDto dto){
        return mapper.selMaxPageVal(dto);
    }

    public ResultVo selMaxPageVal2(NoticeBoardDto dto){
        return mapper.selMaxPageVal2(dto);
    }
}
