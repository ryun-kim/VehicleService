package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListSearchEntity {
    private List<String> compunyResult; //제조사
    private int  Min_Mileage; //최대거리
    private int  Max_Mileage;  //최소거리
    private int  Min_price; //최대 가격
    private int  Max_price; //최소 가격
    private List<String> gearboxResult; //변속기
    private List<String> fuelResult; //연료
    private List<String> ariaResult; //지역
//aria 오타임 수정해야할부분

}
