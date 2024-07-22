package com.springboot.gamedata.entity;

import com.springboot.audit.Auditable;
import com.springboot.gameresultdata.entity.GameResultData;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameDataId;
//    private String gameId;

    private String playTime;
    private String gameVersion;

    // LobbyData

    private String teamNumber;
    private String lobbyNation;
    private String inGameNation; // inGameData
    private String isHuman;
    private String playerNumber;
    private String mapName;
    @OneToMany(mappedBy = "gameData", cascade = CascadeType.PERSIST)
    private List<GameResultData> gameResultDatas = new ArrayList<>();
    private LocalDate createdAt = LocalDate.now();

    public void addGameResultData(GameResultData gameResultData) {
        this.gameResultDatas.add(gameResultData);
        if (gameResultData.getGameData() != this) {
            gameResultData.addGameData(this);
        }
    }
}
