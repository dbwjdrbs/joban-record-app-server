package com.springboot.gamedata.entity;

import com.springboot.gameresultdata.entity.GameResultData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class GameData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameDataId;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(name = "PLAYERS", nullable = false)
    private List<String> players = new ArrayList<>();
    private String gameVersion;
    private String mapName; // 다른 메모리 영역
    private int playTime;

    @OneToMany(mappedBy = "gameData", cascade = CascadeType.PERSIST)
    private List<GameResultData> gameResultDatas = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();

    public void addGameResultData(GameResultData gameResultData) {
        this.gameResultDatas.add(gameResultData);
        if (gameResultData.getGameData() != this) {
            gameResultData.addGameData(this);
        }
    }
}
