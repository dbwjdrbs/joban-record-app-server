package com.springboot.gamedata.dto;

import com.springboot.gameresultdata.entity.GameResultData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class GameDataDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private List<String> players; // 식별자
        private String gameVersion;
        private String mapName; // 다른 영역의 메모리
//        private List<GameResultData> gameResultDatas;
    }
    
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long gameDataId;
        private int playTime;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long gameDataId;
    }
}
