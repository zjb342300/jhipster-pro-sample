package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonTableFieldDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonTableField} and its DTO {@link CommonTableFieldDTO}.
 */
@Mapper(componentModel = "spring", uses = { CommonTableMapper.class })
public interface CommonTableFieldMapper extends EntityMapper<CommonTableFieldDTO, CommonTableField> {
    @Mapping(target = "metaModel", source = "metaModel", qualifiedByName = "name")
    @Mapping(target = "commonTable", source = "commonTable", qualifiedByName = "name")
    CommonTableFieldDTO toDto(CommonTableField commonTableField);

    @Named("titleSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    LinkedHashSet<CommonTableFieldDTO> toDtoTitleSet(Set<CommonTableField> commonTableField);
}
