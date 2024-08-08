package com.springboot.helper.event.gamemode_event;

import com.springboot.gamedata.entity.GameData;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GameModeEventListener {
    //    @Value()
    private final String GAME_MODE = "gamemode:";
    private final long DURATION = 2;

    @EventListener
    private void setGameMode(GameModeEvent gameModeEvent) {
        GameData gameData = gameModeEvent.getGameData();
        List<String> players = gameData.getPlayers();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                players.stream()
                        .forEach(player ->
                                gameModeEvent.getRedisService().setValues(GAME_MODE + player,
                                        String.valueOf(gameData.getGameDataId()), Duration.ofHours(2))
                        );
            } catch (Exception e) {
                gameModeEvent.getGameDataRepository().delete(gameData);
                players.stream()
                        .forEach(player ->
                                gameModeEvent.getRedisService().deleteValues(GAME_MODE + player)
                        );
                throw new RuntimeException(e);
            }
        });


    }
}
