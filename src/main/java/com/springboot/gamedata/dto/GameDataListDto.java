package com.springboot.gamedata.dto;

import javax.validation.constraints.Positive;

public class GameDataListDto {
    @Positive
    private long gameDataId;
}
