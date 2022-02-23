package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarExplanationEntity {
    private int selliboard;
    private String car_state;
    private String accident_state;
    private String manage_state;
    private String inout_inform;
    private String tuning_inform;
    private String other;
}
