package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonLocalDateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonLocalDate} and its DTO {@link CommonLocalDateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonLocalDateMapper extends EntityMapper<CommonLocalDateDTO, CommonLocalDate> {}
