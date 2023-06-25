package com.rickags.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonFilePlayByPlayDataManipulatorService
{

   private final Integer DECIMAL_SCALE = 5;

   @Autowired
   protected EspnPlayByPlayExtractionService espnPlayByPlayExtractionService;

   //
//   public String determineTimeFrameType(BigInteger priorTimeSeconds)
//   {
//      if (priorTimeSeconds.compareTo(new BigInteger("1260")) > 0) {
//         return "REGGAME";
//      } else if (priorTimeSeconds.compareTo(new BigInteger("1200")) > 0) {
//         return "FIRSTHALF1MIN";
//      } else if (priorTimeSeconds.compareTo(new BigInteger("300")) > 0) {
//         return "REGGAME";
//      } else {
//         return "LAST5MIN";
//      }
//   }
//
//   public PossessionResultBreakdown extractPossessionResultBreakdown (List<PossessionResultBreakdown> possessionResultBreakdownList,
//                                                                      String playType,
//                                                                      String timeFrameType,
//                                                                      BigInteger possessionDifference,
//                                                                      BigInteger pointsDifference,
//                                                                      String offensiveGroupingName,
//                                                                      String defensiveGroupingName)
//   {
//
//      for(PossessionResultBreakdown possessionResultBreakdown : possessionResultBreakdownList){
//         if(possessionResultBreakdown.getPossessionStart().equals(playType) &&
//            possessionResultBreakdown.getGameTimeFrame().equals(timeFrameType) &&
//            possessionResultBreakdown.getOffensiveGroupingName().equals(offensiveGroupingName) &&
//            possessionResultBreakdown.getDefensiveGroupingName().equals(defensiveGroupingName) &&
//            possessionResultBreakdown.getPossessionDifference().equals(possessionDifference) &&
//            possessionResultBreakdown.getPointsDifference().equals(pointsDifference)){
//
//            return possessionResultBreakdown;
//
//         }
//      }
//
//      return null;
//   }
//
//   public PossessionResultBreakdown extractPossessionResultBreakdownSummary (List<PossessionResultBreakdown> possessionResultBreakdownSummaryList,
//                                                                             PossessionResultBreakdown possessionResultBreakdown)
//   {
//
//      for(PossessionResultBreakdown possessionResultBreakdownSummary : possessionResultBreakdownSummaryList){
//         if(possessionResultBreakdownSummary.getPossessionStart().equals(possessionResultBreakdown.getPossessionStart()) &&
//            possessionResultBreakdownSummary.getGameTimeFrame().equals(possessionResultBreakdown.getGameTimeFrame()) &&
//            possessionResultBreakdownSummary.getOffensiveGroupingName().equals(possessionResultBreakdown.getOffensiveGroupingName()) &&
//            possessionResultBreakdownSummary.getDefensiveGroupingName().equals(possessionResultBreakdown.getDefensiveGroupingName()) &&
//            possessionResultBreakdownSummary.getPossessionDifference().equals(possessionResultBreakdown.getPossessionDifference()) &&
//            possessionResultBreakdownSummary.getPointsDifference().equals(possessionResultBreakdown.getPointsDifference())){
//
//            return possessionResultBreakdownSummary;
//
//         }
//      }
//
//      return null;
//   }
//
//   public PossessionResultData extractPossessionResultData (PossessionResultBreakdown possessionResultBreakdown, String overallPlayType)
//   {
//      for(PossessionResultData possessionResultData : possessionResultBreakdown.getPossessionResultDataList()){
//         if(possessionResultData.getOverallPossessionResult().equals(overallPlayType)){
//            return possessionResultData;
//         }
//      }
//
//      return null;
//   }
//
//   private PossessionResultData extractPossessionResultDataSummary (PossessionResultBreakdown possessionResultBreakdownSummary,
//                                                                    PossessionResultData possessionResultData)
//   {
//      for(PossessionResultData possessionResultDataSummary : possessionResultBreakdownSummary.getPossessionResultDataList()){
//         if(possessionResultDataSummary.getOverallPossessionResult().equals(possessionResultData.getOverallPossessionResult())){
//            return possessionResultDataSummary;
//         }
//      }
//
//      return null;
//   }
//
//   public IndividualPossessionResultData extractIndividualPossessionResultData (PossessionResultData possessionResultData,
//                                                                                String playPlayer)
//   {
//      for(IndividualPossessionResultData individualPossessionResultData : possessionResultData.getIndividualPossessionResultDataList()){
//         if(individualPossessionResultData.getPlayPlayer().equals(playPlayer)){
//            return individualPossessionResultData;
//         }
//      }
//
//      return null;
//   }
//
//   private IndividualPossessionResultData extractIndividualPossessionResultDataSummary (PossessionResultData possessionResultDataSummary,
//                                                                                        IndividualPossessionResultData individualPossessionResultData)
//   {
//      for(IndividualPossessionResultData individualPossessionResultDataSummary : possessionResultDataSummary.getIndividualPossessionResultDataList()){
//         if(individualPossessionResultDataSummary.getPlayPlayer().equals(individualPossessionResultData.getPlayPlayer())){
//            return individualPossessionResultDataSummary;
//         }
//      }
//
//      return null;
//   }
//
//   public ShotResultBreakdown extractShotResultBreakdown (List<ShotResultBreakdown> shotResultBreakdownList,
//                                                          String priorPlayType,
//                                                          String playType,
//                                                          String timeFrameType,
//                                                          String offensiveGroupingName,
//                                                          String defensiveGroupingName)
//   {
//      for(ShotResultBreakdown shotResultBreakdown : shotResultBreakdownList){
//         if(shotResultBreakdown.getPossessionStart().equals(priorPlayType) &&
//            shotResultBreakdown.getOverallPossessionResult().equals(playType) &&
//            shotResultBreakdown.getGameTimeFrame().equals(timeFrameType) &&
//            shotResultBreakdown.getOffensiveGroupingName().equals(offensiveGroupingName) &&
//            shotResultBreakdown.getDefensiveGroupingName().equals(defensiveGroupingName)){
//            return shotResultBreakdown;
//         }
//      }
//
//      return null;
//   }
//
//   public IndividualShotResultData extractIndividualShotResultData (ShotResultBreakdown shotResultBreakdown,
//                                                                    PlayTypeInformationExtractor playTypeInformation)
//   {
//      for(IndividualShotResultData individualShotResultData: shotResultBreakdown.getIndividualShotResultDataList()){
//         if(individualShotResultData.getPlayPlayer().equals(playTypeInformation.getPlayPlayer())){
//            return individualShotResultData;
//         }
//      }
//
//      return null;
//   }
//
//
//   private ReboundResultBreakdown extractReboundResultBreakdown (List<ReboundResultBreakdown> reboundResultBreakdownList,
//                                                                 String priorPlayTypePast,
//                                                                 String playTypePast,
//                                                                 String timeFrameType,
//                                                                 String offensiveGroupingName,
//                                                                 String defensiveGroupingName)
//   {
//      for(ReboundResultBreakdown reboundResultBreakdown : reboundResultBreakdownList){
//         if(reboundResultBreakdown.getGameTimeFrame().equals(timeFrameType) &&
//            reboundResultBreakdown.getPossessionStart().equals(priorPlayTypePast) &&
//            reboundResultBreakdown.getOverallPossessionResult().equals(playTypePast) &&
//            reboundResultBreakdown.getOffensiveGroupingName().equals(offensiveGroupingName) &&
//            reboundResultBreakdown.getDefensiveGroupingName().equals(defensiveGroupingName)){
//
//            return reboundResultBreakdown;
//         }
//      }
//      return null;
//   }
//
//   private IndividualReboundResultData extractIndividualReboundResultData (ReboundResultBreakdown reboundResultBreakdown,
//                                                                           PlayTypeInformationExtractor playTypeInformation)
//   {
//      for(IndividualReboundResultData individualReboundResultData : reboundResultBreakdown.getIndividualReboundResultDataList()){
//         if(individualReboundResultData.getPlayPlayer().equals(playTypeInformation.getPlayPlayer())){
//            return individualReboundResultData;
//         }
//      }
//
//      return null;
//   }
//
//   public IndividualFreeThrowResultData extractIndividualFreeThrowResultData (FreeThrowResultBreakdown freeThrowResultBreakdown,
//                                                                              PlayTypeInformationExtractor playTypeInformation)
//   {
//      for(IndividualFreeThrowResultData individualFreeThrowResultData : freeThrowResultBreakdown.getIndividualFreeThrowResultDataList()){
//         if(individualFreeThrowResultData.getPlayPlayer().equals(playTypeInformation.getPlayPlayer())){
//            return individualFreeThrowResultData;
//         }
//      }
//
//      return null;
//   }
//
//   public void populatePossessionResultBreakdown (PlayTracker playTracker,
//                                                  GameResult gameResult,
//                                                  boolean isOpponent,
//                                                  PlayTypeInformationExtractor playTypeInformation,
//                                                  PlayTypeInformationExtractor playTypeInformationNext,
//                                                  PlayTypeInformationExtractor playTypeInformationNextNext,
//                                                  String altPlayType,
//                                                  String timeFrameType,
//                                                  String playType,
//                                                  String offensiveGroupingName,
//                                                  String defensiveGroupingName,
//                                                  BigInteger possessionDifference,
//                                                  BigInteger pointsDifference,
//                                                  BigInteger tempo)
//   {
//
//      final Set<String> REBOUND_ELIGIBLE = Set.of("MISS3JUMP", "MISS2JUMP", "MISS2PTCLOSE");
//
//      List<PossessionResultBreakdown> possessionResultBreakdownList = null;
//
//      if(isOpponent){
//         possessionResultBreakdownList = gameResult.getDefensivePossessionResultBreakdownList();
//      }else{
//         possessionResultBreakdownList = gameResult.getOffensivePossessionResultBreakdownList();
//      }
//
//      if(REBOUND_ELIGIBLE.contains(playType)){
//         playType = "DEADBALL";
//      }
//
//      PossessionResultBreakdown possessionResultBreakdown = extractPossessionResultBreakdown(possessionResultBreakdownList, playType, timeFrameType, possessionDifference, pointsDifference, offensiveGroupingName, defensiveGroupingName);
//      if(possessionResultBreakdown == null) {
//         possessionResultBreakdown = new PossessionResultBreakdown();
//         possessionResultBreakdown.setGameTimeFrame(timeFrameType);
//         possessionResultBreakdown.setPossessionStart(playType);
//         possessionResultBreakdown.setOffensiveGroupingName(offensiveGroupingName);
//         possessionResultBreakdown.setDefensiveGroupingName(defensiveGroupingName);
//         possessionResultBreakdown.setPossessionDifference(possessionDifference);
//         possessionResultBreakdown.setPointsDifference(pointsDifference);
//         possessionResultBreakdown.setTotalOccurrences(BigInteger.ONE);
//
//         PossessionResultData possessionResultData = new PossessionResultData();
//
//         if (altPlayType != null) {
//            possessionResultData.setOverallPossessionResult(altPlayType);
//         } else {
//            possessionResultData.setOverallPossessionResult(playTypeInformation.getOverallPlayType());
//         }
//
//         possessionResultData.setOccurrencePercentage(BigDecimal.ZERO);
//         possessionResultData.setOccurrences(BigInteger.ONE);
//         possessionResultData.getTempos().add(tempo);
//         DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
//         for (BigInteger tempoTime : possessionResultData.getTempos()) {
//            descriptiveStatistics.addValue(tempoTime.doubleValue());
//         }
//         possessionResultData.setTempoAverage(new BigDecimal(descriptiveStatistics.getMean()));
//         possessionResultData.setTempoStDev(new BigDecimal(descriptiveStatistics.getStandardDeviation()));
//
//         IndividualPossessionResultData individualPossessionResultData = new IndividualPossessionResultData();
//
//         if (isOpponent && altPlayType != null) {
//            if(altPlayType.equals("SHOOTINGFOUL")){
//               individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            }else {
//               individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//            }
//         }else if (altPlayType != null && altPlayType.equals("SHOOTINGFOUL")){
//            if(playTypeInformationNext.getOverallPlayType().equals("FREETHROW")){
//               individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//            }else{
//               individualPossessionResultData.setPlayPlayer(playTypeInformationNextNext.getPlayPlayer());
//            }
//         }else {
//            individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//         }
//
//         individualPossessionResultData.setIndividualOccurrences(BigInteger.ONE);
//         individualPossessionResultData.setIndividualOccurrencePercentage(BigDecimal.ZERO);
//
//         playTracker.setOverallPlayResult(possessionResultData.getOverallPossessionResult());
//
//         if (!(!isOpponent && playTypeInformation.getOverallPlayType().equals("FOUL") && altPlayType == null)){
//            possessionResultData.getIndividualPossessionResultDataList().add(individualPossessionResultData);
//            playTracker.setOverallPlayResultIndiv(individualPossessionResultData.getPlayPlayer());
//         }
//
//         possessionResultBreakdown.getPossessionResultDataList().add(possessionResultData);
//         possessionResultBreakdownList.add(possessionResultBreakdown);
//
//      }else{
//         possessionResultBreakdown.setTotalOccurrences(possessionResultBreakdown.getTotalOccurrences().add(BigInteger.ONE));
//         PossessionResultData possessionResultData = null;
//         if(altPlayType != null) {
//            possessionResultData = extractPossessionResultData(possessionResultBreakdown, altPlayType);
//         }else {
//            possessionResultData = extractPossessionResultData(possessionResultBreakdown, playTypeInformation.getOverallPlayType());
//         }
//
//         if(possessionResultData == null){
//            possessionResultData = new PossessionResultData();
//            if(altPlayType != null){
//               possessionResultData.setOverallPossessionResult(altPlayType);
//            }else {
//               possessionResultData.setOverallPossessionResult(playTypeInformation.getOverallPlayType());
//            }
//            possessionResultData.setOccurrencePercentage(BigDecimal.ZERO);
//            possessionResultData.setOccurrences(BigInteger.ONE);
//            possessionResultData.getTempos().add(tempo);
//            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
//            for(BigInteger tempoTime : possessionResultData.getTempos()){
//               descriptiveStatistics.addValue(tempoTime.doubleValue());
//            }
//            possessionResultData.setTempoAverage(new BigDecimal(descriptiveStatistics.getMean()));
//            possessionResultData.setTempoStDev(new BigDecimal(descriptiveStatistics.getStandardDeviation()));
//
//            IndividualPossessionResultData individualPossessionResultData = new IndividualPossessionResultData();
//            if (isOpponent && altPlayType != null) {
//               if(altPlayType.equals("SHOOTINGFOUL")){
//                  individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//               }else {
//                  individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//               }
//            }else if (altPlayType != null && altPlayType.equals("SHOOTINGFOUL")){
//               if(playTypeInformationNext.getOverallPlayType().equals("FREETHROW")){
//                  individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//               }else{
//                  individualPossessionResultData.setPlayPlayer(playTypeInformationNextNext.getPlayPlayer());
//               }
//            }else {
//               individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            }
//            individualPossessionResultData.setIndividualOccurrences(BigInteger.ONE);
//            individualPossessionResultData.setIndividualOccurrencePercentage(BigDecimal.ZERO);
//
//            playTracker.setOverallPlayResult(possessionResultData.getOverallPossessionResult());
//
//
//            if (!(!isOpponent && playTypeInformation.getOverallPlayType().equals("FOUL") && altPlayType == null)){
//               possessionResultData.getIndividualPossessionResultDataList().add(individualPossessionResultData);
//               playTracker.setOverallPlayResultIndiv(individualPossessionResultData.getPlayPlayer());
//            }
//
//            possessionResultBreakdown.getPossessionResultDataList().add(possessionResultData);
//         } else {
//            possessionResultData.setOccurrencePercentage(BigDecimal.ZERO);
//            possessionResultData.setOccurrences(possessionResultData.getOccurrences().add(BigInteger.ONE));
//            possessionResultData.getTempos().add(tempo);
//            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
//            for(BigInteger tempoTime : possessionResultData.getTempos()){
//               descriptiveStatistics.addValue(tempoTime.doubleValue());
//            }
//            possessionResultData.setTempoAverage(new BigDecimal(descriptiveStatistics.getMean()));
//            possessionResultData.setTempoStDev(new BigDecimal(descriptiveStatistics.getStandardDeviation()));
//
//            IndividualPossessionResultData individualPossessionResultData = null;
//
//            if(isOpponent && altPlayType != null) {
//               if(altPlayType.equals("SHOOTINGFOUL")){
//                  individualPossessionResultData = extractIndividualPossessionResultData(possessionResultData, playTypeInformation.getPlayPlayer());
//               }else {
//                  individualPossessionResultData = extractIndividualPossessionResultData(possessionResultData, playTypeInformationNext.getPlayPlayer());
//               }
//            }else if (altPlayType != null && altPlayType.equals("SHOOTINGFOUL")){
//               if(playTypeInformationNext.getOverallPlayType().equals("FREETHROW")){
//                  individualPossessionResultData = extractIndividualPossessionResultData(possessionResultData, playTypeInformationNext.getPlayPlayer());
//               }else{
//                  individualPossessionResultData = extractIndividualPossessionResultData(possessionResultData, playTypeInformationNextNext.getPlayPlayer());
//               }
//            }else {
//               individualPossessionResultData = extractIndividualPossessionResultData(possessionResultData, playTypeInformation.getPlayPlayer());
//            }
//
//            if(individualPossessionResultData == null){
//               individualPossessionResultData = new IndividualPossessionResultData();
//
//               if (isOpponent && altPlayType != null) {
//                  if(altPlayType.equals("SHOOTINGFOUL")){
//                     individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//                  }else {
//                     individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//                  }
//               }else if (altPlayType != null && altPlayType.equals("SHOOTINGFOUL")){
//                  if(playTypeInformationNext.getOverallPlayType().equals("FREETHROW")){
//                     individualPossessionResultData.setPlayPlayer(playTypeInformationNext.getPlayPlayer());
//                  }else{
//                     individualPossessionResultData.setPlayPlayer(playTypeInformationNextNext.getPlayPlayer());
//                  }
//               }else {
//                  individualPossessionResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//               }
//
//               individualPossessionResultData.setIndividualOccurrences(BigInteger.ONE);
//               individualPossessionResultData.setIndividualOccurrencePercentage(BigDecimal.ZERO);
//
//               playTracker.setOverallPlayResult(possessionResultData.getOverallPossessionResult());
//
//               if (!(!isOpponent && playTypeInformation.getOverallPlayType().equals("FOUL") && altPlayType == null)){
//                  possessionResultData.getIndividualPossessionResultDataList().add(individualPossessionResultData);
//                  playTracker.setOverallPlayResultIndiv(individualPossessionResultData.getPlayPlayer());
//               }
//
//            }else{
//               playTracker.setOverallPlayResult(possessionResultData.getOverallPossessionResult());
//               playTracker.setOverallPlayResultIndiv(individualPossessionResultData.getPlayPlayer());
//
//               individualPossessionResultData.setIndividualOccurrences(individualPossessionResultData.getIndividualOccurrences().add(BigInteger.ONE));
//               individualPossessionResultData.setIndividualOccurrencePercentage(BigDecimal.ZERO);
//            }
//         }
//      }
//   }
//
//   public void populatePossessionResultBreakdownSummary (List<PossessionResultBreakdown> overallOffensivePossessionResultBreakdownList,
//                                                         PossessionResultBreakdown possessionResultBreakdown)
//   {
//      PossessionResultBreakdown possessionResultBreakdownSummary = extractPossessionResultBreakdownSummary(overallOffensivePossessionResultBreakdownList, possessionResultBreakdown);
//      if(possessionResultBreakdownSummary == null){
//         possessionResultBreakdownSummary = new PossessionResultBreakdown();
//         possessionResultBreakdownSummary.setGameTimeFrame(possessionResultBreakdown.getGameTimeFrame());
//         possessionResultBreakdownSummary.setPossessionStart(possessionResultBreakdown.getPossessionStart());
//         possessionResultBreakdownSummary.setOffensiveGroupingName(possessionResultBreakdown.getOffensiveGroupingName());
//         possessionResultBreakdownSummary.setDefensiveGroupingName(possessionResultBreakdown.getDefensiveGroupingName());
//         possessionResultBreakdownSummary.setPossessionDifference(possessionResultBreakdown.getPossessionDifference());
//         possessionResultBreakdownSummary.setPointsDifference(possessionResultBreakdown.getPointsDifference());
//         possessionResultBreakdownSummary.setTotalOccurrences(possessionResultBreakdown.getTotalOccurrences());
//
//         overallOffensivePossessionResultBreakdownList.add(possessionResultBreakdownSummary);
//      }else{
//         possessionResultBreakdownSummary.setTotalOccurrences(possessionResultBreakdownSummary.getTotalOccurrences().add(possessionResultBreakdown.getTotalOccurrences()));
//      }
//
//      for(PossessionResultData possessionResultData : possessionResultBreakdown.getPossessionResultDataList()){
//         PossessionResultData possessionResultDataSummary = extractPossessionResultDataSummary(possessionResultBreakdownSummary, possessionResultData);
//         if(possessionResultDataSummary == null){
//            possessionResultDataSummary = new PossessionResultData();
//            possessionResultDataSummary.setOverallPossessionResult(possessionResultData.getOverallPossessionResult());
//            possessionResultDataSummary.setOccurrences(possessionResultData.getOccurrences());
//            possessionResultDataSummary.setTempos(possessionResultData.getTempos());
//            possessionResultDataSummary.setIndividualPossessionResultDataList(possessionResultData.getIndividualPossessionResultDataList());
//            possessionResultDataSummary.setOccurrencePercentage(BigDecimal.ZERO);
//            possessionResultDataSummary.setTempoAverage(BigDecimal.ZERO);
//            possessionResultDataSummary.setTempoStDev(BigDecimal.ZERO);
//
//            possessionResultBreakdownSummary.getPossessionResultDataList().add(possessionResultDataSummary);
//
//         }else{
//            possessionResultDataSummary.setOccurrences(possessionResultDataSummary.getOccurrences().add(possessionResultData.getOccurrences()));
//            for(BigInteger tempo: possessionResultData.getTempos()){
//               possessionResultDataSummary.getTempos().add(tempo);
//            }
//            for(IndividualPossessionResultData individualPossessionResultData : possessionResultData.getIndividualPossessionResultDataList()){
//               IndividualPossessionResultData individualPossessionResultDataSummary = extractIndividualPossessionResultDataSummary(possessionResultDataSummary, individualPossessionResultData);
//               if(individualPossessionResultDataSummary == null){
//                  individualPossessionResultDataSummary = new IndividualPossessionResultData();
//                  individualPossessionResultDataSummary.setPlayPlayer(individualPossessionResultData.getPlayPlayer());
//                  individualPossessionResultDataSummary.setIndividualOccurrences(individualPossessionResultData.getIndividualOccurrences());
//                  individualPossessionResultDataSummary.setIndividualOccurrencePercentage(BigDecimal.ZERO);
//
//                  possessionResultDataSummary.getIndividualPossessionResultDataList().add(individualPossessionResultDataSummary);
//
//               }else{
//                  individualPossessionResultDataSummary.setIndividualOccurrences(individualPossessionResultDataSummary.getIndividualOccurrences().add(individualPossessionResultData.getIndividualOccurrences()));
//               }
//            }
//         }
//      }
//   }
//
//   public void populateShotResultBreakdown (GameResult gameResult,
//                                            boolean isOpponent,
//                                            PlayTypeInformationExtractor playTypeInformation,
//                                            String timeFrameType,
//                                            String playType,
//                                            String offensiveGroupingName,
//                                            String defensiveGroupingName)
//   {
//      List<ShotResultBreakdown> shotResultBreakdownList = null;
//
//      if(isOpponent){
//         shotResultBreakdownList = gameResult.getDefensiveShotResultBreakdownList();
//      }else{
//         shotResultBreakdownList = gameResult.getOffensiveShotResultBreakdownList();
//      }
//
//      ShotResultBreakdown shotResultBreakdown = extractShotResultBreakdown(shotResultBreakdownList, playType, playTypeInformation.getOverallPlayType(), timeFrameType, offensiveGroupingName, defensiveGroupingName);
//      if(shotResultBreakdown == null){
//         shotResultBreakdown = new ShotResultBreakdown();
//         shotResultBreakdown.setGameTimeFrame(timeFrameType);
//         shotResultBreakdown.setPossessionStart(playType);
//         shotResultBreakdown.setOverallPossessionResult(playTypeInformation.getOverallPlayType());
//         shotResultBreakdown.setOffensiveGroupingName(offensiveGroupingName);
//         shotResultBreakdown.setDefensiveGroupingName(defensiveGroupingName);
//         shotResultBreakdown.setTotalOccurrences(BigInteger.ONE);
//
//         if(playTypeInformation.getPlayType().contains("MADE")) {
//            shotResultBreakdown.setTotalSuccesses(BigInteger.ONE);
//         }else{
//            shotResultBreakdown.setTotalSuccesses(BigInteger.ZERO);
//         }
//
//         shotResultBreakdown.setSuccessPercentage(new BigDecimal(shotResultBreakdown.getTotalSuccesses()).divide(new BigDecimal(shotResultBreakdown.getTotalOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//
//         IndividualShotResultData individualShotResultData = new IndividualShotResultData();
//         individualShotResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//         individualShotResultData.setIndividualOccurrences(BigInteger.ONE);
//
//         if(playTypeInformation.getPlayType().contains("MADE")) {
//            individualShotResultData.setIndividualSuccesses(BigInteger.ONE);
//         }else{
//            individualShotResultData.setIndividualSuccesses(BigInteger.ZERO);
//         }
//
//         individualShotResultData.setIndividualSuccessPercentage(new BigDecimal(individualShotResultData.getIndividualSuccesses()).divide(new BigDecimal(individualShotResultData.getIndividualOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//
//         shotResultBreakdown.getIndividualShotResultDataList().add(individualShotResultData);
//         shotResultBreakdownList.add(shotResultBreakdown);
//
//      }else{
//
//         shotResultBreakdown.setTotalOccurrences(shotResultBreakdown.getTotalOccurrences().add(BigInteger.ONE));
//
//         if(playTypeInformation.getPlayType().contains("MADE")) {
//            shotResultBreakdown.setTotalSuccesses( shotResultBreakdown.getTotalSuccesses().add(BigInteger.ONE));
//         }
//
//         shotResultBreakdown.setSuccessPercentage(new BigDecimal(shotResultBreakdown.getTotalSuccesses()).divide(new BigDecimal(shotResultBreakdown.getTotalOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//
//         IndividualShotResultData individualShotResultData = extractIndividualShotResultData(shotResultBreakdown, playTypeInformation);
//         if(individualShotResultData == null) {
//            individualShotResultData = new IndividualShotResultData();
//            individualShotResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            individualShotResultData.setIndividualOccurrences(BigInteger.ONE);
//
//            if (playTypeInformation.getPlayType().contains("MADE")) {
//               individualShotResultData.setIndividualSuccesses(BigInteger.ONE);
//            } else {
//               individualShotResultData.setIndividualSuccesses(BigInteger.ZERO);
//            }
//
//            individualShotResultData.setIndividualSuccessPercentage(new BigDecimal(individualShotResultData.getIndividualSuccesses()).divide(new BigDecimal(individualShotResultData.getIndividualOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//
//            shotResultBreakdown.getIndividualShotResultDataList().add(individualShotResultData);
//         }else{
//            individualShotResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            individualShotResultData.setIndividualOccurrences(individualShotResultData.getIndividualOccurrences().add(BigInteger.ONE));
//
//            if (playTypeInformation.getPlayType().contains("MADE")) {
//               individualShotResultData.setIndividualSuccesses(individualShotResultData.getIndividualSuccesses().add(BigInteger.ONE));
//            }
//
//            individualShotResultData.setIndividualSuccessPercentage(new BigDecimal(individualShotResultData.getIndividualSuccesses()).divide(new BigDecimal(individualShotResultData.getIndividualOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//         }
//      }
//   }
//
//   public void populateReboundResultBreakdown (GameResult gameResult,
//                                               boolean isOpponent,
//                                               PlayTypeInformationExtractor playTypeInformation,
//                                               String playTypeInformationPast,
//                                               String timeFrameType,
//                                               String priorPlayType,
//                                               String offensiveGroupingName,
//                                               String defensiveGroupingName,
//                                               BigInteger tempo)
//   {
//      List<ReboundResultBreakdown> reboundResultBreakdownList = null;
//
//      if(isOpponent) {
//         if (playTypeInformation.getPlayType().equals("DEFREB")) {
//            reboundResultBreakdownList = gameResult.getOffensiveReboundResultBreakdownList();
//         } else {
//            reboundResultBreakdownList = gameResult.getDefensiveReboundResultBreakdownList();
//         }
//      }else{
//         if (playTypeInformation.getPlayType().equals("DEFREB")) {
//            reboundResultBreakdownList = gameResult.getDefensiveReboundResultBreakdownList();
//         } else {
//            reboundResultBreakdownList = gameResult.getOffensiveReboundResultBreakdownList();
//         }
//      }
//
//      String[] playTypeInformationBreakdown = playTypeInformationPast.split("_");
//
//      ReboundResultBreakdown reboundResultBreakdown = extractReboundResultBreakdown(reboundResultBreakdownList, playTypeInformationBreakdown[0], playTypeInformationBreakdown[1], timeFrameType, offensiveGroupingName, defensiveGroupingName);
//      if(reboundResultBreakdown == null){
//         reboundResultBreakdown = new ReboundResultBreakdown();
//         reboundResultBreakdown.setGameTimeFrame(timeFrameType);
//         reboundResultBreakdown.setPossessionStart(playTypeInformationBreakdown[0]);
//         reboundResultBreakdown.setOverallPossessionResult(playTypeInformationBreakdown[1]);
//         reboundResultBreakdown.setOffensiveGroupingName(offensiveGroupingName);
//         reboundResultBreakdown.setDefensiveGroupingName(defensiveGroupingName);
//         reboundResultBreakdown.setTotalOccurrences(BigInteger.ONE);
//         if(isOpponent) {
//            reboundResultBreakdown.setTotalRebounds(BigInteger.ZERO);
//         }else{
//            reboundResultBreakdown.setTotalRebounds(BigInteger.ONE);
//            reboundResultBreakdown.getTempos().add(tempo);
//
//            IndividualReboundResultData individualReboundResultData = new IndividualReboundResultData();
//            individualReboundResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            individualReboundResultData.setIndividualRebounds(BigInteger.ONE);
//            individualReboundResultData.setIndividualReboundPercentage(BigDecimal.ONE);
//
//            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
//            for(BigInteger tempoTime : reboundResultBreakdown.getTempos()){
//               descriptiveStatistics.addValue(tempoTime.doubleValue());
//            }
//            reboundResultBreakdown.setTempoAverage(new BigDecimal(descriptiveStatistics.getMean()));
//            reboundResultBreakdown.setTempoStDev(new BigDecimal(descriptiveStatistics.getStandardDeviation()));
//
//            reboundResultBreakdown.getIndividualReboundResultDataList().add(individualReboundResultData);
//
//         }
//         reboundResultBreakdown.setReboundPercentage(new BigDecimal(reboundResultBreakdown.getTotalRebounds()).divide(new BigDecimal(reboundResultBreakdown.getTotalOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//         reboundResultBreakdownList.add(reboundResultBreakdown);
//      }else{
//         reboundResultBreakdown.setTotalOccurrences(reboundResultBreakdown.getTotalOccurrences().add(BigInteger.ONE));
//         if(!isOpponent) {
//            reboundResultBreakdown.setTotalRebounds(reboundResultBreakdown.getTotalRebounds().add(BigInteger.ONE));
//            reboundResultBreakdown.getTempos().add(tempo);
//
//            IndividualReboundResultData individualReboundResultData = extractIndividualReboundResultData(reboundResultBreakdown, playTypeInformation);
//            if(individualReboundResultData == null){
//               individualReboundResultData = new IndividualReboundResultData();
//               individualReboundResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//               individualReboundResultData.setIndividualRebounds(BigInteger.ONE);
//               individualReboundResultData.setIndividualReboundPercentage( new BigDecimal(individualReboundResultData.getIndividualRebounds()).divide(new BigDecimal(reboundResultBreakdown.getTotalRebounds()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//
//               reboundResultBreakdown.getIndividualReboundResultDataList().add(individualReboundResultData);
//            }else{
//               individualReboundResultData.setIndividualRebounds(individualReboundResultData.getIndividualRebounds().add(BigInteger.ONE));
//               individualReboundResultData.setIndividualReboundPercentage( new BigDecimal(individualReboundResultData.getIndividualRebounds()).divide(new BigDecimal(reboundResultBreakdown.getTotalRebounds()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//            }
//
//            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
//            for(BigInteger tempoTime : reboundResultBreakdown.getTempos()){
//               descriptiveStatistics.addValue(tempoTime.doubleValue());
//            }
//
//            reboundResultBreakdown.setTempoAverage(new BigDecimal(descriptiveStatistics.getMean()));
//            reboundResultBreakdown.setTempoStDev(new BigDecimal(descriptiveStatistics.getStandardDeviation()));
//         }
//         reboundResultBreakdown.setReboundPercentage(new BigDecimal(reboundResultBreakdown.getTotalRebounds()).divide(new BigDecimal(reboundResultBreakdown.getTotalOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//      }
//   }
//
//   public void populateFreeThrowResultBreakdown (GameResult gameResult,
//                                                 boolean isOpponent,
//                                                 PlayTypeInformationExtractor playTypeInformation)
//   {
//      if(!isOpponent) {
//         FreeThrowResultBreakdown freeThrowResultBreakdown = gameResult.getFreeThrowResultBreakdown();
//         freeThrowResultBreakdown.setTotalOccurrences(freeThrowResultBreakdown.getTotalOccurrences().add(BigInteger.ONE));
//         if (playTypeInformation.getPlayType().equals("MADEFREE")) {
//            freeThrowResultBreakdown.setTotalSuccesses(freeThrowResultBreakdown.getTotalSuccesses().add(BigInteger.ONE));
//         }
//         IndividualFreeThrowResultData individualFreeThrowResultData = extractIndividualFreeThrowResultData(freeThrowResultBreakdown, playTypeInformation);
//         if (individualFreeThrowResultData == null) {
//            individualFreeThrowResultData = new IndividualFreeThrowResultData();
//            individualFreeThrowResultData.setPlayPlayer(playTypeInformation.getPlayPlayer());
//            individualFreeThrowResultData.setIndividualOccurrences(BigInteger.ONE);
//            if (playTypeInformation.getPlayType().equals("MADEFREE")) {
//               individualFreeThrowResultData.setIndividualSuccesses(BigInteger.ONE);
//            } else {
//               individualFreeThrowResultData.setIndividualSuccesses(BigInteger.ZERO);
//            }
//            freeThrowResultBreakdown.getIndividualFreeThrowResultDataList().add(individualFreeThrowResultData);
//         } else {
//            individualFreeThrowResultData.setIndividualOccurrences(individualFreeThrowResultData.getIndividualOccurrences().add(BigInteger.ONE));
//            if (playTypeInformation.getPlayType().equals("MADEFREE")) {
//               individualFreeThrowResultData.setIndividualSuccesses(individualFreeThrowResultData.getIndividualSuccesses().add(BigInteger.ONE));
//            }
//         }
//      }
//   }
//
//   public void updatePossessionPercentages (List<PossessionResultBreakdown> possessionResultBreakdownList)
//   {
//      for(PossessionResultBreakdown possessionResultBreakdown : possessionResultBreakdownList){
//         for(PossessionResultData possessionResultData : possessionResultBreakdown.getPossessionResultDataList()){
//            possessionResultData.setOccurrencePercentage(new BigDecimal(possessionResultData.getOccurrences()).divide(new BigDecimal(possessionResultBreakdown.getTotalOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//            for(IndividualPossessionResultData individualPossessionResultData : possessionResultData.getIndividualPossessionResultDataList()){
//               individualPossessionResultData.setIndividualOccurrencePercentage(new BigDecimal(individualPossessionResultData.getIndividualOccurrences()).divide(new BigDecimal(possessionResultData.getOccurrences()), DECIMAL_SCALE, RoundingMode.HALF_UP));
//            }
//         }
//      }
//   }
//
////   public EspnPlayByPlay fixRebRebIssue (NcaaTeam ncaaTeam,
////                                         GameResult gameResult,
////                                         EspnPlayByPlay pbpPast,
////                                         EspnPlayByPlay pbp,
////                                         PlayTypeInformationExtractor playTypeInformationPast,
////                                         PlayTypeInformationExtractor playTypeInformation)
////   {
////      EspnPlayByPlay newPbp = new EspnPlayByPlay();
////
////      boolean isOpponent = espnPlayByPlayExtractionService.extractOpponent(pbpPast.getTeamLogo(), ncaaTeam.getTeamId());
////
////      newPbp.setTeamLogo(pbpPast.getTeamLogo());
////      newPbp.setCombinedScore(pbpPast.getCombinedScore());
////      newPbp.setGameHalf(pbpPast.getGameHalf());
////      newPbp.setOffTeam(pbpPast.getOffTeam());
////
////      if(isOpponent){
////         newPbp.setGameDetail(gameResult.getOpponentName() + " missed Jumper.");
////      }else{
////         newPbp.setGameDetail(ncaaTeam.getTeamName() + " missed Jumper.");
////      }
////      newPbp.setTimeStamp(pbp.getTimeStamp());
////
////      return newPbp;
////   }
//
////   public EspnPlayByPlay fixNotRebEligRebIssue (NcaaTeam ncaaTeam,
////                                                GameResult gameResult,
////                                                EspnPlayByPlay pbpPast,
////                                                EspnPlayByPlay pbp,
////                                                PlayTypeInformationExtractor playTypeInformationPast,
////                                                PlayTypeInformationExtractor playTypeInformation)
////   {
////
////      EspnPlayByPlay newPbp = new EspnPlayByPlay();
////
////      boolean isOpponent = espnPlayByPlayExtractionService.extractOpponent(pbpPast.getTeamLogo(), ncaaTeam.getTeamId());
////      newPbp.setTeamLogo(pbp.getTeamLogo());
////      newPbp.setCombinedScore(pbp.getCombinedScore());
////      newPbp.setGameHalf(pbp.getGameHalf());
////      newPbp.setOffTeam(pbp.getOffTeam());
////
////      if(isOpponent){
////         newPbp.setGameDetail(ncaaTeam.getTeamName() + " missed Jumper.");
////      }else{
////         newPbp.setGameDetail(gameResult.getOpponentName() + " missed Jumper.");
////      }
////
////      newPbp.setTimeStamp(pbp.getTimeStamp());
////
////      return newPbp;
////   }
////
////   public EspnPlayByPlay fixMissNotReboundIssue (NcaaTeam ncaaTeam,
////                                                 GameResult gameResult,
////                                                 EspnPlayByPlay pbpPast,
////                                                 EspnPlayByPlay pbp,
////                                                 PlayTypeInformationExtractor playTypeInformationPast,
////                                                 PlayTypeInformationExtractor playTypeInformation)
////   {
////      EspnPlayByPlay newPbp = new EspnPlayByPlay();
////
////      boolean isOpponentPast = espnPlayByPlayExtractionService.extractOpponent(pbpPast.getTeamLogo(), ncaaTeam.getTeamId());
////      boolean isOpponentCurrent = espnPlayByPlayExtractionService.extractOpponent(pbpPast.getTeamLogo(), ncaaTeam.getTeamId());
////
////      newPbp.setTeamLogo(pbp.getTeamLogo());
////      newPbp.setCombinedScore(pbp.getCombinedScore());
////      newPbp.setGameHalf(pbp.getGameHalf());
////      newPbp.setOffTeam(pbp.getOffTeam());
////
////      if(isOpponentPast == isOpponentCurrent){
////         if(isOpponentPast){
////            newPbp.setGameDetail(gameResult.getOpponentName() + " Unknown Offensive Rebound.");
////         }else{
////            newPbp.setGameDetail(ncaaTeam.getTeamName() + " Unknown Defensive Rebound.");
////         }
////      }else{
////         if(isOpponentPast){
////            newPbp.setGameDetail(ncaaTeam.getTeamName() + " Unknown Defensive Rebound.");
////         }else{
////            newPbp.setGameDetail(gameResult.getOpponentName() + " Unknown Offensive Rebound.");
////         }
////      }
////
////      newPbp.setTimeStamp(pbpPast.getTimeStamp());
////      return newPbp;
////   }


}
