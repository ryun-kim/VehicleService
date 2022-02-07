package com.koreait.vehicleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //나중에 db세팅하면 exclude부분 지워주기
public class VehicleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleServiceApplication.class, args);
    }

}
