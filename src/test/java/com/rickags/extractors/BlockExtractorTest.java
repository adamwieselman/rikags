package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class BlockExtractorTest
{
   @Test
   public void testBlockExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Block");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      BlockExtractor blockExtractorPlain = new BlockExtractor();
      BlockExtractor blockExtractor = new BlockExtractor(pbp);
      assertTrue(blockExtractorPlain instanceof BlockExtractor);
      assertTrue( blockExtractor.generateExtractor(pbp) instanceof BlockExtractor);
      assertEquals("END", blockExtractor.getBeginningOrEnding());
      assertEquals("BLOCK", blockExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", blockExtractor.getPlayPlayer());
      assertEquals("BLOCK", blockExtractor.getOverallPlayType());
      assertEquals("BLOCK", blockExtractor.getPlayType());
      assertTrue(blockExtractor.supports("Jimmy Greek Block"));
      assertFalse(blockExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(blockExtractor.getPlayPlayer());
   }
}
