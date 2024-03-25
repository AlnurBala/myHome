package com.startup.myhome.controller;

import com.startup.myhome.annotation.CheckMonthlyLimit;
import com.startup.myhome.dto.request.PropertyRequest;
import com.startup.myhome.dto.response.PropertyResponseDto;
import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import com.startup.myhome.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/property")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;
    @GetMapping
    @CheckMonthlyLimit
    public Page<PropertyResponseDto> getAllCategories(Pageable pageable) {
        return propertyService.getAllProperties(pageable);
    }
    @PostMapping
    public ResponseEntity<PropertyResponseDto> createProperty(@RequestBody PropertyRequest propertyRequest){
        return new ResponseEntity<>(propertyService.createProperty(propertyRequest),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public PropertyResponseDto updateProperty(@PathVariable Integer id, @RequestBody PropertyRequest propertyRequest){
        return propertyService.updateProperty(id,propertyRequest);
    }
    @DeleteMapping("/{id}")
    public void deletePropertyById(@PathVariable Integer id){
        propertyService.deleteProperty(id);
    }

    @GetMapping("/filter")
    @CheckMonthlyLimit
    public ResponseEntity<Page<PropertyResponseDto>> filterProperties(
            @Valid PropertyRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PropertyResponseDto> filteredProperties = propertyService.filterProperties(request, pageable);

        return ResponseEntity.ok(filteredProperties);
    }
    @GetMapping("/byArea/{area}")
    public Page<PropertyResponseDto> getPropertiesByArea(
            @PathVariable Double area,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByArea(area, pageable);
    }
    @GetMapping("/byArea")
    public Page<PropertyResponseDto> getPropertiesByAreaWithParameter(
            @RequestParam Double area,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByArea(area, pageable);
    }
    @GetMapping("/byBillOfSale")
    public Page<PropertyResponseDto> getBillOfSaleByOption(
            @RequestParam BillOfSale billOfSale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getBillOfSaleByOption(billOfSale, pageable);
    }
    @GetMapping("/byRenovation")
    public Page<PropertyResponseDto> getRenovationByOption(
            @RequestParam Renovation renovation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getRenovationByOption(renovation, pageable);
    }
    @GetMapping("/byBuildingFloor/{floor}")
    public Page<PropertyResponseDto> getPropertiesByBuildingFloor(
            @PathVariable(required = false) Integer floor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {


        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByFloor(floor, pageable);
    }
    @GetMapping("/byBuildingFloor")
    public Page<PropertyResponseDto> getPropertiesByBuildingFloorWithParameter(
            @RequestParam(required = false) Integer floor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByFloor(floor, pageable);
    }
    @GetMapping("/byAdvertDate")
    public Page<PropertyResponseDto> getPropertiesByAdvertDateWithParameter(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByAdvertDate(date, pageable);
    }
    @GetMapping("/byAdvertDate/{date}")
    public Page<PropertyResponseDto> getPropertiesByAdvertDate(
            @PathVariable LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByAdvertDate(date, pageable);
    }
    @GetMapping("/byUpdatedDate")
    public Page<PropertyResponseDto> getPropertiesByUpdatedDateWithParameter(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByUpdatedDate(date, pageable);
    }
    @GetMapping("/byUpdatedDate/{date}")
    public Page<PropertyResponseDto> getPropertiesByUpdatedDate(
            @PathVariable LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return propertyService.getPropertiesByUpdatedDate(date, pageable);
    }
    @GetMapping("/byMetro")
    public ResponseEntity<Page<PropertyResponseDto>> getPropertiesByMetro(
            @Valid PropertyRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PropertyResponseDto> properties = propertyService.getPropertiesByMetro(request.getMetro(), pageable);

        return ResponseEntity.ok(properties);
    }
    @GetMapping("/byCity")
    @CheckMonthlyLimit
    public ResponseEntity<Page<PropertyResponseDto>> getPropertiesByCity(
            @Valid PropertyRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<PropertyResponseDto> properties = propertyService.getPropertiesByCity(request.getCity(), pageable);

        return ResponseEntity.ok(properties);
    }



}
