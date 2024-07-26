package com.springboot.gamedata.mapper;

import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameDataMapper {
    GameData gameDataPostToGameData(GameDataDto.Post requestBody);
    GameData gameDataPatchToGameData(GameDataDto.Patch requestBody); //{
//        GameData gameData = new GameData();
//        gameData.setGameDataId(requestBody.getGameDataId());
//        gameData.setPlayTime(requestBody.getPlayTime());
//        gameData.getGameResultDatas().add(requestBody.getGameResultData());
//        return gameData;
//    }

    GameDataDto.Response gameDataToResponse(GameData gameData);
}