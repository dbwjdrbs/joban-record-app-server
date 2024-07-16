package com.springboot.gamedata.dto;

import com.springboot.gamedata.entity.GameDataList;
import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class GameDataDto {
    private String gameId;
    public String playTime;
    public String gameVersion;

    @Positive
    public long memberId;

//    private List<GameDataListDto> gameDataLists;

    // LobbyData
    public String teamNumber;
    public String lobbyNation;
    public String isHuman;
    public String playerNumber;

    // ResultData
    public int troop;
    public int building;
    public int troopLoss;
    public int kill;
    public int buildingLoss;
    public int destroy;
    public int tree;
    public int grain;

    public String isWin;
    public String inGameNation;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
