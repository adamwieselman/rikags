package com.rickags.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.factories.BasicObjectFactory;
import com.rickags.factories.JsonObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnSubSection;

@Component
public class HtmlExtractionService
{
   @Autowired
   protected UnirestApiService unirestApiService;

   @Autowired
   protected JsonMapper jsonMapper;

   @Autowired
   protected BasicObjectFactory basicObjectFactory;

   @Autowired
   protected JsonObjectFactory jsonObjectFactory;


   public EspnGamePlayByPlay extractNcaabbGameHtml (String gameId)
      throws Exception
   {
      unirestApiService.setUpUnirest();
      EspnSubSection espnSubSection = unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=" + gameId + "&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class);
      unirestApiService.shutdownUnirest();
      Document doc = Jsoup.parse(espnSubSection.getEspnContent().getHtml());
      JSONObject game = jsonObjectFactory.createJSONObject();
      JSONArray plays = jsonObjectFactory.createJSONArray();
      Integer quarterNumber = 1;
      Element pbpTableSession = doc.select("#gp-quarter-1").first();
      if (pbpTableSession != null) {
         while (pbpTableSession != null) {
            Elements timeStampsFirst = pbpTableSession.getElementsByClass("time-stamp");
            Elements teamLogosFirst = pbpTableSession.getElementsByClass("team-logo");
            Elements gameDetailsFirst = pbpTableSession.getElementsByClass("game-details");
            Elements combinedScoresFirst = pbpTableSession.getElementsByClass("combined-score");

            for (int i = 0, l = timeStampsFirst.size(); i < l; i++) {
               String timeStampValue = timeStampsFirst.get(i).text();
               String teamLogoValue = teamLogosFirst.get(i).attr("src");
               String gameDetailValue = gameDetailsFirst.get(i).text();
               String combinedScoreValue = combinedScoresFirst.get(i).text();

               JSONObject play = jsonObjectFactory.createJSONObject();
               play.put("time-stamp", timeStampValue);
               play.put("team-logo", teamLogoValue);
               play.put("game-detail", gameDetailValue);
               play.put("combined-score", combinedScoreValue);
               play.put("game-half", quarterNumber.toString());

               plays.put(play);
            }

            game.put("game-plays", plays);

            pbpTableSession = doc.select("#gp-quarter-" + ++quarterNumber + "").first();

         }

         return jsonMapper.readValue(game.toString(), EspnGamePlayByPlay.class);

      }

      // if nothing create a blank list
      EspnGamePlayByPlay espnGamePlayByPlay = basicObjectFactory.createEspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(basicObjectFactory.createEspnPlayByPlays());

      return espnGamePlayByPlay;
   }

}
