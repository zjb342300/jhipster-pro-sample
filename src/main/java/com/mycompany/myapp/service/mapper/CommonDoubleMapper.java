package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonDoubleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonDouble} and its DTO {@link CommonDoubleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonDoubleMapper extends EntityMapper<CommonDoubleDTO, CommonDouble> {}
