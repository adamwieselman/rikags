package com.rickags.models.json.file;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.rickags.extractors.PlayTypeInformationExtractor;

public class Possession
{

   private int possessionNumber;
   private boolean isOpponentsPossession;
   private boolean isPossessionError;
   private BigInteger possessionTempo;
   private List<PlayTypeInformationExtractor> possessionPlays;

   public Possession ()
   {
      this.possessionPlays = new ArrayList<>();
   }

   public List<PlayTypeInformationExtractor> getPossessionPlays ()
   {
      return possessionPlays;
   }

   public void setPossessionPlays (List<PlayTypeInformationExtractor> possessionPlays)
   {
      this.possessionPlays = possessionPlays;
   }

   public int getPossessionNumber ()
   {
      return possessionNumber;
   }

   public void setPossessionNumber (int possessionNumber)
   {
      this.possessionNumber = possessionNumber;
   }

   public boolean isOpponentsPossession ()
   {
      return isOpponentsPossession;
   }

   public boolean isPossessionError ()
   {
      return isPossessionError;
   }

   public void setOpponentsPossession (boolean opponentsPossession)
   {
      isOpponentsPossession = opponentsPossession;
   }

   public BigInteger getPossessionTempo ()
   {
      return possessionTempo;
   }

   public void setPossessionTempo (BigInteger possessionTempo)
   {
      this.possessionTempo = possessionTempo;
   }

   public void setPossessionError (boolean possessionError)
   {
      isPossessionError = possessionError;
   }
}
