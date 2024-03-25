package com.startup.myhome.service.impl;

import com.startup.myhome.dto.request.PropertyRequest;
import com.startup.myhome.dto.response.PropertyResponseDto;
import com.startup.myhome.entity.Property;
import com.startup.myhome.enumeration.BillOfSale;
import com.startup.myhome.enumeration.Renovation;
import com.startup.myhome.mapper.PropertyMapper;
import com.startup.myhome.repository.PropertyCriteria;
import com.startup.myhome.repository.PropertyRepository;
import com.startup.myhome.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final PropertyCriteria propertyCriteria;

    public Page<PropertyResponseDto> filterProperties(PropertyRequest request, Pageable pageable) {
        Page<Property> filteredProperties = propertyCriteria.filterProperties(request, pageable);

        double averagePricePerSquareMeter = calculateAveragePricePerSquareMeter(filteredProperties);
        double userSpecifiedArea = request.getArea(); // User-specified area

        // Map properties to DTOs with their respective final prices
        Page<PropertyResponseDto> result = filteredProperties.map(property -> {
            double finalPrice = calculateFinalPrice(property, averagePricePerSquareMeter, userSpecifiedArea);
            PropertyResponseDto response = propertyMapper.toDTOx(property, finalPrice);
            response.setFinalPrice(finalPrice); // Set the final price in the DTO
            return response;
        });

        return result;
    }

    private double calculateFinalPrice(Property property, double averagePricePerSquareMeter, double userSpecifiedArea) {
        return averagePricePerSquareMeter * userSpecifiedArea;
    }

    private double calculatePricePerSquareMeter(Property property) {
        if (property.getArea() != 0) {
            return (double) property.getPrice() / property.getArea();
        } else {
            throw new IllegalArgumentException("Area cannot be zero");
        }
    }

    private double calculateAveragePricePerSquareMeter(Page<Property> properties) {
        if (properties.isEmpty()) {
            return 0.0;
        }

        double totalPricePerSquareMeter = properties.stream()
                .mapToDouble(property -> calculatePricePerSquareMeter(property))
                .sum();

        return totalPricePerSquareMeter / properties.getTotalElements();
    }

    @Override
    public Page<PropertyResponseDto> getAllProperties(Pageable pageable) {
        var propertyEntity=propertyRepository.findAll(pageable);
        return propertyMapper.toDTOp(propertyEntity);
    }
    @Override
    public PropertyResponseDto createProperty(PropertyRequest propertyRequest){
        var propertyEntity=propertyMapper.fromDTO(propertyRequest);
        propertyEntity=propertyRepository.save(propertyEntity);
        return propertyMapper.toDTO(propertyEntity);
    }
    @Override
    public PropertyResponseDto updateProperty(Integer id, PropertyRequest propertyRequest){
        var newProperty=propertyRepository.findById(id).orElse(new Property());
        propertyMapper.mapUpdateRequestToEntity(newProperty,propertyRequest);
        newProperty=propertyRepository.save(newProperty);
        return  propertyMapper.toDTO(newProperty);
    }
    @Override
    public void deleteProperty(Integer id){
        propertyRepository.deleteById(id);
    }
    @Override
    public Page<PropertyResponseDto> getPropertiesByArea(Double area, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByArea(area,pageable);
        return properties.map(propertyMapper::toDTO);
    }

    @Override
    public Page<PropertyResponseDto> getPropertiesByFloor(Integer buildingFloor, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByBuildingFloor(buildingFloor, pageable);
        return properties.map(propertyMapper::toDTO);
    }

    @Override
    public Page<PropertyResponseDto> getBillOfSaleByOption(BillOfSale billOfSale, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByBillOfSale(billOfSale,pageable);
        return properties.map(propertyMapper::toDTO);
    }
    @Override
    public Page<PropertyResponseDto> getRenovationByOption(Renovation renovation, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByRenovation(renovation,pageable);
        return properties.map(propertyMapper::toDTO);
    }

    @Override
    public Page<PropertyResponseDto> getPropertiesByMetro(String metro, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByMetro(metro,pageable);
        return properties.map(propertyMapper::toDTO);
    }
    @Override
    public Page<PropertyResponseDto> getPropertiesByCity(String city, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByCity(city,pageable);
        return properties.map(propertyMapper::toDTO);
    }

    @Override
    public Page<PropertyResponseDto> getPropertiesByAdvertDate(LocalDate date, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByAdvertDate(date, pageable);
        return properties.map(propertyMapper::toDTO);
    }

    @Override
    public Page<PropertyResponseDto> getPropertiesByUpdatedDate(LocalDate date, Pageable pageable) {
        Page<Property> properties = propertyCriteria.getPropertiesByUpdatedDate(date, pageable);
        return properties.map(propertyMapper::toDTO);
    }




}
