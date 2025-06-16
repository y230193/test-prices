package com.inditex.db.infrastructure.mapper;

import com.inditex.db.infrastructure.entity.PriceEntity;
import com.inditex.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "priceList", target = "rate")
    Price mapToDomain(PriceEntity priceEntity);
}
