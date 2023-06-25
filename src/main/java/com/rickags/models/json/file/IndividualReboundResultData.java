package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndividualReboundResultData
{
   private String playPlayer;
   private BigInteger individualRebounds;
   private BigDecimal individualReboundPercentage;

   public String getPlayPlayer ()
   {
      return playPlayer;
   }

   public void setPlayPlayer (String playPlayer)
   {
      this.playPlayer = playPlayer;
   }

   public BigInteger getIndividualRebounds ()
   {
      return individualRebounds;
   }

   public void setIndividualRebounds (BigInteger individualRebounds)
   {
      this.individualRebounds = individualRebounds;
   }

   public BigDecimal getIndividualReboundPercentage ()
   {
      return individualReboundPercentage;
   }

   public void setIndividualReboundPercentage (BigDecimal individualReboundPercentage)
   {
      this.individualReboundPercentage = individualReboundPercentage;
   }
}