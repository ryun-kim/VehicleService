package com.koreait.vehicleservice.center;


import com.koreait.vehicleservice.MyUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired private BoardMapper mapper;
    @Autowired private MyUserUtils userUtils;

    public int insBoard(BoardEntity boardEntity){
        boardEntity.setWriteriuser(userUtils.getLoginUserPk());
        return mapper.insBoard(boardEntity);
    }
}
