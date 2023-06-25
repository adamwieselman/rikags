package com.rickags.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.rickags.validators.RickagsError;

public class ResponseContext implements Serializable
{
   LocalDate startDate;
   List<RickagsError> errors;
   List<TeamEspnPlayByPlay> teamEspnPlayByPlayList;

   public ResponseContext ()
   {
      this.errors = new ArrayList<>();
      this.teamEspnPlayByPlayList = new ArrayList<>();
   }

   public List<RickagsError> getErrors ()
   {
      return errors;
   }

   public void setErrors (List<RickagsError> errors)
   {
      this.errors = errors;
   }

   public LocalDate getStartDate ()
   {
      return startDate;
   }

   public void setStartDate (LocalDate startDate)
   {
      this.startDate = startDate;
   }

   public List<TeamEspnPlayByPlay> getTeamEspnPlayByPlayList ()
   {
      return teamEspnPlayByPlayList;
   }

   public void setTeamEspnPlayByPlayList (List<TeamEspnPlayByPlay> teamEspnPlayByPlayList)
   {
      this.teamEspnPlayByPlayList = teamEspnPlayByPlayList;
   }

   public boolean hasResponseErrors(){
      return errors.size()>0;
   }
}
