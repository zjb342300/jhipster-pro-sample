package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonLongDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonLong} and its DTO {@link CommonLongDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonLongMapper extends EntityMapper<CommonLongDTO, CommonLong> {}
