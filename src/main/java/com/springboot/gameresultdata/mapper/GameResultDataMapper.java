package com.springboot.gameresultdata.mapper;

import com.springboot.gameresultdata.dto.GameResultDataDto;
import com.springboot.gameresultdata.entity.GameResultData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameResultDataMapper {
    GameResultData gameResultDataPostToGameResultData(GameResultDataDto requestBody);
}
