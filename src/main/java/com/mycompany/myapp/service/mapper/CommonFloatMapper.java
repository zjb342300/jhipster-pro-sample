package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonFloatDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonFloat} and its DTO {@link CommonFloatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonFloatMapper extends EntityMapper<CommonFloatDTO, CommonFloat> {}
