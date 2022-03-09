package com.koreait.vehicleservice.center;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeBoardDto {
    private int iboard;
    private int recordCount;
    private int currentPage;
    private int startIdx;
}
