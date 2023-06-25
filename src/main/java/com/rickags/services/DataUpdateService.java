package com.rickags.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.mappers.JsonMapper;

@Component
public class DataUpdateService
{
   @Autowired
   protected UnirestApiService unirestApiService;

   @Autowired
   protected UnirestApiServiceHelper unirestApiServiceHelper;

   @Autowired
   protected JsonMapper jsonMapper;

   @Autowired
   protected HtmlExtractionService htmlExtractionService;

   @Autowired
   protected EspnPlayByPlayExtractionService espnPlayByPlayExtractionService;


//   public void pullAllScheduleDataInformation (RequestContext ncaabbRequest,
//                                               ResponseContext ncaabbResponse)
//      throws IOException, UnirestException
//   {
//      LocalDate endDate = LocalDate.now();
//      LocalDate startDate = ncaabbRequest.getStartDate();
//
//      while (!startDate.equals(endDate)) {
//         unirestApiService.setUpUnirest();
//         String url = "http://cdn.espn.com/core/mens-college-basketball/scoreboard/_/group/50/date/" + startDate.getYear() + String.format("%02d", startDate.getMonthValue()) + String.format("%02d", startDate.getDayOfMonth()) + "?xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full";
//         EspnScoreboard espnScoreboard = unirestApiService.getWebsiteDataObject(url, null, null, EspnScoreboard.class);
//         unirestApiService.shutdownUnirest();
//
//         List<EspnEvent> espnEventsList = espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents();
//         // if no games skip to next day
//         if (espnEventsList.size() != 0) {
//            for (EspnEvent espnEvent : espnEventsList) {
//               //collect data if status is final
//               if ("STATUS_FINAL".equals(espnEvent.getEspnStatus().getEspnType().getName())) {
//                  String eventId = espnEvent.getId();
//                  String eventVenueId = espnEvent.getEspnCompetitions().get(0).getEspnVenue().getId();
//                  String eventTeam1Score = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getScore();
//                  String eventTeam2Score = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getScore();
//
//                  String team1Id = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getId();
//                  String team1Name = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getLocation();
//                  String team1Conference = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId();
//                 // String team1ArenaId = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getEspnVenue().getId();
//
//                  String team2Id = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getId();
//                  String team2Name = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getLocation();
//                  String team2Conference = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getConferenceId();
//                  boolean conferenceGame = espnEvent.getEspnCompetitions().get(0).isConferenceCompetition();
//                 // String team2ArenaId = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getEspnVenue().getId();
//
//                  if(team1Conference != null && team2Conference != null) {
//                     //see if json files are already available
//                     //create if not available
//                     //pull in and update otherwise
//
//                     File file1 = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\" + team1Id + ".json");
//
//                     if (file1.exists()) {
//
//                        String json = null;
//                        //Instantiating the FileInputStream class
//                        try(FileInputStream fileIn = new FileInputStream(file1)) {
//                           //Instantiating the DataInputStream class
//                           try(DataInputStream inputStream = new DataInputStream(fileIn)) {
//                              //Reading UTF data from the DataInputStream
//                              json = new String(inputStream.readAllBytes(),"UTF-8");
//                           }
//                        }
//                      //  String json = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath())));
//                        NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//
//                        String year = "";
//                        if (startDate.getMonthValue() < 5) {
//                           year = String.valueOf(startDate.getYear() - 1);
//                        } else {
//                           year = String.valueOf(startDate.getYear());
//                        }
//
//                        //check if year is created and create if it is
//                        YearSummary yearSummary = dataUpdateServiceHelper.extractYearSummary(ncaaTeam, year);
//                        if(yearSummary != null){
//                           GameResult gameResult = dataUpdateServiceHelper.extractGameResult(yearSummary, eventId);
//                           if(gameResult == null){
//                              //create gameResult
//                              gameResult = new GameResult();
//
//                              gameResult.setGameDate(startDate);
//                              gameResult.setGameId(eventId);
//                              gameResult.setVenueId(eventVenueId);
//                              gameResult.setOpponentId(team2Id);
//                              gameResult.setOpponentName(team2Name);
//                              gameResult.setOpponentConferenceId(team2Conference);
//                              gameResult.setConferenceGame(conferenceGame);
//                              gameResult.setDesignatedHost(true);
//
//                              yearSummary.getGameResults().add(gameResult);
//                           }
//                        }else{
//                           // create everything
//                           yearSummary = new YearSummary();
//                           yearSummary.setYear(year);
//                           yearSummary.setConferenceId(team1Conference);
//                           GameResult gameResult = new GameResult();
//
//                           gameResult.setGameDate(startDate);
//                           gameResult.setGameId(eventId);
//                           gameResult.setVenueId(eventVenueId);
//                           gameResult.setOpponentId(team2Id);
//                           gameResult.setOpponentName(team2Name);
//                           gameResult.setOpponentConferenceId(team2Conference);
//                           gameResult.setConferenceGame(conferenceGame);
//                           gameResult.setDesignatedHost(true);
//
//                           yearSummary.getGameResults().add(gameResult);
//                           ncaaTeam.getYearSummaries().add(yearSummary);
//                        }
//
//                        try (FileOutputStream output = new FileOutputStream(file1)){
//                           try(DataOutputStream outputStream = new DataOutputStream(output)) {
//                              outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                              outputStream.flush();
//                           }
//                        }
//                     } else {
//                        System.out.println("creating file for " + team1Name);
//                        NcaaTeam ncaaTeam = new NcaaTeam();
//                        ncaaTeam.setTeamId(team1Id);
//                        ncaaTeam.setTeamName(team1Name);
//
//                        YearSummary yearSummary = new YearSummary();
//
//                        if (startDate.getMonthValue() < 5) {
//                           yearSummary.setYear(String.valueOf(startDate.getYear() - 1));
//                        } else {
//                           yearSummary.setYear(String.valueOf(startDate.getYear()));
//                        }
//
//                        yearSummary.setConferenceId(team1Conference);
//
//                        GameResult gameResult = new GameResult();
//
//                        gameResult.setGameDate(startDate);
//                        gameResult.setGameId(eventId);
//                        gameResult.setVenueId(eventVenueId);
//                        gameResult.setOpponentId(team2Id);
//                        gameResult.setOpponentName(team2Name);
//                        gameResult.setOpponentConferenceId(team2Conference);
//                        gameResult.setConferenceGame(conferenceGame);
//                        gameResult.setDesignatedHost(true);
//
//                        yearSummary.getGameResults().add(gameResult);
//                        ncaaTeam.getYearSummaries().add(yearSummary);
//
//                        try (FileOutputStream output = new FileOutputStream(file1)){
//                           try(DataOutputStream outputStream = new DataOutputStream(output)) {
//                              outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                              outputStream.flush();
//                           }
//                        }
//                     }
//
//                     File file2 = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\" + team2Id + ".json");
//
//                     if (file2.exists()) {
//
//                        String json = null;
//                        //Instantiating the FileInputStream class
//                        try(FileInputStream fileIn = new FileInputStream(file2)) {
//                           //Instantiating the DataInputStream class
//                           try(DataInputStream inputStream = new DataInputStream(fileIn)) {
//                              //Reading UTF data from the DataInputStream
//                              json = new String(inputStream.readAllBytes(),"UTF-8");
//                           }
//                        }
//
//                        //String json = new String(Files.readAllBytes(Paths.get(file2.getAbsolutePath())));
//                        NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//
//                        String year = "";
//                        if (startDate.getMonthValue() < 5) {
//                           year = String.valueOf(startDate.getYear() - 1);
//                        } else {
//                           year = String.valueOf(startDate.getYear());
//                        }
//
//                        //check if year is created and create if it is
//                        YearSummary yearSummary = dataUpdateServiceHelper.extractYearSummary(ncaaTeam, year);
//                        if(yearSummary != null){
//                           GameResult gameResult = dataUpdateServiceHelper.extractGameResult(yearSummary, eventId);
//                           if(gameResult == null){
//                              //create gameResult
//                              gameResult = new GameResult();
//
//                              gameResult.setGameDate(startDate);
//                              gameResult.setGameId(eventId);
//                              gameResult.setVenueId(eventVenueId);
//                              gameResult.setOpponentId(team1Id);
//                              gameResult.setOpponentName(team1Name);
//                              gameResult.setOpponentConferenceId(team1Conference);
//                              gameResult.setConferenceGame(conferenceGame);
//                              gameResult.setDesignatedHost(true);
//
//                              yearSummary.getGameResults().add(gameResult);
//                           }
//                        }else{
//                           // create everything
//                           yearSummary = new YearSummary();
//                           yearSummary.setYear(year);
//                           yearSummary.setConferenceId(team2Conference);
//                           GameResult gameResult = new GameResult();
//
//                           gameResult.setGameDate(startDate);
//                           gameResult.setGameId(eventId);
//                           gameResult.setVenueId(eventVenueId);
//                           gameResult.setOpponentId(team1Id);
//                           gameResult.setOpponentName(team1Name);
//                           gameResult.setOpponentConferenceId(team1Conference);
//                           gameResult.setConferenceGame(conferenceGame);
//                           gameResult.setDesignatedHost(true);
//
//                           yearSummary.getGameResults().add(gameResult);
//                           ncaaTeam.getYearSummaries().add(yearSummary);
//                        }
//
//                        try (FileOutputStream output = new FileOutputStream(file2)){
//                           try(DataOutputStream outputStream = new DataOutputStream(output)) {
//                              outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                              outputStream.flush();
//                           }
//                        }
//                     } else {
//                        System.out.println("creating file for " + team2Name);
//                        NcaaTeam ncaaTeam = new NcaaTeam();
//                        ncaaTeam.setTeamId(team2Id);
//                        ncaaTeam.setTeamName(team2Name);
//
//                        YearSummary yearSummary = new YearSummary();
//
//                        if (startDate.getMonthValue() < 5) {
//                           yearSummary.setYear(String.valueOf(startDate.getYear() - 1));
//                        } else {
//                           yearSummary.setYear(String.valueOf(startDate.getYear()));
//                        }
//
//                        yearSummary.setConferenceId(team2Conference);
//
//                        GameResult gameResult = new GameResult();
//
//                        gameResult.setGameDate(startDate);
//                        gameResult.setGameId(eventId);
//                        gameResult.setVenueId(eventVenueId);
//                        gameResult.setOpponentId(team1Id);
//                        gameResult.setOpponentName(team1Name);
//                        gameResult.setOpponentConferenceId(team1Conference);
//                        gameResult.setConferenceGame(conferenceGame);
//                        gameResult.setDesignatedHost(true);
//
//                        yearSummary.getGameResults().add(gameResult);
//                        ncaaTeam.getYearSummaries().add(yearSummary);
//
//                        try (FileOutputStream output = new FileOutputStream(file2)){
//                           try(DataOutputStream outputStream = new DataOutputStream(output)) {
//                              outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                              outputStream.flush();
//                           }
//                        }
//                     }
//                  }
//               }
//            }
//         }
//         startDate = startDate.plusDays(1);
//      }
//   }
//
//   public void pullAllPlayByPlayDataInformation (RequestContext ncaabbRequest,
//                                                  ResponseContext ncaabbResponse)
//      throws Exception
//   {
//      Collection<File> filesInFolder = FileUtils.listFiles(new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\"), null, false);
//
//      File resultMakeupFile = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\makeup.json");
//      List<PlayTracker> playTrackers = new ArrayList<>();
//
//      final Set<String> POSSESSION_VALUES = Set.of("2PTJUMPATTEMPT", "2PTCLOSEATTEMPT", "3PTATTEMPT", "TURNOVER", "STEAL", "FOUL");
//      final Set<String> SHOT_VALUES = Set.of("2PTJUMPATTEMPT", "2PTCLOSEATTEMPT", "3PTATTEMPT");
//      final Set<String> REBOUND_ELIGIBLE = Set.of("MISS3JUMP","MISS2JUMP", "MISS2PTCLOSE");
//      final Set<String> REBOUND_VALUES = Set.of("OFFREB", "DEFREB");
//      final Set<String> FREETHROW_VALUES = Set.of("MISSFREE", "MADEFREE");
//
//      for (File file : filesInFolder) {
//
//         String fileName = file.getAbsolutePath();
//
//         String json = null;
//         //Instantiating the FileInputStream class
//         try(FileInputStream fileIn = new FileInputStream(fileName)) {
//            //Instantiating the DataInputStream class
//            try(DataInputStream inputStream = new DataInputStream(fileIn)) {
//               //Reading UTF data from the DataInputStream
//               json = new String(inputStream.readAllBytes(),"UTF-8");
//            }
//         }
//
//         NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//         String teamNumber = ncaaTeam.getTeamId();
//
//         for (YearSummary yearSummary : ncaaTeam.getYearSummaries()) {
//            for (GameResult gameResult : yearSummary.getGameResults()) {
//               if (gameResult.getOffensivePossessionResultBreakdownList().size() == 0) {
//                  boolean isHost = gameResult.getDesignatedHost();
//                  BigInteger priorTimeSeconds = new BigInteger("2400");
//                  EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml(gameResult.getGameId());
//                  Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();
//                  int opponentFouls = 0;
//                  int primaryFouls = 0;
//                  boolean previousPlay1st = true;
//                  String groupingName = "ALL";
//                  String priorPlayType = "DEADBALL";
//                  BigInteger priorPossessionDifference = BigInteger.ZERO;
//                  BigInteger priorPointsDifference = BigInteger.ZERO;
//                  String priorTimeFrameType = "REGGAME";
//                  EspnPlayByPlay pbp = null;
//                  EspnPlayByPlay pbpNext = null;
//                  EspnPlayByPlay pbpNextNext = null;
//                  EspnPlayByPlay pbpPast = null;
//                  String playTypeInformationPastScenario = null;
//                  PlayTypeInformationExtractor playTypeInformationNext = null;
//                  PlayTypeInformationExtractor playTypeInformation = null;
//                  PlayTypeInformationExtractor playTypeInformationNextNext = null;
//                  PlayTypeInformationExtractor playTypeInformationFake = null;
//                  PlayTypeInformationExtractor playTypeInformationPast = null;
//
//                  for(int x = 0; x < playByPlaySize; ++x) {
//
//                     int initialX = x;
//                     PlayTracker playTracker = null;
//                     String altPlayType = null;
//
//                     System.out.println("play " + x);
//
//                     pbp = espnGamePlayByPlay.getEspnPlayByPlays().get(x);
//
//                     if(x + 1 < playByPlaySize){
//                        pbpNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x+1);
//                     }else{
//                        pbpNext = null;
//                     }
//
//                     if(x + 2 < playByPlaySize){
//                        pbpNextNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x+2);
//                     }else{
//                        pbpNextNext = null;
//                     }
//
//                     playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp.getGameDetail());
//
//                     if(playTypeInformationPast != null){
//                        // validate pbp with pbpPast and figure out if it needs something inbetween
//                        if(REBOUND_ELIGIBLE.contains(playTypeInformationPast.getPlayType()) && !(REBOUND_VALUES.contains(playTypeInformation.getPlayType()) || playTypeInformation.getOverallPlayType().equals("EXTRAPARTS") || playTypeInformation.getPlayType().equals("SKIP"))){
//                           if(REBOUND_ELIGIBLE.contains(playTypeInformation.getPlayType())) {
//                              pbpPast = espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1);
//                              pbp = dataUpdateServiceHelper.fixMissNotReboundIssue(ncaaTeam, gameResult, pbpPast, pbp, playTypeInformationPast, playTypeInformation);
//                              playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp.getGameDetail());
//                              --x;
//                           }else if(FREETHROW_VALUES.contains(playTypeInformation.getPlayType())){
//
//
//                           }else{
//                              throw new Exception("we got some trouble, fuckers");
//                           }
//                        }
//                        if(REBOUND_VALUES.contains(playTypeInformationPast.getPlayType()) && REBOUND_VALUES.contains(playTypeInformation.getPlayType())){
//                           pbpPast = espnGamePlayByPlay.getEspnPlayByPlays().get(x-1);
//                           pbp = dataUpdateServiceHelper.fixRebRebIssue(ncaaTeam, gameResult, pbpPast, pbp, playTypeInformationPast, playTypeInformation);
//                           playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp.getGameDetail());
//                           --x;
//                        }
//                        if(!(REBOUND_ELIGIBLE.contains(playTypeInformationPast.getPlayType())
//                           || playTypeInformationPast.getPlayType().equals("MISSFREE")
//                           || playTypeInformationPast.getPlayType().equals("SKIP")) && REBOUND_VALUES.contains(playTypeInformation.getPlayType())){
//                           pbpPast = espnGamePlayByPlay.getEspnPlayByPlays().get(x-1);
//                           pbp = dataUpdateServiceHelper.fixNotRebEligRebIssue(ncaaTeam, gameResult, pbpPast, pbp, playTypeInformationPast, playTypeInformation);
//                           playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp.getGameDetail());
//                           --x;
//                        }
//                     }
//
//                     BigInteger playTimeSeconds = espnPlayByPlayExtractionService.extractTime(pbp.getTimeStamp(), pbp.getGameHalf());
//
//                     if (!pbp.getGameHalf().equals("1") && !pbp.getGameHalf().equals("2") && priorTimeSeconds.equals(BigInteger.ZERO)) {
//                        priorTimeSeconds = new BigInteger("300");
//                     }
//
//                     BigInteger tempo = priorTimeSeconds.subtract(playTimeSeconds);
//                     priorTimeSeconds = playTimeSeconds;
//                     boolean isOpponent = espnPlayByPlayExtractionService.extractOpponent(pbp.getTeamLogo(), teamNumber);
//
//                     if(playTypeInformation.getPlayType().equals("TURNOVER") || playTypeInformation.getPlayType().equals("FOUL")) {
//                        if(pbpNext != null) {
//                           BigInteger playTimeSecondsNext = espnPlayByPlayExtractionService.extractTime(pbpNext.getTimeStamp(), pbpNext.getGameHalf());
//                           playTypeInformationNext = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbpNext.getGameDetail());
//
//                           if (playTypeInformationNext.getPlayType().equals("STEAL") && playTimeSeconds.equals(playTimeSecondsNext)) {
//                              ++x;
//                              altPlayType = "STEAL";
//                           }
//
//                           if (playTypeInformation.getPlayType().equals("FOUL")) {
//                              if (!pbpNext.getGameHalf().equals("1")) {
//                                 if (previousPlay1st) {
//                                    previousPlay1st = false;
//                                    opponentFouls = 0;
//                                    primaryFouls = 0;
//                                 }
//                              }
//
//                              if (playTypeInformationNext.getPlayType().equals("TURNOVER") && playTimeSeconds.equals(playTimeSecondsNext)) {
//                                 ++x;
//                                 altPlayType = "TURNOVER";
//                              } else {
//                                 playTypeInformationNextNext = null;
//                                 if(pbpNextNext != null) {
//                                     playTypeInformationNextNext = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbpNextNext.getGameDetail());
//                                 }
//                                 if (isOpponent) {
//                                    ++opponentFouls;
//                                    if (opponentFouls < 7 && playTypeInformationNext.getOverallPlayType().equals("FREETHROW")) {
//                                       altPlayType = "SHOOTINGFOUL";
//                                    } else if (opponentFouls < 7 && playTypeInformationNext.getPlayType().equals("TIMEOUT")) {
//                                       if(pbpNextNext != null) {
//                                          if (playTypeInformationNextNext.getOverallPlayType().equals("FREETHROW")) {
//                                             altPlayType = "SHOOTINGFOUL";
//                                          }
//                                       }
//                                    } else if (tempo.equals(BigInteger.ZERO) && priorPlayType.equals("DEADBALL")) {
//                                       altPlayType = "SHOOTINGFOUL";
//                                    }
//                                 } else {
//                                    ++primaryFouls;
//                                    if (primaryFouls < 7 && playTypeInformationNext.getOverallPlayType().equals("FREETHROW")) {
//                                       altPlayType = "SHOOTINGFOUL";
//                                    } else if (primaryFouls < 7 && playTypeInformationNext.getPlayType().equals("TIMEOUT")) {
//                                       if(pbpNextNext != null) {
//                                          if (playTypeInformationNextNext.getOverallPlayType().equals("FREETHROW")) {
//                                             altPlayType = "SHOOTINGFOUL";
//                                          }
//                                       }
//                                    } else if (tempo.equals(BigInteger.ZERO) && priorPlayType.equals("DEADBALL")) {
//                                       altPlayType = "SHOOTINGFOUL";
//                                    }
//                                 }
//                                 isOpponent = !isOpponent;
//                              }
//                           }
//                        }
//                     }
//
//                     if (playTypeInformation.getOverallPlayType().equals("FREETHROW")) {
//                        priorPlayType = "FOUL";
//                     }
//
//                     playTracker = new PlayTracker(initialX, pbp, pbpNext, pbpNextNext, playTypeInformation, playTypeInformationNext, playTypeInformationNextNext, isOpponent, altPlayType, priorTimeFrameType, priorPlayType, groupingName, groupingName, priorPossessionDifference, priorPointsDifference, tempo);
//
//                     if(!playTypeInformation.getOverallPlayType().equals("EXTRAPARTS")){
//                        if(POSSESSION_VALUES.contains(playTypeInformation.getOverallPlayType())) {
//                           dataUpdateServiceHelper.populatePossessionResultBreakdown(playTracker, gameResult, isOpponent, playTypeInformation, playTypeInformationNext, playTypeInformationNextNext, altPlayType, priorTimeFrameType, priorPlayType, groupingName, groupingName, priorPossessionDifference, priorPointsDifference, tempo);
//                           if (SHOT_VALUES.contains(playTypeInformation.getOverallPlayType())) {
//                              dataUpdateServiceHelper.populateShotResultBreakdown(gameResult, isOpponent, playTypeInformation, priorTimeFrameType, priorPlayType, groupingName, groupingName);
//                           }
//                           if(REBOUND_ELIGIBLE.contains(playTypeInformation.getPlayType())) {
//                              playTypeInformationPastScenario = priorPlayType + "_" + playTypeInformation.getOverallPlayType();
//                              playTracker.setPlayTypeInformationPast(playTypeInformationPastScenario);
//                           }
//                        }
//                        if(REBOUND_VALUES.contains(playTypeInformation.getPlayType()) && !(playTypeInformation.getPlayPlayer().equals(ncaaTeam.getTeamName()) || playTypeInformation.getPlayPlayer().equals(gameResult.getOpponentName()))){
//                           dataUpdateServiceHelper.populateReboundResultBreakdown(gameResult, isOpponent, playTypeInformation, playTypeInformationPastScenario, priorTimeFrameType, priorPlayType, groupingName, groupingName, tempo);
//                           playTypeInformationPastScenario = null;
//                        }
//                        if(FREETHROW_VALUES.contains(playTypeInformation.getPlayType())){
//                           dataUpdateServiceHelper.populateFreeThrowResultBreakdown(gameResult, isOpponent, playTypeInformation);
//                           if(playTypeInformation.getPlayType().equals("MISSFREE")) {
//                              playTypeInformationPastScenario = priorPlayType + "_" + playTypeInformation.getOverallPlayType();
//                              playTracker.setPlayTypeInformationPast(playTypeInformationPastScenario);
//                           }
//                        }
//                     }else{
//                        playTracker.setOverallPlayResult("Skip Everything");
//                     }
//
//                     if(altPlayType != null) {
//                        priorPlayType = altPlayType;
//                     }else if (playTypeInformation.getPlayType().equals("SKIP")){
//                           priorPlayType = priorPlayType;  //This is more just for showing we ignore things
//                     }else if(REBOUND_VALUES.contains(playTypeInformation.getPlayType()) &&
//                              (playTypeInformation.getPlayPlayer().equals(ncaaTeam.getTeamName()) ||
//                                 playTypeInformation.getPlayPlayer().equals(gameResult.getOpponentName())))
//                     {
//                           priorPlayType = "DEADBALL";
//                     }else{
//                           priorPlayType = playTypeInformation.getPriorPlayType();
//                     }
//
//
//                   //  priorPointsDifference = espnPlayByPlayExtractionService.extractPointsDifference(pbp.getCombinedScore());
//                   //priorPossessionDifference = espnPlayByPlayExtractionService.extractPossessionDifference(pbp.getCombinedScore());
//
//                     priorTimeFrameType = dataUpdateServiceHelper.determineTimeFrameType(priorTimeSeconds);
//
//                     playTrackers.add(playTracker);
//                     playTypeInformationNext = null;
//                     playTypeInformationPast = playTypeInformation;
//                     playTypeInformation = null;
//                     playTypeInformationNextNext = null;
//                  }
//
//                  dataUpdateServiceHelper.updatePossessionPercentages(gameResult.getOffensivePossessionResultBreakdownList());
//                  dataUpdateServiceHelper.updatePossessionPercentages(gameResult.getDefensivePossessionResultBreakdownList());
//
//                  try (FileOutputStream output = new FileOutputStream(file)){
//                     try(DataOutputStream outputStream = new DataOutputStream(output)) {
//                        outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                        outputStream.flush();
//                     }
//                  }
////
////                  try (FileOutputStream output = new FileOutputStream(resultMakeupFile)){
////                     output.write(jsonMapper.serializeResponseToJson(playTrackers).getBytes(StandardCharsets.UTF_8));
////                     output.flush();
////                  }
//
//               }else{
//                  System.out.println("No Play by Play");
//               }
//            }
//         }
//      }
//   }
//
//   public void summurizeAllPlayByPlayDataInformation (RequestContext ncaabbRequest,
//                                                      ResponseContext ncaabbResponse)
//      throws Exception
//   {
//
//         // stuff up here to figure out how the data is going to be sorted, dates used, etc.
//         // for below just conference games for the current year will be used.
//
//         Collection<File> filesInFolder = FileUtils.listFiles(new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\"), null, false);
//
//         for (File file : filesInFolder) {
//
//            String fileName = file.getAbsolutePath();
//
//            String json = null;
//            //Instantiating the FileInputStream class
//            try (FileInputStream fileIn = new FileInputStream(fileName)) {
//               //Instantiating the DataInputStream class
//               try (DataInputStream inputStream = new DataInputStream(fileIn)) {
//                  //Reading UTF data from the DataInputStream
//                  json = new String(inputStream.readAllBytes(), "UTF-8");
//               }
//            }
//
//            NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//            String teamNumber = ncaaTeam.getTeamId();
//            StatsBreakdown statsBreakdown = new StatsBreakdown();
//
//            for (YearSummary yearSummary : ncaaTeam.getYearSummaries()) {
//               for (GameResult gameResult : yearSummary.getGameResults()) {
//                  for (PossessionResultBreakdown possessionResultBreakdown : gameResult.getOffensivePossessionResultBreakdownList()) {
//                     dataUpdateServiceHelper.populatePossessionResultBreakdownSummary(statsBreakdown.getOverallOffensivePossessionResultBreakdownList(), possessionResultBreakdown);
//                  }
//               }
//            }
//
//            ncaaTeam.getStatsBreakdown().add(statsBreakdown);
//
//            try (FileOutputStream output = new FileOutputStream(file)) {
//               try (DataOutputStream outputStream = new DataOutputStream(output)) {
//                  outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                  outputStream.flush();
//               }
//            }
//         }
//   }
//
//   public void disectAllPlayByPlayDataInformation (RequestContext ncaabbRequest,
//                                                      ResponseContext ncaabbResponse)
//    {
//      // stuff up here to figure out how the data is going to be sorted, dates used, etc.
//      // for below just conference games for the current year will be used.
//      try{
//         Collection<File> filesInFolder = FileUtils.listFiles(new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\"), null, false);
//         List<PlayTrackerDisect> playTrackerList = new ArrayList<>();
//         File resultMakeupFile = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\makeup.json");
//
//         for (File file : filesInFolder) {
//
//            String fileName = file.getAbsolutePath();
//
//            String json = null;
//            //Instantiating the FileInputStream class
//            try (FileInputStream fileIn = new FileInputStream(fileName)) {
//               //Instantiating the DataInputStream class
//               try (DataInputStream inputStream = new DataInputStream(fileIn)) {
//                  //Reading UTF data from the DataInputStream
//                  json = new String(inputStream.readAllBytes(), "UTF-8");
//               }
//            }
//
//            NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//            String teamNumber = ncaaTeam.getTeamId();
//
//            for (YearSummary yearSummary : ncaaTeam.getYearSummaries()) {
//               for (GameResult gameResult : yearSummary.getGameResults()) {
//                  EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml(gameResult.getGameId());
//                  if(espnGamePlayByPlay != null) {
//
//                     Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();
//                     EspnPlayByPlay pbp = null;
//                     EspnPlayByPlay pbpNext = null;
//                     EspnPlayByPlay pbpNextNext = null;
//                     EspnPlayByPlay pbpPast = null;
//                     String playTypeInformationPastScenario = null;
//                     PlayTypeInformationExtractor playTypeInformationNext = null;
//                     PlayTypeInformationExtractor playTypeInformation = null;
//                     PlayTypeInformationExtractor playTypeInformationNextNext = null;
//                     String playType = null;
//                     String playTypeNext = null;
//                     String playTypeNextNext = null;
//                     boolean isOpponentNextNext = false;
//                     boolean isOpponentNext = false;
//                     boolean isOpponent = false;
//
//                     for (int x = 0; x < playByPlaySize; ++x) {
//                        pbp = espnGamePlayByPlay.getEspnPlayByPlays().get(x);
//
//                        if (x + 1 < playByPlaySize) {
//                           pbpNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 1);
//                        } else {
//                           pbpNext = null;
//                        }
//
//                        if (x + 2 < playByPlaySize) {
//                           pbpNextNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 2);
//                        } else {
//                           pbpNextNext = null;
//                        }
//
//                        playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp.getGameDetail());
//                        isOpponent = espnPlayByPlayExtractionService.extractOpponent(pbp.getTeamLogo(), teamNumber);
//
//                        if(playTypeInformation != null) {
//                           playType = playTypeInformation.getPlayType();
//                        }else{
//                           playType = "ISSUE";
//                        }
//
//                        if (pbpNext != null) {
//                           playTypeInformationNext = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbpNext.getGameDetail());
//                           isOpponentNext = espnPlayByPlayExtractionService.extractOpponent(pbpNext.getTeamLogo(), teamNumber);
//                           if(playTypeInformationNext != null) {
//                              playTypeNext = playTypeInformationNext.getPlayType();
//                           }else{
//                              playTypeNext = "ISSUE";
//                           }
//                        } else {
//                           playTypeNext = "BLANK";
//                        }
//
//                        if (pbpNextNext != null) {
//                           playTypeInformationNextNext = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbpNextNext.getGameDetail());
//                           isOpponentNextNext = espnPlayByPlayExtractionService.extractOpponent(pbpNextNext.getTeamLogo(), teamNumber);
//                           if(playTypeInformationNextNext != null) {
//                              playTypeNextNext = playTypeInformationNextNext.getPlayType();
//                           }else{
//                              playTypeNextNext = "ISSUE";
//                           }
//                        } else {
//                           playTypeNextNext = "BLANK";
//                           isOpponentNextNext = false;
//                        }
//
//                        PlayTrackerDisect playTracker = extractPlayTracker(playTrackerList, playType, isOpponent, playTypeNext, isOpponentNext);//, playTypeNextNext, isOpponentNextNext);
//
//                        if (playTracker != null) {
//                           playTracker.setOccurances(playTracker.getOccurances().add(BigInteger.ONE));
//                        } else {
//                           playTracker = new PlayTrackerDisect(BigInteger.ONE, playType, isOpponent, playTypeNext, isOpponentNext, gameResult.getGameId()); //, playTypeNextNext, isOpponentNextNext);
//                           playTrackerList.add(playTracker);
//                        }
//                     }
//                  }
//               }
//            }
//         }
//
//         try (FileOutputStream output = new FileOutputStream(resultMakeupFile)) {
//            output.write(jsonMapper.serializeResponseToJson(playTrackerList).getBytes(StandardCharsets.UTF_8));
//            output.flush();
//         }
//      }catch(Exception e){
//            String message = "Watch it";
//      }
//
//   }
//
//   private PlayTrackerDisect extractPlayTracker (List<PlayTrackerDisect> playTrackerList,
//                                                 String playType,
//                                                 boolean isOpponent,
//                                                 String playTypeNext,
//                                                 boolean isOpponentNext)//,
//                                                // String playTypeNextNext,
//                                                // boolean isOpponentNextNext)
//   {
//      for(PlayTrackerDisect playTrackerDisect : playTrackerList){
//         if(
//            playTrackerDisect.getPlayType().equals(playType) &&
//            playTrackerDisect.isOpponent() == isOpponent &&
//            playTrackerDisect.getPlayTypeNext().equals(playTypeNext) &&
//            playTrackerDisect.isOpponentNext() == isOpponentNext //&&
//           // playTrackerDisect.getPlayTypeNextNext().equals(playTypeNextNext) &&
//           // playTrackerDisect.isOpponentNextNext() == isOpponentNextNext
//         ){
//            return playTrackerDisect;
//         }
//      }
//
//      return null;
//   }
}
