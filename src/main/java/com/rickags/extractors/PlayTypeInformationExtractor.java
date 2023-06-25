package com.rickags.extractors;

import java.math.BigInteger;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public interface PlayTypeInformationExtractor
{
   boolean supports(String gameDetail);
   PlayTypeInformationExtractor generateExtractor(EspnPlayByPlay pbp);
   String getPlayType();
   String getOverallPlayType();
   String getPlayPlayer();
   String getPriorPlayType();
   String getGameHalf();
   String getGameDetail();
   BigInteger getTime();
   String getBeginningOrEnding();
   boolean isOpponent (String teamNumber);
   String getOpponent();
   BigInteger getPointsDifference();
   BigInteger getPossessionDifference();
}
