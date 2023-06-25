package com.rickags.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class PbpFacadeTest
{
   @Test
   public void test_PbpFacade(){

      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("This is the gameDetail");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setTimeStamp("15:35");
      pbp2.setGameHalf("2");

      PbpFacade testPbpFacade = new PbpFacade(pbp);

      assertEquals("1", testPbpFacade.getGameHalf());
      assertEquals("This is the gameDetail", testPbpFacade.getGameDetail());
      assertEquals(new BigInteger("2135"), testPbpFacade.getTime());
      assertEquals("55", testPbpFacade.getOpponent());
      assertFalse(testPbpFacade.isOpponent("55"));
      assertTrue(testPbpFacade.isOpponent("45"));
      assertEquals(new BigInteger("1"), testPbpFacade.getPointsDifference());

      PbpFacade testPbpFacade2 = new PbpFacade(pbp2);
      assertEquals(new BigInteger("935"), testPbpFacade2.getTime());

   }

   @Test
   public void test_PbpFacade_PossessionDiff_noRem()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("67-64");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("1"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_RemNeg()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("64-69");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("-2"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_RemPos()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("69-64");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("2"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_Rem0Pos()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("65-64");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("1"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_Rem0Neg()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("64-66");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("-1"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_GT4Pos()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("78-64");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("4"), testPbpFacade.getPossessionDifference());
   }

   @Test
   public void test_PbpFacade_PossessionDiff_GT4Neg()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setCombinedScore("64-77");
      PbpFacade testPbpFacade = new PbpFacade(pbp);
      assertEquals(new BigInteger("-4"), testPbpFacade.getPossessionDifference());
   }
}
