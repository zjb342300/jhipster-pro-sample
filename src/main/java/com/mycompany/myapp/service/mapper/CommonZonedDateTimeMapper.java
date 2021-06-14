package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonZonedDateTimeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonZonedDateTime} and its DTO {@link CommonZonedDateTimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonZonedDateTimeMapper extends EntityMapper<CommonZonedDateTimeDTO, CommonZonedDateTime> {}
