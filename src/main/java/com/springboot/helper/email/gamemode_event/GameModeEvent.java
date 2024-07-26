package com.springboot.helper.email.gamemode_event;

import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.repository.GameDataRepository;
import com.springboot.helper.redis.service.RedisService;
import lombok.Getter;

@Getter
public class GameModeEvent {
    private final GameData gameData;
    private final GameDataRepository gameDataRepository;
    private final RedisService redisService;

    public GameModeEvent(GameData gameData, GameDataRepository gameDataRepository, RedisService redisService) {
        this.gameData = gameData;
        this.gameDataRepository = gameDataRepository;
        this.redisService = redisService;
    }
}
