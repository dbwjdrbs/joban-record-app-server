package com.springboot.gameresultdata.controller;

import com.springboot.gameresultdata.dto.GameResultDataDto;
import com.springboot.gameresultdata.entity.GameResultData;
import com.springboot.gameresultdata.mapper.GameResultDataMapper;
import com.springboot.gameresultdata.service.GameResultDataService;
import com.springboot.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/save/gameResultDatas")
@Validated
public class GameResultDataController {
    private final static String GAME_RESULT_DATA_URL = "save/gameResultDatas";
    private final GameResultDataService gameResultDataService;
    private final GameResultDataMapper mapper;

    public GameResultDataController(GameResultDataService gameResultDataService, GameResultDataMapper mapper) {
        this.gameResultDataService = gameResultDataService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postGameResultData(@Valid @RequestBody GameResultDataDto.Post requestBody) {
        GameResultData gameResultData = mapper.gameResultDataPostToGameResultData(requestBody);
        GameResultData createdGameResultData = gameResultDataService.createGameResultData(gameResultData);
        URI location = UriCreator.createUri(GAME_RESULT_DATA_URL, createdGameResultData.getGameResultDataId());
        return ResponseEntity.created(location).build();
    }
}
