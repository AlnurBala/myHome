package com.startup.myhome.mapper;

import com.startup.myhome.dto.request.PropertyRequest;
import com.startup.myhome.dto.response.PropertyResponseDto;
import com.startup.myhome.entity.Property;
import org.mapstruct.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface PropertyMapper {
    Property fromDTO(PropertyRequest propertyRequest);
    PropertyResponseDto toDTO(Property property);

    Property mapUpdateRequestToEntity(@MappingTarget Property property, PropertyRequest propertyRequest);
    default Page<PropertyResponseDto> toDTOp(Page<Property> properties) {
        List<PropertyResponseDto> propertyResponsDtos = properties
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(propertyResponsDtos, properties.getPageable(), properties.getTotalElements());
    }
    @Mapping(target = "finalPrice", expression = "java(calculateFinalPrice(property, averagePricePerSquareMeter))")
    PropertyResponseDto toDTOx(Property property, @Context double averagePricePerSquareMeter);

    default Double calculateFinalPrice(Property property, @Context double averagePricePerSquareMeter) {
        return averagePricePerSquareMeter * property.getArea();
    }
}
