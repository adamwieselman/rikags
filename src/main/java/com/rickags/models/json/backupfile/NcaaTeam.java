package com.rickags.models.json.backupfile;

import java.util.ArrayList;
import java.util.List;

public class NcaaTeam
{
   private String teamId;
   private String teamName;
   private List<YearSummary> yearSummaries;

   public NcaaTeam(){
      yearSummaries = new ArrayList<>();
   }

   public String getTeamId ()
   {
      return teamId;
   }

   public void setTeamId (String teamId)
   {
      this.teamId = teamId;
   }

   public String getTeamName ()
   {
      return teamName;
   }

   public void setTeamName (String teamName)
   {
      this.teamName = teamName;
   }

   public List<YearSummary> getYearSummaries ()
   {
      return yearSummaries;
   }

   public void setYearSummaries (List<YearSummary> yearSummaries)
   {
      this.yearSummaries = yearSummaries;
   }
}
