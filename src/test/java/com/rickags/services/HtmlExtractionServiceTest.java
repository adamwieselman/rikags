package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.factories.BasicObjectFactory;
import com.rickags.factories.JsonObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.espn.ncabb.EspnContent;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnSubSection;


public class HtmlExtractionServiceTest
{
   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   protected UnirestApiService unirestApiService;

   @Mock
   protected JsonMapper jsonMapper;

   @InjectMocks
   public HtmlExtractionService htmlExtractionService = new HtmlExtractionService();

   @Test
   public void extractNcaabbGameHtml_DataExists ()
      throws Exception
   {
      htmlExtractionService.jsonMapper = new JsonMapper();
      htmlExtractionService.jsonObjectFactory = new JsonObjectFactory();
      htmlExtractionService.basicObjectFactory = new BasicObjectFactory();

      EspnSubSection espnSubSection = new EspnSubSection();
      espnSubSection.setEspnContent(new EspnContent());
      espnSubSection.getEspnContent().setHtml("<div class=\"accordion-header\"><a class=\"webview-internal\" role=\"button\" data-toggle=\"collapse\" href=\"#gp-quarter-1\" aria-expanded=\"true\" aria-controls=\"gp-quarter-1\"><div class=\"left\"><h3>1st Half</h3></div></div><div id=\"gp-quarter-1\" class=\"accordion-content collapse in\"><table><thead><tr><th>time</th><th>team</th><th>PLAY</th><th class=\"score\">SCORE</th></tr></thead><tr><td class=\"time-stamp\">20:00</td><td class=\"logo\"><img class=\"team-logo\" src=\"https://a.espncdn.com/combiner/i?img=/i/teamlogos/ncaa/500/150.png&h=100&w=100\"/></td><td class=\"game-details\">Jump Ball won by Duke</td><td class=\"combined-score\">0 - 0</td><td class=\"video  \"></td></tr>");

      Mockito.when(unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=1111111&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class)).thenReturn(espnSubSection);

      EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml("1111111");

      assertNotNull(espnGamePlayByPlay);
      EspnPlayByPlay espnPlayByPlay = espnGamePlayByPlay.getEspnPlayByPlays().get(0);
      assertEquals("0 - 0", espnPlayByPlay.getCombinedScore());
      assertEquals("1", espnPlayByPlay.getGameHalf());
      assertEquals("20:00", espnPlayByPlay.getTimeStamp());
   }

   @Test
   public void extractNcaabbGameHtml_DataNull ()
      throws Exception
   {
      htmlExtractionService.jsonMapper = new JsonMapper();
      htmlExtractionService.jsonObjectFactory = new JsonObjectFactory();
      htmlExtractionService.basicObjectFactory = new BasicObjectFactory();

      EspnSubSection espnSubSection = new EspnSubSection();
      espnSubSection.setEspnContent(new EspnContent());
      espnSubSection.getEspnContent().setHtml("");

      Mockito.when(unirestApiService.getWebsiteDataObject("http://cdn.espn.com/core/mens-college-basketball/playbyplay?gameId=1111111&xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", null, null, EspnSubSection.class)).thenReturn(espnSubSection);

      EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml("1111111");

      assertEquals(0,espnGamePlayByPlay.getEspnPlayByPlays().size());
   }

}
