package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonTableRelationshipDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonTableRelationship} and its DTO {@link CommonTableRelationshipDTO}.
 */
@Mapper(componentModel = "spring", uses = { CommonTableMapper.class, DataDictionaryMapper.class })
public interface CommonTableRelationshipMapper extends EntityMapper<CommonTableRelationshipDTO, CommonTableRelationship> {
    @Mapping(target = "relationEntity", source = "relationEntity", qualifiedByName = "name")
    @Mapping(target = "dataDictionaryNode", source = "dataDictionaryNode", qualifiedByName = "name")
    @Mapping(target = "metaModel", source = "metaModel", qualifiedByName = "name")
    @Mapping(target = "commonTable", source = "commonTable", qualifiedByName = "name")
    CommonTableRelationshipDTO toDto(CommonTableRelationship commonTableRelationship);

    @Named("nameSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LinkedHashSet<CommonTableRelationshipDTO> toDtoNameSet(Set<CommonTableRelationship> commonTableRelationship);
}
