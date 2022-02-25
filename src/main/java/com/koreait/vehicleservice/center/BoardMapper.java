package com.koreait.vehicleservice.center;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity boardEntity);
}
