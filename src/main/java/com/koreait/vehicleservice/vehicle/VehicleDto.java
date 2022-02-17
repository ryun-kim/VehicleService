package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;

@Getter
@Setter
@ToString
public class VehicleEntity {
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
