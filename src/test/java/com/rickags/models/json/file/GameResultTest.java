package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class GameResultTest
{
   @Test
   public void testGameResult()
   {
      LocalDate startDate = LocalDate.now();

      GameResult gameResult = new GameResult();
      gameResult.setGameId("gameId");
      gameResult.setConferenceGame(false);
      gameResult.setOpponentId("opponentId");
      gameResult.setVenueId("venueId");
      gameResult.setGameDate(startDate);
      gameResult.setDesignatedHost(true);
      gameResult.setOpponentName("opponentName");
      gameResult.setOpponentConferenceId("conferenceId");
      gameResult.setOffensivePossessionResultBreakdownList(new ArrayList<>());
      gameResult.setOffensiveShotResultBreakdownList(new ArrayList<>());
      gameResult.setOffensiveReboundResultBreakdownList(new ArrayList<>());
      gameResult.setDefensivePossessionResultBreakdownList(new ArrayList<>());
      gameResult.setDefensiveShotResultBreakdownList(new ArrayList<>());
      gameResult.setDefensiveReboundResultBreakdownList(new ArrayList<>());
      gameResult.setFreeThrowResultBreakdown(new FreeThrowResultBreakdown());
      gameResult.getOffensivePossessionResultBreakdownList().add(new PossessionResultBreakdown());
      gameResult.getOffensiveShotResultBreakdownList().add(new ShotResultBreakdown());
      gameResult.getOffensiveReboundResultBreakdownList().add(new ReboundResultBreakdown());
      gameResult.getDefensivePossessionResultBreakdownList().add(new PossessionResultBreakdown());
      gameResult.getDefensiveShotResultBreakdownList().add(new ShotResultBreakdown());
      gameResult.getDefensiveReboundResultBreakdownList().add(new ReboundResultBreakdown());

      assertEquals("gameId", gameResult.getGameId());
      assertEquals(false, gameResult.isConferenceGame());
      assertEquals("opponentId", gameResult.getOpponentId());
      assertEquals("venueId", gameResult.getVenueId());
      assertEquals(startDate, gameResult.getGameDate());
      assertEquals(true, gameResult.getDesignatedHost());
      assertEquals("opponentName", gameResult.getOpponentName());
      assertEquals("conferenceId", gameResult.getOpponentConferenceId());
      assertTrue(gameResult.getOffensivePossessionResultBreakdownList().get(0) instanceof PossessionResultBreakdown);
      assertTrue(gameResult.getOffensiveShotResultBreakdownList().get(0) instanceof ShotResultBreakdown);
      assertTrue(gameResult.getOffensiveReboundResultBreakdownList().get(0) instanceof ReboundResultBreakdown);
      assertTrue(gameResult.getDefensivePossessionResultBreakdownList().get(0) instanceof PossessionResultBreakdown);
      assertTrue(gameResult.getDefensiveShotResultBreakdownList().get(0) instanceof ShotResultBreakdown);
      assertTrue(gameResult.getDefensiveReboundResultBreakdownList().get(0) instanceof ReboundResultBreakdown);
      assertTrue(gameResult.getFreeThrowResultBreakdown() instanceof FreeThrowResultBreakdown);


   }
}
