package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonIntegerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonInteger} and its DTO {@link CommonIntegerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonIntegerMapper extends EntityMapper<CommonIntegerDTO, CommonInteger> {}
