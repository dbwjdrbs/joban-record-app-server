package com.springboot.gamedata.entity;

import com.springboot.audit.Auditable;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class GameData extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameDataId;

    private String gameId;

    private String playTime;
    private String gameVersion;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "gameData", cascade = CascadeType.PERSIST)
    private List<GameDataList> gameDataLists = new ArrayList<>();

    // LobbyData
    private String teamNumber;
    private String lobbyNation;
    private String isHuman;
    private String playerNumber;
    private String mapName;

    // ResultData
    private int troop;
    private int building;
    private int troopLoss;
    private int kill;
    private int buildingLoss;
    private int destroy;
    private int tree;
    private int grain;

    private String isWin;
    private String inGameNation;

    public void addGameDataList(GameDataList gameDataList) {
        this.gameDataLists.add(gameDataList);
        if (gameDataList.getGameData() != this) {
            gameDataList.addGameData(this);
        }
    }
}
