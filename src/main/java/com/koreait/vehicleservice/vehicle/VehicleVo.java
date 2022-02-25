package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.vehicle.VehicleEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class VehicleVo extends VehicleEntity {
    private int aircon;
    private int smart_key;
    private int camera;
    private int hi_pass;
    private int navigation;
    private int bluetooth;
    private String mainimg;
    private List<String> subimg;
    private String car_state;
    private String accident_state;
    private String manage_state;
    private String inout_inform;
    private String tuning_inform;
    private String other;
}