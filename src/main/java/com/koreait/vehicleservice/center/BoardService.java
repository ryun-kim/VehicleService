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

    /*public List<BoardEntity> selBoardList(){
        return mapper.selBoardList();
    }*/

    public List<BoardEntity> selBoardList(BoardDto dto){
        int startIdx = (dto.getCurrentPage() - 1) * dto.getRecordCount();
        if(startIdx < 0){
            startIdx = 0;
        }
        dto.setStartIdx(startIdx);
        return mapper.selBoardList(dto);
    }

    public BoardVo selBoard(int quesiboard){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setQuesiboard(quesiboard);
        return mapper.selBoard(boardEntity);
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
}
