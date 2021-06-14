package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.GpsInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GpsInfo} and its DTO {@link GpsInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GpsInfoMapper extends EntityMapper<GpsInfoDTO, GpsInfo> {}
