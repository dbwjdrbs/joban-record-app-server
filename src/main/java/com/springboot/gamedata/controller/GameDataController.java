package com.springboot.gamedata.controller;

import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.mapper.GameDataMapper;
import com.springboot.gamedata.service.GameDataService;
import com.springboot.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/gamedatas")
public class GameDataController {
    private final static String GAMEDATA_DEFAULT_URL = "gamedatas";
    private final GameDataService gameDataService;
    private final GameDataMapper mapper;

    public GameDataController(GameDataService gameDataService, GameDataMapper mapper) {
        this.gameDataService = gameDataService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postGameData(@RequestBody GameDataDto requestBody) {
        GameData gameData = mapper.gameDataDtoToGameData(requestBody);

        GameData createdGameData = gameDataService.createGameData(gameData);
        URI location = UriCreator.createUri(GAMEDATA_DEFAULT_URL, createdGameData.getGameDataId());

        return ResponseEntity.created(location).build();
    }
}
