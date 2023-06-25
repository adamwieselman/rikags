package com.rickags.facade;

import com.rickags.models.json.espn.ncabb.EspnEvent;

public class EspnEventFacade
{
   private EspnEvent espnEvent;

   public EspnEventFacade (EspnEvent espnEvent)
   {
      this.espnEvent = espnEvent;
   }

   public String getEventStatus ()
   {
      return espnEvent.getEspnStatus().getEspnType().getName();
   }

   public String getEventId ()
   {
      return espnEvent.getId();
   }

   public String getEventVenueId ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnVenue().getId();
   }

   public String getTeam1Score ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getScore();
   }

   public String getTeam2Score ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getScore();
   }

   public String getTeam1Id ()
{
   return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getId();
}

   public String getTeam1Name ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getLocation();
   }

   public String getTeam1Conference ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getConferenceId();
   }

   public String getTeam1ArenaId ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(0).getEspnTeam().getEspnVenue().getId();
   }

   public String getTeam2Id ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getId();
   }

   public String getTeam2Name ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getLocation();
   }

   public String getTeam2Conference ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getConferenceId();
   }

   public String getTeam2ArenaId ()
   {
      return espnEvent.getEspnCompetitions().get(0).getEspnCompetitors().get(1).getEspnTeam().getEspnVenue().getId();
   }

   public boolean isConferenceGame()
   {
      return espnEvent.getEspnCompetitions().get(0).isConferenceCompetition();
   }

   public boolean isDivisionOneGame ()
   {
      return  getTeam1Conference() != null && getTeam2Conference() != null;
   }
}
