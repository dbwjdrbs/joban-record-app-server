package com.springboot.gameresultdata.mapper;

import com.springboot.gameresultdata.dto.GameResultDataDto;
import com.springboot.gameresultdata.entity.GameResultData;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-22T15:45:12+0900",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.22 (Azul Systems, Inc.)"
)
@Component
public class GameResultDataMapperImpl implements GameResultDataMapper {

    @Override
    public GameResultData gameResultDataPostToGameResultData(GameResultDataDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        GameResultData gameResultData = new GameResultData();

        gameResultData.setGameId( requestBody.getGameId() );
        gameResultData.setIsWin( requestBody.getIsWin() );
        gameResultData.setTroop( requestBody.getTroop() );
        gameResultData.setBuilding( requestBody.getBuilding() );
        gameResultData.setTroopLoss( requestBody.getTroopLoss() );
        gameResultData.setKill( requestBody.getKill() );
        gameResultData.setBuildingLoss( requestBody.getBuildingLoss() );
        gameResultData.setDestroy( requestBody.getDestroy() );
        gameResultData.setTree( requestBody.getTree() );
        gameResultData.setGrain( requestBody.getGrain() );

        return gameResultData;
    }
}
