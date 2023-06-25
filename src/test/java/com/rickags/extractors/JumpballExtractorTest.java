package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class JumpballExtractorTest
{

   private static String JUMPBALL = "Jump Ball ";

   @Test
   public void testJumpballExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jump Ball won by Wisconsin");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      JumpballExtractor jumpballExtractor = new JumpballExtractor(pbp);
      JumpballExtractor jumpballExtractor1 = new JumpballExtractor();
      assertTrue( jumpballExtractor.generateExtractor(pbp) instanceof JumpballExtractor);
      assertTrue( jumpballExtractor1 instanceof JumpballExtractor);
      assertEquals("BEGIN", jumpballExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", jumpballExtractor.getPriorPlayType());
      assertEquals(null, jumpballExtractor.getPlayPlayer());
      assertEquals("JUMPBALL", jumpballExtractor.getOverallPlayType());
      assertEquals("JUMPBALL", jumpballExtractor.getPlayType());
      assertTrue(jumpballExtractor.supports("Jump Ball won by Wisconsin"));
      assertFalse(jumpballExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(jumpballExtractor.getPlayPlayer());
   }
}
