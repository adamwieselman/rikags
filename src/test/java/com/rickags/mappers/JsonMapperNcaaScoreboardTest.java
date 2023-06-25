package com.rickags.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rickags.models.json.espn.ncabb.EspnGameBoxScore;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnScoreboard;
import com.rickags.models.json.espn.ncabb.EspnSubSection;
import com.rickags.services.UnirestApiService;
import com.rickags.services.UnirestApiServiceHelper;

public class JsonMapperNcaaScoreboardTest {

    private JsonMapper jsonMapper;

    @Test
    @Ignore
    public void testReadValue_ncaaBoxscore() throws UnirestException, IOException {
        UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
        JsonMapper jsonMapper = new JsonMapper();
        UnirestApiService unirestApiService = new UnirestApiService();
        unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
        unirestApiService.jsonMapper = jsonMapper;
        unirestApiService.setUpUnirest();
        EspnSubSection espnSubSection = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/boxscore?gameId=400988595&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class);
        unirestApiService.shutdownUnirest();

        assertEquals("boxscore", espnSubSection.getType());
        assertEquals("400988595", espnSubSection.getGameId());
        assertEquals("mens-college-basketball", espnSubSection.getEspnContent().getLeague());
        assertNotNull(espnSubSection.getEspnContent().getHtml());

        Document doc = Jsoup.parse(espnSubSection.getEspnContent().getHtml());
        Element awayTeamBoxscore = doc.getElementsByClass("gamepackage-away-wrap").first();
        Element homeTeamBoxscore = doc.getElementsByClass("gamepackage-home-wrap").first();
        Elements awayTeamName = awayTeamBoxscore.getElementsByClass("name");
        Elements awayTeamMin = awayTeamBoxscore.getElementsByClass("min");
        Elements awayTeamFg = awayTeamBoxscore.getElementsByClass("fg");
        Elements awayTeam3pt = awayTeamBoxscore.getElementsByClass("3pt");
        Elements awayTeamFt = awayTeamBoxscore.getElementsByClass("ft");
        Elements awayTeamOreb = awayTeamBoxscore.getElementsByClass("oreb");
        Elements awayTeamDreb = awayTeamBoxscore.getElementsByClass("dreb");
        Elements awayTeamAst = awayTeamBoxscore.getElementsByClass("ast");
        Elements awayTeamStl = awayTeamBoxscore.getElementsByClass("stl");
        Elements awayTeamBlk = awayTeamBoxscore.getElementsByClass("blk");
        Elements awayTeamTo = awayTeamBoxscore.getElementsByClass("to");
        Elements awayTeamPf = awayTeamBoxscore.getElementsByClass("pf");
        Elements awayTeamPts = awayTeamBoxscore.getElementsByClass("pts");

        JSONObject boxScore = new JSONObject();
        JSONArray playerResults = new JSONArray();

        for (int i = 0; i < awayTeamName.size() - 2; i++) {
            if(i != 0 && i != 6) {
                String playerName = awayTeamName.get(i).getElementsByTag("span").get(0).text();
                String playerPosition = awayTeamName.get(i).getElementsByClass("position").get(0).text();
                String playerMin = awayTeamMin.get(i).text();
                String playerFg = awayTeamFg.get(i).text();
                String player3pt = awayTeam3pt.get(i).text();
                String playerFt = awayTeamFt.get(i).text();
                String playerOreb = awayTeamOreb.get(i).text();
                String playerDreb = awayTeamDreb.get(i).text();
                String playerAst = awayTeamAst.get(i).text();
                String playerStl = awayTeamStl.get(i).text();
                String playerBlk = awayTeamBlk.get(i).text();
                String playerTo = awayTeamTo.get(i).text();
                String playerPf = awayTeamPf.get(i).text();
                String playerPts = awayTeamPts.get(i).text();

                JSONObject playerResult = new JSONObject();
                playerResult.put("playerName", playerName);
                playerResult.put("playerPosition", playerPosition);
                playerResult.put("playerMin", playerMin);
                playerResult.put("playerFg", playerFg);
                playerResult.put("player3pt", player3pt);
                playerResult.put("playerFt", playerFt);
                playerResult.put("playerOreb", playerOreb);
                playerResult.put("playerDreb", playerDreb);
                playerResult.put("playerAst", playerAst);
                playerResult.put("playerStl", playerStl);
                playerResult.put("playerBlk", playerBlk);
                playerResult.put("playerTo", playerTo);
                playerResult.put("playerPf", playerPf);
                playerResult.put("playerPts", playerPts);

                playerResults.put(playerResult);
            }
        }

