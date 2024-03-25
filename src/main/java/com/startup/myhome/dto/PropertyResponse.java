package com.startup.myhome.dto;

import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponse {
    private Integer propertyId;
    private Double finalPrice;

    private Date advertDate;

    private Double area;

    private BillOfSale billOfSale;

    private Integer buildingFloor;
    private String city;

    private String description;
    private Integer flatFloor;

    private String metro;

    private Integer price;

    private String propertyCategory;

    private Renovation renovation;

    private Integer rooms;

    private String target;

    private Date updatedDate;
}
