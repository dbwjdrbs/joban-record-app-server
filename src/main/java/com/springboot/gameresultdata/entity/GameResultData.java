package com.springboot.gameresultdata.entity;

import com.springboot.gamedata.entity.GameData;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GameResultData {
    // ResultData
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameResultDataId;
    @Column(nullable = false)
    private int isWin;  // inGameData
    @Column(nullable = false)
    private int troop;
    @Column(nullable = false)
    private int building;
    @Column(nullable = false)
    private int troopLoss;
    @Column(nullable = false)
    private int kill;
    @Column(nullable = false)
    private int buildingLoss;
    @Column(nullable = false)
    private int destroy;
    @Column(nullable = false)
    private int tree;
    @Column(nullable = false)
    private int grain;
    // LobbyData
    @Column(nullable = false)
    private int teamNumber;
    @Column(nullable = false)
    private int isHuman;
    @Column(nullable = false)
    private int playerNumber;
    @Column(nullable = false)
    private String lobbyNation;
    @Column(nullable = false)
    private String inGameNation; // inGameData

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member = new Member();

    @ManyToOne
    @JoinColumn(name = "GAME_DATA_ID")
    private GameData gameData;

    public void addMember(Member member) {
        this.member = member;
        if (!member.getGameResultDatas().contains(this)) {
            this.member.addGameResultData(this);
        }
    }

    public void addGameData(GameData gameData) {
        this.gameData = gameData;
        if (!gameData.getGameResultDatas().contains(this)) {
            this.gameData.addGameResultData(this);
        }
    }
}
