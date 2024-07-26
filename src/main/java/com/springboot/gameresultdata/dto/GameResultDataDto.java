package com.springboot.gameresultdata.dto;

import com.springboot.gamedata.entity.GameData;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameResultDataDto {
    private Member member;
    private GameData gameData;

    // ResultData
    private int isWin;  // inGameData
    private int troop;
    private int building;
    private int troopLoss;
    private int kill;
    private int buildingLoss;
    private int destroy;
    private int tree;
    private int grain;

    // LobbyData
    private int teamNumber;
    private int isHuman;
    private int playerNumber;
    private String lobbyNation;
    private String inGameNation; // inGameData
}
