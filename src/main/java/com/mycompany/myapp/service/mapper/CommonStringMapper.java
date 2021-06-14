package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonStringDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonString} and its DTO {@link CommonStringDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonStringMapper extends EntityMapper<CommonStringDTO, CommonString> {}
