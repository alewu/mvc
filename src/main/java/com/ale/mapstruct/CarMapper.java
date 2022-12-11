package com.ale.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface CarMapper extends Converter<Car, CarDto> {

    @Mapping(target = "seats", source = "seatConfiguration")
    CarDto convert(Car car);
}