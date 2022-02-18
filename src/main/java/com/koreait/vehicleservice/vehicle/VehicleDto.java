package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
public class VehicleDto extends VehicleEntity {
    private String[] explanations; //설명글
    private File mainimg; //메인사진
    private File[] subimg; //서브사진
    private String[] checked_option; //옵션

}
