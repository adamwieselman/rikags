package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

import com.rickags.extractors.JumpballExtractor;
import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class PossessionTest
{
   @Test
   public void testPossession()
   {
      Possession possession = new Possession();
      possession.setPossessionNumber(1);
      possession.setOpponentsPossession(true);
      possession.setPossessionTempo(new BigInteger("2"));
      possession.setPossessionPlays(new ArrayList<>());
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      JumpballExtractor playTypeInformationExtractor = new JumpballExtractor(pbp);
      possession.getPossessionPlays().add(playTypeInformationExtractor);

      assertEquals(1, possession.getPossessionNumber());
      assertTrue(possession.isOpponentsPossession());
      assertEquals(new BigInteger("2"), possession.getPossessionTempo());
      assertTrue(possession.getPossessionPlays().get(0) instanceof PlayTypeInformationExtractor);
   }
}
