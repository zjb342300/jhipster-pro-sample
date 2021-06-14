package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonExtDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonExtData} and its DTO {@link CommonExtDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonExtDataMapper extends EntityMapper<CommonExtDataDTO, CommonExtData> {}
