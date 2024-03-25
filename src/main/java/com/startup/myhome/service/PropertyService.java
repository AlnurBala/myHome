package com.startup.myhome.service;

import com.startup.myhome.dto.request.PropertyRequest;
import com.startup.myhome.dto.response.PropertyResponseDto;
import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PropertyService {
    Page<PropertyResponseDto> getAllProperties(Pageable pageable);
    PropertyResponseDto createProperty(PropertyRequest propertyRequest);
    PropertyResponseDto updateProperty(Integer id, PropertyRequest propertyRequest);
    void deleteProperty(Integer id);
    Page<PropertyResponseDto> filterProperties(PropertyRequest request, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByArea(Double area, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByFloor(Integer buildingFloor, Pageable pageable);

    Page<PropertyResponseDto> getBillOfSaleByOption(BillOfSale billOfSale, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByAdvertDate(LocalDate date, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByUpdatedDate(LocalDate date, Pageable pageable);
    Page<PropertyResponseDto> getRenovationByOption(Renovation renovation, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByMetro(String metro, Pageable pageable);
    Page<PropertyResponseDto> getPropertiesByCity(String city, Pageable pageable);
}