        boxScore.put("game-boxscores", playerResults);
        EspnGameBoxScore espnGameBoxScore = jsonMapper.readValue(boxScore.toString(), EspnGameBoxScore.class);
        assertEquals("T. Hegner", espnGameBoxScore.getEspnBoxScores().get(0).getPlayerName());
        assertEquals("K. Thomas", espnGameBoxScore.getEspnBoxScores().get(3).getPlayerName());
    }

    @Test
    @Ignore
    public void testReadValue_ncaaScoreboard() throws IOException, UnirestException {
        UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
        JsonMapper jsonMapper = new JsonMapper();
        UnirestApiService unirestApiService = new UnirestApiService();
        unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
        unirestApiService.jsonMapper = jsonMapper;
        unirestApiService.setUpUnirest();
        EspnScoreboard espnScoreboard = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/scoreboard/_/group/50/date/20200109?xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnScoreboard.class);
        unirestApiService.shutdownUnirest();

        assertEquals("scoreboard", espnScoreboard.getType());
        assertEquals("mens-college-basketball", espnScoreboard.getEspnContent().getLeague());
        assertEquals("NCAAM Basketball", espnScoreboard.getEspnContent().getEspnSbGroup().getPageTitle());
        assertEquals("NCAA Men's Basketball", espnScoreboard.getEspnContent().getEspnSbGroup().getAltTitle());
        assertEquals("2002-10-10", espnScoreboard.getEspnContent().getEspnSbGroup().getScheduleStartDate());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbGroup().isCollege());
        assertEquals("mens-college-basketball", espnScoreboard.getEspnContent().getEspnSbGroup().getLeague());
        assertEquals("basketball", espnScoreboard.getEspnContent().getEspnSbGroup().getSport());
        assertEquals("2018-11-10T00:00Z", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getDate());
        assertEquals("s:40~l:41~e:401083123", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getUid());
        assertEquals("Southern Illinois Salukis at Kentucky Wildcats", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getName());
        assertEquals("401083123", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getId());
        assertEquals("SIU @ UK", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getShortName());
        assertEquals(2, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getPeriod());
        assertEquals("0:00", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getDisplayClock());
        assertEquals(0, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getClock());
        assertEquals("STATUS_FINAL", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getName());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getDescription());
        assertEquals("3", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getId());
        assertEquals("post", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getState());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getCompleted());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getShortDetail());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnStatus().getEspnType().getDetail());
        assertEquals("2018-11-10T00:00Z", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getDate());
        assertEquals(false, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).isConferenceCompetition());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).isTimeValid());
        assertEquals("401083123", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getId());
        assertEquals(false, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).isNeutralSite());
        assertEquals(false, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).isRecent());
        assertEquals(20277, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getAttendance());
        assertEquals("2018-11-10T00:00Z", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getStartDate());
        assertEquals(2, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getPeriod());
        assertEquals("0:00", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getDisplayClock());
        assertEquals(0, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getClock());
        assertEquals("STATUS_FINAL", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getName());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getDescription());
        assertEquals("3", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getId());
        assertEquals("post", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getState());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getCompleted());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getShortDetail());
        assertEquals("Final", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnStatus().getEspnType().getDetail());
        assertEquals("Rupp Arena", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().getFullName());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().isIndoor());
        assertEquals("251", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().getId());
        assertEquals(20500, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().getCapacity());
        assertEquals("Lexington", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().getEspnAddress().getCity());
        assertEquals("KY", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnVenue().getEspnAddress().getState());
        assertEquals("team", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getType());
        assertEquals("s:40~l:41~t:96", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getUid());
        assertEquals("home", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getHomeAway());
        assertEquals("71", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getScore());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).isWinner());
        assertEquals("96", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getId());
        assertEquals(31, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnLineScores().get(0).getValue());
        assertEquals("ffffff", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getAlternateColor());
        assertEquals("251", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getEspnVenue().getId());
        assertEquals("005DAA", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getColor());
        assertEquals("Kentucky Wildcats", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getDisplayName());
        assertEquals("UK", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getAbbreviation());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().isActive());
        assertEquals("Kentucky", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getShortDisplayName());
        assertEquals("s:40~l:41~t:96", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getUid());
        assertEquals("23", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId());
        assertEquals("Wildcats", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getName());
        assertEquals("https://a.espncdn.com/i/teamlogos/ncaa/500/96.png", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getLogo());
        assertEquals("Kentucky", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getLocation());
        assertEquals("96", espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().get(0).getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getId());
