package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.StatisticsApiDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StatisticsApi} and its DTO {@link StatisticsApiDTO}.
 */
@Mapper(componentModel = "spring", uses = { CommonTableMapper.class, UserMapper.class })
public interface StatisticsApiMapper extends EntityMapper<StatisticsApiDTO, StatisticsApi> {
    @Mapping(target = "commonTable", source = "commonTable", qualifiedByName = "name")
    @Mapping(target = "creator", source = "creator", qualifiedByName = "firstName")
    @Mapping(target = "modifier", source = "modifier", qualifiedByName = "firstName")
    StatisticsApiDTO toDto(StatisticsApi statisticsApi);
}
