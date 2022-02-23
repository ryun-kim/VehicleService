package com.koreait.vehicleservice.vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class VehicleDto extends VehicleEntity {
    private String[] explanations; //설명글

    private List<MultipartFile> subimg; //서브사진
    private String[] checked_option; //옵션

}
