package com.rickags.models.json.file;

import java.util.ArrayList;
import java.util.List;

public class NcaaTeam
{
   private String teamId;
   private String teamName;
   private List<YearSummary> yearSummaries;
   private List<StatsBreakdown> statsBreakdown;

   public NcaaTeam(){
      yearSummaries = new ArrayList<>();
      statsBreakdown = new ArrayList<>();
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

   public List<StatsBreakdown> getStatsBreakdown ()
   {
      return statsBreakdown;
   }

   public void setStatsBreakdown (List<StatsBreakdown> statsBreakdown)
   {
      this.statsBreakdown = statsBreakdown;
   }
}
