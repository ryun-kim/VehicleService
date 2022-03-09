package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehicleEntity {  //db와 일치
     int selliboard; //글번호
     int writeriuser; //작성자
     String category; //국산 수입
     String manufacturer; //제조사
     String model;  //모델
     String detail_model; //세부모델
     String car_number; //차량번호
     int price; //가격
     String color; //색상
     String fuel; //연료
     int distan_driven;  //거리
     String gearbox; //변속기
     String trading_area;  //거래지역
}
