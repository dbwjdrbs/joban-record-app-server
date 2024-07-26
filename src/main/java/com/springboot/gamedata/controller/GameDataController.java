package com.springboot.gamedata.controller;

import com.springboot.dto.SingleResponseDto;
import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import com.springboot.gamedata.mapper.GameDataMapper;
import com.springboot.gamedata.service.GameDataService;
import com.springboot.utils.UriCreator;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/gamedatas")
@Validated
public class GameDataController {
    private final static String GAMEDATA_DEFAULT_URL = "gamedatas";
    private final GameDataService gameDataService;
    private final GameDataMapper mapper;

    public GameDataController(GameDataService gameDataService, GameDataMapper mapper) {
        this.gameDataService = gameDataService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postGameData(@Valid @RequestBody GameDataDto.Post requestBody,
                                       Authentication authentication) {
        GameData gameData = mapper.gameDataPostToGameData(requestBody);

        GameData createdGameData = gameDataService.createGameData(gameData, authentication);
        URI location = UriCreator.createUri(GAMEDATA_DEFAULT_URL, createdGameData.getGameDataId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/end")
    public ResponseEntity patchGameData(@RequestBody @Valid GameDataDto.Patch requestBody,
                                        Authentication authentication) {
        GameData gameData = mapper.gameDataPatchToGameData(requestBody);

        return new ResponseEntity<>(
                new SingleResponseDto<>(gameDataService.updateGameData(gameData, authentication))
                , HttpStatus.OK);
    }

    @GetMapping("/gamemode")
    public ResponseEntity getGameData(Authentication authentication) {
        GameData findGameData = gameDataService.findGameData(authentication);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.gameDataToResponse(findGameData))
                , HttpStatus.OK);
    }
}
