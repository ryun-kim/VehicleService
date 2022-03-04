package com.koreait.vehicleservice.home;

import com.koreait.vehicleservice.home.model.NewsEntity;
import com.koreait.vehicleservice.home.model.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int insNewsBoard(NewsEntity newsEntity);
    List<NewsEntity> selNewsBoardList(NewsEntity entity);
    int upNewsBoardList(NewsEntity entity);
}
