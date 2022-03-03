package com.koreait.vehicleservice.center;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private int quesiboard;
    private int recordCount;
    private int currentPage;
    private int startIdx;
}
