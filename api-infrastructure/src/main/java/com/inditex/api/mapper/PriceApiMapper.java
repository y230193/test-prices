package com.inditex.api.mapper;


import com.inditex.api.model.PriceRequestDto;
import com.inditex.api.model.PriceResponseDto;
import com.inditex.application.dto.FindPriceDto;
import com.inditex.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PriceApiMapper {

    @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "convertToLocalDateTime")
    FindPriceDto mapPriceRequestToDomain(PriceRequestDto input);

    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "convertToOffsetDateTime")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "convertToOffsetDateTime")
    PriceResponseDto mapPriceToApiResponse(Price price);

    @Named("convertToLocalDateTime")
    default LocalDateTime convertToLocalDateTime(OffsetDateTime input) {
        if (input == null) {
            return null;
        }
        return input.toLocalDateTime();
    }


    @Named("convertToOffsetDateTime")
    default OffsetDateTime convertToOffsetDateTime(LocalDateTime input) {
        if (input == null) {
            return null;
        }
        return OffsetDateTime.of(input, ZoneOffset.UTC);
    }
}
