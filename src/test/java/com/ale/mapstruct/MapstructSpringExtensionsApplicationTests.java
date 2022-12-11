package com.ale.mapstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

@SpringBootTest
class MapstructSpringExtensionsApplicationTests {
    @Autowired
    private ConversionService conversionService;
    @Test
    void contextLoads() {

        Car source = new Car();
        source.setName("A");
        source.setType("SUV");
        source.setSeatConfiguration(7);
        // 替代 CarMapper carMapper = CarMapper.CAR_MAPPER;
        CarDto carDto = conversionService.convert(source, CarDto.class);
        Assertions.assertNotNull(carDto);
        Assertions.assertEquals(7,carDto.getSeats());
        Assertions.assertEquals("A",carDto.getName());
        Assertions.assertEquals("SUV",carDto.getType());

    }

}