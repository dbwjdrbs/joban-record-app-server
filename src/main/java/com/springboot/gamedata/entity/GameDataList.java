package com.springboot.gamedata.entity;

import com.springboot.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class GameDataList extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameDataListId;

    @ManyToOne
    @JoinColumn(name = "GAME_DATA_ID")
    private GameData gameData;

    public void addGameData(GameData gameData) {
        this.gameData = gameData;
        if (!this.gameData.getGameDataLists().contains(this)) {
            this.gameData.getGameDataLists().add(this);
        }
    }
}