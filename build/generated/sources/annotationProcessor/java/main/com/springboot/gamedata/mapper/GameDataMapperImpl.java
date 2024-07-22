package com.springboot.gamedata.mapper;

import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-22T15:45:12+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.22 (Azul Systems, Inc.)"
)
@Component
public class GameDataMapperImpl implements GameDataMapper {

    @Override
    public GameData gameDataDtoToGameData(GameDataDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        GameData gameData = new GameData();

        return gameData;
    }
}
