package com.rickags.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.models.json.espn.ncabb.EspnCompetition;
import com.rickags.models.json.espn.ncabb.EspnCompetitor;
import com.rickags.models.json.espn.ncabb.EspnEvent;
import com.rickags.models.json.espn.ncabb.EspnStatus;
import com.rickags.models.json.espn.ncabb.EspnTeam;
import com.rickags.models.json.espn.ncabb.EspnType;
import com.rickags.models.json.espn.ncabb.EspnVenue;

public class EspnEventFacadeTest
{
   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Test
   public void testGrabbingEspnEventFacade()
   {
      EspnEvent espnEvent = new EspnEvent();
      espnEvent.setId("12345");
      espnEvent.setEspnCompetitions(new ArrayList<>());
      EspnCompetition espnCompetition = new EspnCompetition();
      espnCompetition.setEspnVenue(new EspnVenue());
      espnCompetition.getEspnVenue().setId("venueid");
      espnCompetition.setConferenceCompetition(false);
      EspnCompetitor team1 = new EspnCompetitor();
      team1.setScore("321");
      team1.setEspnTeam(new EspnTeam());
      team1.getEspnTeam().setId("team1TeamId");
      team1.getEspnTeam().setLocation("team1Name");
      team1.getEspnTeam().setConferenceId("66");
      team1.getEspnTeam().setEspnVenue(new EspnVenue());
      team1.getEspnTeam().getEspnVenue().setId("3344");
      EspnCompetitor team2 = new EspnCompetitor();
      team2.setScore("123");
      team2.setEspnTeam(new EspnTeam());
      team2.getEspnTeam().setId("team2TeamId");
      team2.getEspnTeam().setLocation("team2Name");
      team2.getEspnTeam().setConferenceId("99");
      team2.getEspnTeam().setEspnVenue(new EspnVenue());
      team2.getEspnTeam().getEspnVenue().setId("4433");
      espnCompetition.setEspnCompetitors(new ArrayList<>());
      espnEvent.getEspnCompetitions().add(espnCompetition);
      espnEvent.setEspnStatus(new EspnStatus());
      espnEvent.getEspnStatus().setEspnType(new EspnType());
      espnEvent.getEspnStatus().getEspnType().setName("STATUS");
      espnCompetition.getEspnCompetitors().add(team1);
      espnCompetition.getEspnCompetitors().add(team2);


      EspnEventFacade espnEventFacade = new EspnEventFacade(espnEvent);

      assertEquals("STATUS", espnEventFacade.getEventStatus());
      assertEquals("12345", espnEventFacade.getEventId());
      assertEquals("venueid", espnEventFacade.getEventVenueId());
      assertEquals("321", espnEventFacade.getTeam1Score());
      assertEquals("team1TeamId", espnEventFacade.getTeam1Id());
      assertEquals("team1Name", espnEventFacade.getTeam1Name());
      assertEquals("66", espnEventFacade.getTeam1Conference());
      assertEquals("3344", espnEventFacade.getTeam1ArenaId());
      assertEquals("123", espnEventFacade.getTeam2Score());
      assertEquals("team2TeamId", espnEventFacade.getTeam2Id());
      assertEquals("team2Name", espnEventFacade.getTeam2Name());
      assertEquals("99", espnEventFacade.getTeam2Conference());
      assertEquals("4433", espnEventFacade.getTeam2ArenaId());
      assertFalse(espnEventFacade.isConferenceGame());
      assertTrue(espnEventFacade.isDivisionOneGame());

      espnCompetition.setConferenceCompetition(true);
      espnCompetition.getEspnCompetitors().get(1).getEspnTeam().setConferenceId(null);
      assertFalse(espnEventFacade.isDivisionOneGame());
      assertTrue(espnEventFacade.isConferenceGame());


   }
}
