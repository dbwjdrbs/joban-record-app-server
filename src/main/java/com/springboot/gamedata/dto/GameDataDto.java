package com.springboot.gamedata.dto;

import com.springboot.gameresultdata.entity.GameResultData;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class GameDataDto {
    public static class Post {
        private String playTime;
        private String gameVersion;

        // LobbyData
        private String teamNumber;
        private String lobbyNation;
        private String inGameNation; // inGameData
        private String isHuman;
        private String playerNumber;
        private String mapName; // 다른 영역의 메모리

        private String gameId; // 식별자
    }
}