//        assertEquals("NIT", espnScoreboard.getContent().getAltSbDropdown().getLabel());
//        assertEquals("conference", espnScoreboard.getContent().getAltSbDropdown().getType());
//        assertEquals(false, espnScoreboard.getContent().getAltSbDropdown().getOptions().get(0).isSelected());
//        assertEquals("NCAA Tournament", espnScoreboard.getContent().getAltSbDropdown().getOptions().get(0).getLabel());
//        assertEquals("#", espnScoreboard.getContent().getAltSbDropdown().getOptions().get(0).getValue());
//        assertEquals("null-null-null", espnScoreboard.getContent().getAltSbDropdown().getOptions().get(0).getData().getWeek());
//        assertEquals("100", espnScoreboard.getContent().getAltSbDropdown().getOptions().get(0).getData().getGroup());
        assertEquals("Division I", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getLabel());
        assertEquals("conference", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getType());
        assertEquals(true, espnScoreboard.getEspnContent().getEspnAltSbDropdown().getEspnOptions().get(0).isSelected());
        assertEquals("Division I", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getEspnOptions().get(0).getLabel());
        assertEquals("#", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getEspnOptions().get(0).getValue());
        assertEquals("null-null-null", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getEspnOptions().get(0).getEspnData().getWeek());
        assertEquals("50", espnScoreboard.getEspnContent().getEspnAltSbDropdown().getEspnOptions().get(0).getEspnData().getGroup());
    }

    @Test
    @Ignore
    public void testReadValue_ncaaPlayByPlay() throws UnirestException, IOException {
        UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
        JsonMapper jsonMapper = new JsonMapper();
        UnirestApiService unirestApiService = new UnirestApiService();
        unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
        unirestApiService.jsonMapper = jsonMapper;
        unirestApiService.setUpUnirest();
        EspnSubSection espnSubSection = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=400988595&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class);
        unirestApiService.shutdownUnirest();

        assertEquals("playbyplay", espnSubSection.getType());
        assertEquals("400988595", espnSubSection.getGameId());
        assertEquals("mens-college-basketball", espnSubSection.getEspnContent().getLeague());
        assertNotNull(espnSubSection.getEspnContent().getHtml());

        Document doc = Jsoup.parse(espnSubSection.getEspnContent().getHtml());
        Element pbpTableFirstHalf = doc.select("#gp-quarter-1").first();
        Element pbpTableSecondHalf = doc.select("#gp-quarter-2").first();
        Elements timeStampsFirst = pbpTableFirstHalf.getElementsByClass("time-stamp");
        Elements teamLogosFirst = pbpTableFirstHalf.getElementsByClass("team-logo");
        Elements gameDetailsFirst = pbpTableFirstHalf.getElementsByClass("game-details");
        Elements combinedScoresFirst = pbpTableFirstHalf.getElementsByClass("combined-score");
        Elements timeStampsSecond = pbpTableSecondHalf.getElementsByClass("time-stamp");
        Elements teamLogosSecond = pbpTableSecondHalf.getElementsByClass("team-logo");
        Elements gameDetailsSecond = pbpTableSecondHalf.getElementsByClass("game-details");
        Elements combinedScoresSecond = pbpTableSecondHalf.getElementsByClass("combined-score");

        JSONObject game = new JSONObject();
        JSONArray plays = new JSONArray();

        for (int i = 0, l = timeStampsFirst.size(); i < l; i++) {
            String timeStampValue = timeStampsFirst.get(i).text();
            String teamLogoValue = teamLogosFirst.get(i).attr("src");
            String gameDetailValue = gameDetailsFirst.get(i).text();
            String combinedScoreValue = combinedScoresFirst.get(i).text();

            JSONObject play = new JSONObject();
            play.put("time-stamp", timeStampValue);
            play.put("team-logo", teamLogoValue);
            play.put("game-detail", gameDetailValue);
            play.put("combined-score", combinedScoreValue);

            plays.put(play);
        }

        game.put("game-plays", plays);

        EspnGamePlayByPlay espnGamePlayByPlay = jsonMapper.readValue(game.toString(), EspnGamePlayByPlay.class);

        assertEquals("20:00", espnGamePlayByPlay.getEspnPlayByPlays().get(0).getTimeStamp());
        assertEquals("Jump Ball won by Villanova", espnGamePlayByPlay.getEspnPlayByPlays().get(0).getGameDetail());
    }
}