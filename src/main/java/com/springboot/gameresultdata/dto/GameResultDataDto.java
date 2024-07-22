package com.springboot.gameresultdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class GameResultDataDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        // ResultData
        private String gameId;
        private String isWin;  // inGameData
        private long memberId; // memberId
        private int troop;
        private int building;
        private int troopLoss;
        private int kill;
        private int buildingLoss;
        private int destroy;
        private int tree;
        private int grain;
    }
}
