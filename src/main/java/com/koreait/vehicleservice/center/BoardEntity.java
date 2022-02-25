package com.koreait.vehicleservice.center;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardEntity {
    private int quesiboard;
    private int writeriuser;
    private String title;
    private String ctnt;
    private int hits;
    private String rdt;
    private String mdt;
    private String isanswer;
}
