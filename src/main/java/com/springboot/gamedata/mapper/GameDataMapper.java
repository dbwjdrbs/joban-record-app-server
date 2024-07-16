package com.springboot.gamedata.mapper;

import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.entity.GameDataList;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameDataMapper {
    default GameData gameDataDtoToGameData(GameDataDto requestBody) {
        if (requestBody == null) {
            return null;
        }

        GameData gameData = new GameData();
        Member member = new Member();
        member.setMemberId(requestBody.memberId);

        List<GameDataList> gameDataLists = gameData.getGameDataLists().stream()
                .map(gameDataListDto -> {
                    GameDataList gameDataList = new GameDataList();
                    gameDataList.addGameData(gameData);
                    return gameDataList;
                }).collect(Collectors.toList());

        gameData.setGameId(requestBody.getGameId());
        gameData.setPlayTime(requestBody.getPlayTime());
        gameData.setGameVersion(requestBody.getGameVersion());
        gameData.setTeamNumber(requestBody.getTeamNumber());
        gameData.setLobbyNation(requestBody.getLobbyNation());
        gameData.setIsHuman(requestBody.getIsHuman());
        gameData.setPlayerNumber(requestBody.getPlayerNumber());
        gameData.setTroop(requestBody.getTroop());
        gameData.setBuilding(requestBody.getBuilding());
        gameData.setTroopLoss(requestBody.getTroopLoss());
        gameData.setKill(requestBody.getKill());
        gameData.setBuildingLoss(requestBody.getBuildingLoss());
        gameData.setDestroy(requestBody.getDestroy());
        gameData.setTree(requestBody.getTree());
        gameData.setGrain(requestBody.getGrain());
        gameData.setIsWin(requestBody.getIsWin());
        gameData.setInGameNation(requestBody.getInGameNation());
        gameData.setMember(member);
        gameData.setGameDataLists(gameDataLists);

        return gameData;
    }
}
