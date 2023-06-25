package com.rickags.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.services.NcaaBasketballPlayByPlayDataUpdateService;
import com.rickags.services.PlayByPlayDataUpdateService;

public class PlayByPlayDataUpdateServiceFactoryTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public PlayByPlayDataOrganizerServiceFactory playByPlayDataOrganizerServiceFactory = new PlayByPlayDataOrganizerServiceFactory();


   @Test
   public void testCreatePlayByPlayDataOrganizerService_foundcreatePlayByPlayDataOrganizerService ()
   {
      List<PlayByPlayDataUpdateService> playByPlayDataUpdateServiceList = new ArrayList<>();
      PlayByPlayDataUpdateService playByPlayDataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();
      playByPlayDataUpdateServiceList.add(playByPlayDataUpdateService);

      playByPlayDataOrganizerServiceFactory.playByPlayDataUpdateServices = playByPlayDataUpdateServiceList;
      PlayByPlayDataUpdateService playByPlayDataUpdateServiceChosen = playByPlayDataOrganizerServiceFactory.createPlayByPlayDataOrganizerService("NCAABB");
      assertEquals(playByPlayDataUpdateServiceChosen, playByPlayDataUpdateService);
   }

   @Test
   public void testCreatePlayByPlayDataOrganizerService_noServiceFound ()
   {
      List<PlayByPlayDataUpdateService> playByPlayDataUpdateServiceList = new ArrayList<>();
      PlayByPlayDataUpdateService playByPlayDataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();
      playByPlayDataUpdateServiceList.add(playByPlayDataUpdateService);

      playByPlayDataOrganizerServiceFactory.playByPlayDataUpdateServices = playByPlayDataUpdateServiceList;
      assertNull(playByPlayDataOrganizerServiceFactory.createPlayByPlayDataOrganizerService("OTHER"));
   }
}
