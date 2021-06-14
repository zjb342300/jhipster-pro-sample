package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonBigDecimalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonBigDecimal} and its DTO {@link CommonBigDecimalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonBigDecimalMapper extends EntityMapper<CommonBigDecimalDTO, CommonBigDecimal> {}
