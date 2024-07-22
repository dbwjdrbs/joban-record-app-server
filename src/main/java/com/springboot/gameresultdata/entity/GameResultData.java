package com.springboot.gameresultdata.entity;

import com.springboot.gamedata.entity.GameData;
import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class GameResultData {
    // ResultData
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameResultDataId;
    private String GameId;
    private String isWin;  // inGameData
    private int troop;
    private int building;
    private int troopLoss;
    private int kill;
    private int buildingLoss;
    private int destroy;
    private int tree;
    private int grain;

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