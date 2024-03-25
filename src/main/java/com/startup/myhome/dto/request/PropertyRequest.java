package com.startup.myhome.dto.request;

import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRequest {
    private Integer propertyId;
    private Double finalPrice;

    private Date advertDate;

    private Double area;

    private BillOfSale billOfSale;

    private Integer buildingFloor;
    @Pattern(regexp = "^(Bakı|Gəncə|Sumqayıt|Mingəçevir)$", message = "City must be one of: Bakı, Gəncə, Sumqayıt, Mingəçevir")
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

    @AssertTrue(message = "Building and flat floors are not required when specific variables in property category are selected")
    private boolean isBuildingAndFlatFloorsValid() {
        if (propertyCategory != null && (propertyCategory.equals("Həyət evi/Bağ evi") || propertyCategory.equals("Torpaq"))) {
            return buildingFloor == null && flatFloor == null;
        }
        return true;
    }
}
