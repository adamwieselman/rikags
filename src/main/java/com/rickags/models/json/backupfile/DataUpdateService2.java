package com.rickags.models.json.backupfile;

public class DataUpdateService2
{
//   @Autowired
//   protected UnirestApiService unirestApiService;
//
//   @Autowired
//   protected UnirestApiServiceHelper unirestApiServiceHelper;
//
//   @Autowired
//   protected DataUpdateServiceHelper dataUpdateServiceHelper;
//
//   @Autowired
//   protected JsonMapper jsonMapper;
//
//
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
//                  String team1Name = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getDisplayName();
//                  String team1Conference = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId();
//                 // String team1ArenaId = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getEspnVenue().getId();
//
//                  String team2Id = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getId();
//                  String team2Name = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getDisplayName();
//                  String team2Conference = espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getConferenceId();
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
//                        String json = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath())));
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
//                              gameResult.setOpponentConferenceId(team2Conference);
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
//                           gameResult.setOpponentConferenceId(team2Conference);
//                           gameResult.setDesignatedHost(true);
//
//                           yearSummary.getGameResults().add(gameResult);
//                           ncaaTeam.getYearSummaries().add(yearSummary);
//                        }
//
//                        try (FileOutputStream output = new FileOutputStream(file1)){
//                           output.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                           output.flush();
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
//                        gameResult.setOpponentConferenceId(team2Conference);
//                        gameResult.setDesignatedHost(true);
//
//                        yearSummary.getGameResults().add(gameResult);
//                        ncaaTeam.getYearSummaries().add(yearSummary);
//
//                        try (FileOutputStream output = new FileOutputStream(file1)){
//                           output.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                           output.flush();
//                        }
//                     }
//
//                     File file2 = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\" + team2Id + ".json");
//
//                     if (file2.exists()) {
//                        String json = new String(Files.readAllBytes(Paths.get(file2.getAbsolutePath())));
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
//                              gameResult.setOpponentConferenceId(team2Conference);
//                              gameResult.setDesignatedHost(false);
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
//                           gameResult.setOpponentConferenceId(team2Conference);
//                           gameResult.setDesignatedHost(false);
//
//                           yearSummary.getGameResults().add(gameResult);
//                           ncaaTeam.getYearSummaries().add(yearSummary);
//                        }
//
//                        try (FileOutputStream output = new FileOutputStream(file2)){
//                           output.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                           output.flush();
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
//                        gameResult.setOpponentConferenceId(team1Conference);
//                        gameResult.setDesignatedHost(false);
//
//                        yearSummary.getGameResults().add(gameResult);
//                        ncaaTeam.getYearSummaries().add(yearSummary);
//
//                        try (FileOutputStream output = new FileOutputStream(file2)){
//                           output.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                           output.flush();
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
//   public void pullAllPlayByPlayDataInformation(RequestContext ncaabbRequest,
//                                                ResponseContext ncaabbResponse)
//      throws IOException, UnirestException
//   {
//
//      Collection<File> filesInFolder = FileUtils.listFiles(new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\"), null, false);
//
//      for (File file : filesInFolder) {
//
//         String fileName = file.getAbsolutePath();
//
//         String json = new String(Files.readAllBytes(Paths.get(fileName)));
//
//         NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//         String teamNumber = ncaaTeam.getTeamId();
//
//         for(YearSummary yearSummary: ncaaTeam.getYearSummaries()) {
//            for(GameResult gameResult : yearSummary.getGameResults()) {
//               if (gameResult.getGameResultBreakdowns().size() == 0) {
//                  boolean isHost = gameResult.getDesignatedHost();
//                  unirestApiService.setUpUnirest();
//                  EspnSubSection espnSubSection = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=" + gameResult.getGameId() + "&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class);
//                  unirestApiService.shutdownUnirest();
//
//                  Document doc = Jsoup.parse(espnSubSection.getEspnContent().getHtml());
//                  JSONObject game = new JSONObject();
//                  JSONArray plays = new JSONArray();
//                  Integer quarterNumber = 1;
//                  Element pbpTableSession = doc.select("#gp-quarter-1").first();
//                  if (pbpTableSession != null) {
//                     while (pbpTableSession != null) {
//                        Elements timeStampsFirst = pbpTableSession.getElementsByClass("time-stamp");
//                        Elements teamLogosFirst = pbpTableSession.getElementsByClass("team-logo");
//                        Elements gameDetailsFirst = pbpTableSession.getElementsByClass("game-details");
//                        Elements combinedScoresFirst = pbpTableSession.getElementsByClass("combined-score");
//
//                        for (int i = 0, l = timeStampsFirst.size(); i < l; i++) {
//                           String timeStampValue = timeStampsFirst.get(i).text();
//                           String teamLogoValue = teamLogosFirst.get(i).attr("src");
//                           String gameDetailValue = gameDetailsFirst.get(i).text();
//                           String combinedScoreValue = combinedScoresFirst.get(i).text();
//
//                           JSONObject play = new JSONObject();
//                           play.put("time-stamp", timeStampValue);
//                           play.put("team-logo", teamLogoValue);
//                           play.put("game-detail", gameDetailValue);
//                           play.put("combined-score", combinedScoreValue);
//                           play.put("game-half", quarterNumber.toString());
//
//                           plays.put(play);
//                        }
//
//                        game.put("game-plays", plays);
//
//                        pbpTableSession = doc.select("#gp-quarter-" + ++quarterNumber + "").first();
//                     }
//
//
//                     EspnGamePlayByPlay espnGamePlayByPlay = jsonMapper.readValue(game.toString(), EspnGamePlayByPlay.class);
//                     Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();
//                     final Set<String> VALUES = Set.of("TURNOVER","MADE2PTCLOSE", "DEADBALL", "MADE3JUMP", "MADE2JUMP", "MADELAYUP", "MADEDUNK", "MADE2TIP", "MADEFREE", "TIMEOUT", "ENDOF", "JUMPBALL", "FOUL");
//                     boolean opponent = true;
//                     int opponentFouls = 0;
//                     int primaryFouls = 0;
//                     boolean previousPlay1st = true;
//                     String groupingName = "ALL";
//                     BigInteger priorTimeSeconds = new BigInteger("2400");
//                     String priorPlayType = "DEADBALL";
//                     BigInteger priorPossessionDiff = BigInteger.ZERO;
//                     BigInteger priorPossessionDiffRem = BigInteger.ZERO;
//                     BigInteger priorPointsDiff = BigInteger.ZERO;
//                     String priorTimeFrameType = "FIRSTHALF";
//                     priorTimeFrameType = "REGGAME";
//
//                     // for each play
//                     for(int x = 0; x < playByPlaySize; ++x) {
//                        //for (EspnPlayByPlay pbp : espnGamePlayByPlay.getEspnPlayByPlays()) {
//                        System.out.println("play " + x);
//
//                        EspnPlayByPlay pbp = espnGamePlayByPlay.getEspnPlayByPlays().get(x);
//
//                        String[] timeparts = pbp.getTimeStamp().split(":");
//
//                        BigInteger timeSeconds = BigInteger.ZERO;
//
//                        if (pbp.getGameHalf().equals("1")) {
//                           timeSeconds = new BigInteger(timeparts[0]).add(new BigInteger("20")).multiply(new BigInteger("60")).add(new BigInteger(timeparts[1]));
//                        } else {
//                           timeSeconds = new BigInteger(timeparts[0]).multiply(new BigInteger("60")).add(new BigInteger(timeparts[1]));
//                        }
//
//                        //get initial tempo
//                        if (!pbp.getGameHalf().equals("1") && !pbp.getGameHalf().equals("2") && priorTimeSeconds.equals(BigInteger.ZERO)) {
//                           priorTimeSeconds = new BigInteger("300");
//                        }
//
//                        BigInteger tempo = priorTimeSeconds.subtract(timeSeconds);
//                        priorTimeSeconds = timeSeconds;
//
//                        int pngSpot = pbp.getTeamLogo().indexOf(".png");
//                        int slashSpot = pbp.getTeamLogo().lastIndexOf("/", pngSpot);
//
//                        String playTeamNumber = pbp.getTeamLogo().substring(slashSpot + 1, pngSpot);
//
//                        opponent = true;
//                        if (playTeamNumber.equals(teamNumber)) {
//                           opponent = false;
//                        }
//
//                        String detail = pbp.getGameDetail();
//                        String playType = null;
//                        String overallPlayType = null;
//                        String playPlayer = null;
//
//                        if (detail.contains(" missed Three Point Jumper")) {
//                           playType = "MISS3JUMP";
//                           overallPlayType = "3PTATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Three Point Jumper"));
//                        } else if (detail.contains(" missed Jumper")) {
//                           playType = "MISS2JUMP";
//                           overallPlayType = "2PTJUMPATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Jumper"));
//                        } else if (detail.contains(" missed Layup")) {
//                           playType = "MISSLAYUP";
//                           playType = "MISS2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Layup"));
//                        } else if (detail.contains(" missed Dunk")) {
//                           playType = "MISSDUNK";
//                           playType = "MISS2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Dunk"));
//                        } else if (detail.contains(" missed Two Point Tip Shot")) {
//                           playType = "MISS2TIP";
//                           playType = "MISS2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Two Point Tip Shot"));
//                        } else if (detail.contains(" missed Free Throw")) {
//                           playType = "MISSFREE";
//                           playPlayer = detail.substring(0, detail.indexOf(" missed Free Throw"));
//                        } else if (detail.contains(" made Three Point Jumper")) {
//                           playType = "MADE3JUMP";
//                           overallPlayType = "3PTATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Three Point Jumper"));
//                        } else if (detail.contains(" made Jumper")) {
//                           playType = "MADE2JUMP";
//                           overallPlayType = "2PTJUMPATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Jumper"));
//                        } else if (detail.contains(" made Layup")) {
//                           playType = "MADELAYUP";
//                           playType = "MADE2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Layup"));
//                        } else if (detail.contains(" made Dunk")) {
//                           playType = "MADEDUNK";
//                           playType = "MADE2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Dunk"));
//                        } else if (detail.contains(" made Two Point Tip Shot")) {
//                           playType = "MADE2TIP";
//                           playType = "MADE2PTCLOSE";
//                           overallPlayType = "2PTCLOSEATTEMPT";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Two Point Tip Shot"));
//                        } else if (detail.contains(" made Free Throw")) {
//                           playType = "MADEFREE";
//                           playPlayer = detail.substring(0, detail.indexOf(" made Free Throw"));
//                        } else if (detail.contains(" Offensive Rebound")) {
//                           playType = "OFFREB";
//                           playPlayer = detail.substring(0, detail.indexOf(" Offensive Rebound"));
//                        } else if (detail.contains(" Defensive Rebound")) {
//                           playType = "DEFREB";
//                           playPlayer = detail.substring(0, detail.indexOf(" Defensive Rebound"));
//                        } else if (detail.contains("Foul ")) {
//                           playType = "FOUL";
//                           playPlayer = detail.substring(8, detail.length()-1);
//                        } else if (detail.contains(" Turnover")) {
//                           playType = "TURNOVER";
//                           playPlayer = detail.substring(0, detail.indexOf(" Turnover"));
//                        } else if (detail.contains(" Steal")) {
//                           playType = "STEAL";
//                           playPlayer = detail.substring(0, detail.indexOf(" Steal"));
//                        } else if (detail.contains(" Timeout")) {
//                           playType = "TIMEOUT";
//                        } else if (detail.contains("End of ")) {
//                           playType = "ENDOF";
//                        } else if (detail.contains("Jump Ball ")) {
//                           playType = "JUMPBALL";
//                        } else if (detail.contains(" Block")) {
//                           playType = "BLOCK";
//                           playPlayer = detail.substring(0, detail.indexOf(" Block"));
//                        } else {
//                           System.out.println(detail);
//                        }
//
//                        if(playType != null){
//
//                           if (playType.equals("TURNOVER")) {
//                              // advance one and see if steal is next and time is the same
//                              EspnPlayByPlay pbpNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 1);
//
//                              String[] timepartsNext = pbp.getTimeStamp().split(":");
//
//                              BigInteger timeSecondsNext = BigInteger.ZERO;
//
//                              if (pbp.getGameHalf().equals("1")) {
//                                 timeSecondsNext = (new BigInteger(timepartsNext[0]).add(new BigInteger("20"))).multiply(new BigInteger("60")).add(new BigInteger(timepartsNext[1]));
//                              } else {
//                                 timeSecondsNext = new BigInteger(timepartsNext[0]).multiply(new BigInteger("60")).add(new BigInteger(timepartsNext[1]));
//                              }
//
//                              Boolean detailNext = pbpNext.getGameDetail().contains(" Steal");
//
//                              if (detailNext && timeSecondsNext.equals(timeSeconds)) {
//                                 ++x;
//                                 playType = "STEAL";
//                              }
//                           }
//
//                           if (playType.equals("FOUL")) {
//
//                              // advance one and see if foul is offensive and a turnover
//                              EspnPlayByPlay pbpNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 1);
//
//                              String[] timepartsNext = pbpNext.getTimeStamp().split(":");
//
//                              BigInteger timeSecondsNext = BigInteger.ZERO;
//
//                              if (pbpNext.getGameHalf().equals("1")) {
//                                 timeSecondsNext = (new BigInteger(timepartsNext[0]).add(new BigInteger("20"))).multiply(new BigInteger("60")).add(new BigInteger(timepartsNext[1]));
//                              } else {
//                                 timeSecondsNext = (new BigInteger(timepartsNext[0])).multiply(new BigInteger("60")).add(new BigInteger(timepartsNext[1]));
//                                 if(previousPlay1st){
//                                    previousPlay1st = false;
//                                    opponentFouls = 0;
//                                    primaryFouls = 0;
//                                 }
//                              }
//
//                              Boolean detailTurnoverNext = pbpNext.getGameDetail().contains(" Turnover");
//                              Boolean detailFreeThrowNext = pbpNext.getGameDetail().contains(" Free Throw");
//                              Boolean detailTimeoutNext = pbpNext.getGameDetail().contains(" Timeout");
//
//
//                              if (detailTurnoverNext && timeSecondsNext.equals(timeSeconds)) {
//                                 ++x;
//                                 playType = "TURNOVER";
//                              }else{
//                                 if(opponent){
//                                    ++opponentFouls;
//                                    if(opponentFouls < 7 && detailFreeThrowNext){
//                                       playType = "SHOOTINGFOUL";
//                                    }else if(opponentFouls < 7 && detailTimeoutNext){
//                                       EspnPlayByPlay pbpNextNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 2);
//                                       if(pbpNextNext.getGameDetail().contains(" Free Throw")){
//                                          playType = "SHOOTINGFOUL";
//                                       }
//                                    }else if(tempo.equals(BigInteger.ZERO) && priorPlayType.equals("DEADBALL")){
//                                       playType = "SHOOTINGFOUL";
//                                    }
//                                 }else{
//                                    ++primaryFouls;
//                                    if(primaryFouls < 7 && detailFreeThrowNext){
//                                       playType = "SHOOTINGFOUL";
//                                    }else if(primaryFouls < 7 && detailTimeoutNext){
//                                       EspnPlayByPlay pbpNextNext = espnGamePlayByPlay.getEspnPlayByPlays().get(x + 2);
//                                       if(pbpNextNext.getGameDetail().contains(" Free Throw")){
//                                          playType = "SHOOTINGFOUL";
//                                       }
//                                    }else if(tempo.equals(BigInteger.ZERO) && priorPlayType.equals("DEADBALL")){
//                                       playType = "SHOOTINGFOUL";
//                                    }
//                                 }
//                                 opponent = !opponent;
//                              }
//                           }
//
//                           if (playType.equals("MISSFREE") || playType.equals("MADEFREE")) {
//                              priorPlayType = "FOUL";
//                           }
//
//                           if (!playType.equals("TIMEOUT") && !playType.equals("ENDOF") && !playType.equals("JUMPBALL")) {
//                              // find the game result breakdown that matches
//
//                              GameResultBreakdown gameResultBreakdown = dataUpdateServiceHelper.extractGameResultBreakdown(gameResult, !opponent, priorPlayType, playType, priorTimeFrameType, priorPossessionDiff, priorPointsDiff, groupingName);
//                              if (gameResultBreakdown == null) {
//                                 gameResultBreakdown = new GameResultBreakdown();
//                                 gameResultBreakdown.setPossessionStart(priorPlayType);
//                                 gameResultBreakdown.setOverallPossessionResult(playType);
//                                 gameResultBreakdown.setGameTimeFrame(priorTimeFrameType);
//                                 gameResultBreakdown.setOffense(!opponent);
//                                 gameResultBreakdown.setGroupingName(groupingName);
//
////                                 if(!isHost) {
////                                    gameResultBreakdown.setPossessionDifference(priorPossessionDiff);
////                                    if (priorPossessionDiff == BigInteger.ONE) {
////                                       gameResultBreakdown.setPossessionScoreDifference(priorPointsDiff);
////                                    }
////                                 }else{
////                                    gameResultBreakdown.setPossessionDifference(priorPossessionDiff.multiply(new BigInteger("-1")));
////                                    if (priorPossessionDiff == BigInteger.ONE) {
////                                       gameResultBreakdown.setPossessionScoreDifference(priorPointsDiff.multiply(new BigInteger("-1")));
////                                    }
////                                 }
//
//                                 gameResultBreakdown.getTempos().add(tempo);
//                                 gameResultBreakdown.setOccurances(new BigInteger("1"));
//                                 PlayerBreakdown playerBreakdown = dataUpdateServiceHelper.extractPlayerBreakdown(gameResultBreakdown, playPlayer);
//                                 if(playerBreakdown==null){
//                                    playerBreakdown = new PlayerBreakdown();
//                                    playerBreakdown.setPlayerName(playPlayer);
//                                    playerBreakdown.getTempos().add(tempo);
//                                    playerBreakdown.setOccurances(new BigInteger("1"));
//                                    gameResultBreakdown.getPlayerBreakdowns().add(playerBreakdown);
//                                 }else{
//                                    playerBreakdown.getTempos().add(tempo);
//                                    playerBreakdown.setOccurances(playerBreakdown.getOccurances().add(BigInteger.ONE));
//                                 }
//                                 gameResult.getGameResultBreakdowns().add(gameResultBreakdown);
//                              } else {
//                                 gameResultBreakdown.getTempos().add(tempo);
//                                 gameResultBreakdown.setOccurances(gameResultBreakdown.getOccurances().add(BigInteger.ONE));
//                                 PlayerBreakdown playerBreakdown = dataUpdateServiceHelper.extractPlayerBreakdown(gameResultBreakdown, playPlayer);
//                                 if(playerBreakdown==null){
//                                    playerBreakdown = new PlayerBreakdown();
//                                    playerBreakdown.setPlayerName(playPlayer);
//                                    playerBreakdown.getTempos().add(tempo);
//                                    playerBreakdown.setOccurances(new BigInteger("1"));
//                                    gameResultBreakdown.getPlayerBreakdowns().add(playerBreakdown);
//                                 }else{
//                                    playerBreakdown.getTempos().add(tempo);
//                                    playerBreakdown.setOccurances(playerBreakdown.getOccurances().add(BigInteger.ONE));
//                                 }
//                              }
//                           }
//
//                           if (VALUES.contains(playType)) {
//                              priorPlayType = "DEADBALL";
//                           } else {
//                              priorPlayType = playType;
//                           }
//
//                           String[] scorePoints = pbp.getCombinedScore().split("-");
//                           priorPointsDiff = new BigInteger(scorePoints[0].trim()).subtract(new BigInteger(scorePoints[1].trim()));
//                           priorPossessionDiff = priorPointsDiff.divide(new BigInteger("3"));
//                           priorPossessionDiffRem = priorPointsDiff.mod(new BigInteger("3"));
//
//                           int priorPossessionDiffCompare = priorPossessionDiff.compareTo(BigInteger.ZERO);
//
//                           if(!priorPossessionDiffRem.equals(BigInteger.ZERO)){
//                              if(priorPossessionDiffCompare < 0){
//                                 priorPossessionDiff = priorPossessionDiff.subtract(BigInteger.ONE);
//                              }
//                              if(priorPossessionDiffCompare > 0){
//                                 priorPossessionDiff = priorPossessionDiff.add(BigInteger.ONE);
//                              }
//                              if(priorPossessionDiffCompare == 0){
//                                 if(priorPointsDiff.compareTo(BigInteger.ZERO)<0){
//                                    priorPossessionDiff = priorPossessionDiff.subtract(BigInteger.ONE);
//                                 }else{
//                                    priorPossessionDiff = priorPossessionDiff.add(BigInteger.ONE);
//                                 }
//                              }
//                           }
//
//                           if(priorPossessionDiff.compareTo(new BigInteger("4")) > 0){
//                              priorPossessionDiff = new BigInteger("4");
//                           }
//
//                           if(priorPossessionDiff.compareTo(new BigInteger("-4"))< 0){
//                              priorPossessionDiff = new BigInteger("-4");
//                           }
//
//
//                           if (priorTimeSeconds.compareTo(new BigInteger("1260")) > 0) {
//                              priorTimeFrameType = "FIRSTHALF";
//                              priorTimeFrameType = "REGGAME";
//                           } else if (priorTimeSeconds.compareTo(new BigInteger("1200")) > 0) {
//                              priorTimeFrameType = "FIRSTHALF1MIN";
//                           } else if (priorTimeSeconds.compareTo(new BigInteger("300")) > 0) {
//                              priorTimeFrameType = "SECONDHALF";
//                              priorTimeFrameType = "REGGAME";
//                           } else {
//                              priorTimeFrameType = "LAST5MIN";
//                           }
//                        }
//                     }
//
//                     try (FileOutputStream output = new FileOutputStream(file)){
//                        output.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
//                        output.flush();
//                     }
//
//                  } else {
//                     System.out.println("No Play by Play");
//                  }
//               }
//            }
//         }
//      }
//   }
//
//   public void summarizeGamePlayByPlayDataInformation(RequestContext ncaabbRequest,
//                                                      ResponseContext ncaabbResponse)
//      throws IOException, UnirestException
//   {
//
//      Collection<File> filesInFolder = FileUtils.listFiles(new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\"), null, false);
//
//      for (File file : filesInFolder) {
//
//         String fileName = file.getAbsolutePath();
//
//         String json = new String(Files.readAllBytes(Paths.get(fileName)));
//
//         NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
//         String teamNumber = ncaaTeam.getTeamId();
//
//         for(YearSummary yearSummary: ncaaTeam.getYearSummaries()) {
//            for(GameResult gameResult : yearSummary.getGameResults()) {
//               if (gameResult.getGameResultBreakdowns().size() != 0) {
//
//               }
//            }
//         }
//      }
//   }
//
//
//
//
//
//   public void pullAllData (RequestContext ncaabbRequest,
//                            ResponseContext ncaabbResponse)
//      throws UnirestException, IOException
//   {
//      LocalDate endDate = LocalDate.now();
//      LocalDate startDate = ncaabbRequest.getStartDate();
//
//      while (!startDate.equals(endDate)) {
//         // pull data from the date
//         unirestApiService.setUpUnirest();
//         String url = "http://cdn.espn.com/core/mens-college-basketball/scoreboard/_/group/50/date/" + startDate.getYear() + String.format("%02d", startDate.getMonthValue()) + String.format("%02d", startDate.getDayOfMonth()) + "?xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full";
//         EspnScoreboard espnScoreboard = unirestApiService.getWebsiteDataObject(url, null, null, EspnScoreboard.class);
//         unirestApiService.shutdownUnirest();
//
//         List<EspnEvent> espnEventsList = espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents();
//         // if no games skip to next game
//         if (espnEventsList.size() != 0) {
//            // for each game available on a day
//            for (EspnEvent espnEvent : espnEventsList) {
//               // verify that not cancelled or postponed or active
//               if ("STATUS_FINAL".equals(espnEvent.getEspnStatus().getEspnType().getName())) {
//                  System.out.println(espnEvent.getId());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getId());
//                  Integer homeId = Integer.parseInt(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getId());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getName());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getId());
//                  Integer awayId = Integer.parseInt(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getId());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getName());
//                  System.out.println(espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getConferenceId());
//                  if (espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId() != null && espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getConferenceId() != null) {
//                     unirestApiService.setUpUnirest();
//                     EspnSubSection espnSubSection = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=" + espnEvent.getId() + "&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class);
//                     unirestApiService.shutdownUnirest();
//
//                     Document doc = Jsoup.parse(espnSubSection.getEspnContent().getHtml());
//                     JSONObject game = new JSONObject();
//                     JSONArray plays = new JSONArray();
//                     Integer quarterNumber = 1;
//                     Element pbpTableSession = doc.select("#gp-quarter-1").first();
//                     // pull play by play data from the game
//                     if(pbpTableSession != null) {
//                        while(pbpTableSession != null) {
//                           Elements timeStampsFirst = pbpTableSession.getElementsByClass("time-stamp");
//                           Elements teamLogosFirst = pbpTableSession.getElementsByClass("team-logo");
//                           Elements gameDetailsFirst = pbpTableSession.getElementsByClass("game-details");
//                           Elements combinedScoresFirst = pbpTableSession.getElementsByClass("combined-score");
//
//                           for (int i = 0, l = timeStampsFirst.size(); i < l; i++) {
//                              String timeStampValue = timeStampsFirst.get(i).text();
//                              String teamLogoValue = teamLogosFirst.get(i).attr("src");
//                              String gameDetailValue = gameDetailsFirst.get(i).text();
//                              String combinedScoreValue = combinedScoresFirst.get(i).text();
//
//                              JSONObject play = new JSONObject();
//                              play.put("time-stamp", timeStampValue);
//                              play.put("team-logo", teamLogoValue);
//                              play.put("game-detail", gameDetailValue);
//                              play.put("combined-score", combinedScoreValue);
//                              play.put("game-half", quarterNumber.toString());
//
//                              plays.put(play);
//                           }
//
//                           game.put("game-plays", plays);
//
//                           pbpTableSession = doc.select("#gp-quarter-" + ++quarterNumber + "").first();
//                        }
//
//                        EspnGamePlayByPlay espnGamePlayByPlay = jsonMapper.readValue(game.toString(), EspnGamePlayByPlay.class);
//
//                        Integer homeMissed3Pointers = 0;
//                        Integer awayMissed3Pointers = 0;
//                        // for each play
//                        for (EspnPlayByPlay pbp : espnGamePlayByPlay.getEspnPlayByPlays()) {
//                           String[] timeparts = pbp.getTimeStamp().split(":");
//
//                           int timeSeconds = 0;
//
//                           if (pbp.getGameHalf() == "1") {
//                              timeSeconds = (Integer.parseInt(timeparts[0]) + 20) * 60 + Integer.parseInt(timeparts[1]);
//                           } else {
//                              timeSeconds = Integer.parseInt(timeparts[0]) * 60 + Integer.parseInt(timeparts[1]);
//                           }
//
//                           System.out.println(timeSeconds + " seconds remaining");
//
//                           int pngSpot = pbp.getTeamLogo().indexOf(".png");
//                           int slashSpot = pbp.getTeamLogo().lastIndexOf("/", pngSpot);
//
//                           Integer teamNumber = (Integer.parseInt(pbp.getTeamLogo().substring(slashSpot + 1, pngSpot)));
//                           System.out.println(teamNumber + " team number");
//
//                           String detail = pbp.getGameDetail();
//
//                           if (detail.contains(" missed Three Point Jumper")) {
//                              if (teamNumber.equals(homeId)) {
//                                 ++homeMissed3Pointers;
//                              } else {
//                                 ++awayMissed3Pointers;
//                              }
//
//                              String player = detail.substring(0, detail.indexOf(" missed Three Point Jumper"));
//                              System.out.println("Missed 3 player " + player);
//                           }
//
//                           String[] scorePoints = pbp.getCombinedScore().split("-");
//                           BigDecimal possesionsDiff = new BigDecimal((Integer.parseInt(scorePoints[0].trim()) - Integer.parseInt(scorePoints[1].trim())) / 3);
//                           System.out.println(possesionsDiff);
//                        }
//                        System.out.println("Away Total missed 3's" + awayMissed3Pointers);
//                        System.out.println("Home Total missed 3's" + homeMissed3Pointers);
//                     }else{
//                        System.out.println("No Play by Play");
//                     }
//                  } else {
//                     System.out.println("Not Div 1");
//                  }
//               } else {
//                  System.out.println("This was a different status " + espnEvent.getEspnStatus().getEspnType().getName());
//               }
//            }
//         }
//         startDate = startDate.plusDays(1);
//      }
//   }
//

}
