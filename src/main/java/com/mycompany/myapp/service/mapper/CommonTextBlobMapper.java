package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommonTextBlobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommonTextBlob} and its DTO {@link CommonTextBlobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommonTextBlobMapper extends EntityMapper<CommonTextBlobDTO, CommonTextBlob> {}
