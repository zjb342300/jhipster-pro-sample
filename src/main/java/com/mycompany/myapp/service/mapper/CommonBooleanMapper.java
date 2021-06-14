package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonBooleanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonBoolean} and its DTO {@link CommonBooleanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonBooleanMapper extends EntityMapper<CommonBooleanDTO, CommonBoolean> {}
