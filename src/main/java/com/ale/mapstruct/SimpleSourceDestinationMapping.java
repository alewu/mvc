package com.ale.mapstruct;

import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapping {
    SimpleDestination sourceToDestination(SimpleSource source);
    SimpleSource destinationToSource(SimpleDestination destination);
}
