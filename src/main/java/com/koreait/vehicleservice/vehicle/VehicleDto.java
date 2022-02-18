package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.vehicle.model.VehicleEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
public class VehicleDto extends VehicleEntity {
<<<<<<< HEAD
    private int selliboard;
    private int writeriuser;
    private String category;
    private String sel_company;
    private String sel_model;
    private String sel_detailModel;
    private String car_number;
    private int price;
    private String color;
    private String fuel;
    private int street;
    private String gearbox;
    private String area;
    private String[] explanations;
    private File mainimg;
    private File[] subimg;
    private String[] checked_option;
}
=======
    private String[] explanations; //설명글
    private File mainimg; //메인사진
    private File[] subimg; //서브사진
    private String[] checked_option; //옵션

}
>>>>>>> 8c3d634b58c777243b6f702860f9f57c2d871ab0
