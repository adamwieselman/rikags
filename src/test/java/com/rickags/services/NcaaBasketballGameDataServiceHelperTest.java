package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigInteger;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.models.json.file.Possession;

public class NcaaBasketballGameDataServiceHelperTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   private EspnPlayByPlayExtractionService espnPlayByPlayExtractionService;

   @Mock
   private BasicObjectFactory basicObjectFactory;

   @InjectMocks
   public NcaaBasketballGameDataServiceHelper ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();

   @Test
   public void testDeterminePossesionOwner_BeginOffReb(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("BEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(false);
      possession.getPossessionPlays().add(playTypeInformationExtractor);

      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");
      assertFalse(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossesionOwner_BeginDefReb(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("BEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      possession.getPossessionPlays().add(playTypeInformationExtractor);

      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");
      assertTrue(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossesionOwner_BeginTeamReb(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("BEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      possession.getPossessionPlays().add(playTypeInformationExtractor);

      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");
      assertTrue(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_Nothing(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("STOPPAGE");
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("JUMPBALL");

      possession.getPossessionPlays().add(playTypeInformationExtractor);

      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertFalse(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_FreeThrow(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("END");
      given(playTypeInformationExtractor.getPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("FREETHROW");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertTrue(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_3PTAttempt(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("END");
      given(playTypeInformationExtractor.getPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("3PTATTEMPT");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertTrue(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_2PTJumpAttempt(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("END");
      given(playTypeInformationExtractor.getPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertTrue(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_2PTCloseAttempt(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("END");
      given(playTypeInformationExtractor.getPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertTrue(possession.isOpponentsPossession());
   }


   @Test
   public void testDeterminePossessionOwner_Turnover(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("END");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("TURNOVER");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertTrue(possession.isOpponentsPossession());
   }


   @Test
   public void testDeterminePossessionOwner_Block(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("BLOCK");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertFalse(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_Foul(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("FOUL");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertFalse(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionOwner_Steal(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor2.getOverallPlayType()).willReturn("STEAL");

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);
      ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, "20");

      assertFalse(possession.isOpponentsPossession());
   }

   @Test
   public void testDeterminePossessionTempo(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getTime()).willReturn(new BigInteger("1800"));
      given(playTypeInformationExtractor1.getTime()).willReturn(new BigInteger("1700"));
      given(playTypeInformationExtractor2.getTime()).willReturn(new BigInteger("1650"));

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      ncaaBasketballGameDataServiceHelper.determinePossessionTempo(possession);

      assertEquals(new BigInteger("150"), possession.getPossessionTempo());
   }

   @Test
   public void testSetupPossession(){
      Possession possession = new Possession();
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataServiceHelper.setupPossession(playTypeInformationExtractor, 3);

      assertEquals(4, possession.getPossessionNumber());
   }

   @Test
   public void testDeterminePossessionError_False(){
      Possession possession = new Possession();
      possession.setOpponentsPossession(true);
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor1.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor2.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor1.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor2.getBeginningOrEnding()).willReturn("ENDBEGIN");


      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      given(playTypeInformationExtractor1.isOpponent(Mockito.anyString())).willReturn(true);
      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      ncaaBasketballGameDataServiceHelper.determinePossessionError(possession, "20");

      assertFalse(possession.isPossessionError());
   }

   @Test
   public void testDeterminePossessionError_True(){
      Possession possession = new Possession();
      possession.setOpponentsPossession(true);
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor1.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor2.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor1.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor2.getBeginningOrEnding()).willReturn("ENDBEGIN");

      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      given(playTypeInformationExtractor1.isOpponent(Mockito.anyString())).willReturn(false);
      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      ncaaBasketballGameDataServiceHelper.determinePossessionError(possession, "20");

      assertTrue(possession.isPossessionError());
   }

   @Test
   public void testDeterminePossessionError_False_Foul(){
      Possession possession = new Possession();
      possession.setOpponentsPossession(true);
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor1 = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor playTypeInformationExtractor2 = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor1.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor2.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor1.getBeginningOrEnding()).willReturn("ENDBEGIN");
      given(playTypeInformationExtractor2.getBeginningOrEnding()).willReturn("ENDBEGIN");

      given(playTypeInformationExtractor.isOpponent(Mockito.anyString())).willReturn(true);
      given(playTypeInformationExtractor1.isOpponent(Mockito.anyString())).willReturn(false);
      given(playTypeInformationExtractor2.isOpponent(Mockito.anyString())).willReturn(true);

      possession.getPossessionPlays().add(playTypeInformationExtractor);
      possession.getPossessionPlays().add(playTypeInformationExtractor1);
      possession.getPossessionPlays().add(playTypeInformationExtractor2);

      ncaaBasketballGameDataServiceHelper.determinePossessionError(possession, "20");

      assertFalse(possession.isPossessionError());
   }

   @Test
   public void testIsPlayOk_null(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, null, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Jumpball_TeamDifference(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_EndOf_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_FreeThrowAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Foul_TeamSame(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_Steal(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Turnover_TeamSame(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Steal_TeamDifferent(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("STEAL");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_FreeThrow(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("MISSFREETHROW");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Timeout_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_FreeThrow(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_OffRebound_TeamDifferent(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_Block(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLOCK");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS2PTCLOSE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_TeamReb(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS2PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS3PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }


   @Test
   public void testIsPlayOk_MissShot_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS2PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_TeamDifferent_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS3PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissShot_TeamSame_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISS3PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_TeamReb(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_TeamDifferent_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_TeamSame_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MissFree_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MISSFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_DefRebound_TeamDifferent(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("OFFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("DEFREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_TeamReb(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_TeamSame_OffRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("OFFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_Block_TeamSame_DefRebound(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("DEFREB");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("BLOCK");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_2Pt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_FreeThrow(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_TeamRebound_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("TEAMREB");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE3PTJUMP");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTCLOSE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTJUMP");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTCLOSE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE3PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTCLOSE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE3PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTCLOSE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeShot_TeamSame(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADE2PTJUMP");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_EndOf(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("ENDOF");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_Foul(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("FOUL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_Jumpball(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("JUMPBALL");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_Timeout(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getPlayType()).willReturn("TIMEOUT");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_CloseAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTCLOSEATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_JumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("2PTJUMPATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_3ptJumpAttempt(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("3PTATTEMPT");
      given(playTypeInformationExtractor.getPlayType()).willReturn("NOTHINGGOOD");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_Turnover(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_FreeThrow(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertTrue(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_False(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("BLAH");
      given(playTypeInformationExtractor.getPlayType()).willReturn("BLAH");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_TeamSame(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("TURNOVER");
      given(playTypeInformationExtractor.getPlayType()).willReturn("TURNOVER");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(true);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

   @Test
   public void testIsPlayOk_MadeFreeThrow_TeamDifferent(){
      PlayTypeInformationExtractor playTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      PlayTypeInformationExtractor priorPlayTypeInformationExtractor = mock(PlayTypeInformationExtractor.class);
      given(playTypeInformationExtractor.getOverallPlayType()).willReturn("FREETHROW");
      given(playTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(priorPlayTypeInformationExtractor.getPlayType()).willReturn("MADEFREE");
      given(playTypeInformationExtractor.isOpponent("20")).willReturn(true);
      given(priorPlayTypeInformationExtractor.isOpponent("20")).willReturn(false);
      assertFalse(ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformationExtractor, priorPlayTypeInformationExtractor, "20"));
   }

}
