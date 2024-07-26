package com.springboot.gamedata.mapper;

import com.springboot.gamedata.dto.GameDataDto;
import com.springboot.gamedata.entity.GameData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T11:15:04+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.22 (Azul Systems, Inc.)"
)
@Component
public class GameDataMapperImpl implements GameDataMapper {

    @Override
    public GameData gameDataPostToGameData(GameDataDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        GameData gameData = new GameData();

        List<String> list = requestBody.getPlayers();
        if ( list != null ) {
            gameData.setPlayers( new ArrayList<String>( list ) );
        }
        gameData.setGameVersion( requestBody.getGameVersion() );
        gameData.setMapName( requestBody.getMapName() );

        return gameData;
    }

    @Override
    public GameData gameDataPatchToGameData(GameDataDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        GameData gameData = new GameData();

        gameData.setGameDataId( requestBody.getGameDataId() );
        gameData.setPlayTime( requestBody.getPlayTime() );

        return gameData;
    }

    @Override
    public GameDataDto.Response gameDataToResponse(GameData gameData) {
        if ( gameData == null ) {
            return null;
        }

        long gameDataId = 0L;

        gameDataId = gameData.getGameDataId();

        GameDataDto.Response response = new GameDataDto.Response( gameDataId );

        return response;
    }
}
