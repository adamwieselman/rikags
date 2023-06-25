package com.rickags.factories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.services.PlayByPlayDataUpdateService;

@Component
public class PlayByPlayDataOrganizerServiceFactory
{
   @Autowired
   protected List<PlayByPlayDataUpdateService> playByPlayDataUpdateServices;

   public PlayByPlayDataUpdateService createPlayByPlayDataOrganizerService (String eventType)
   {
      for(PlayByPlayDataUpdateService playByPlayDataUpdateService : playByPlayDataUpdateServices){
         if(playByPlayDataUpdateService.supports(eventType)){
            return playByPlayDataUpdateService;
         }
      }

      return null;
   }
}
