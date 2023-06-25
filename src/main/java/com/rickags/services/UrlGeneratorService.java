package com.rickags.services;

import static com.rickags.constants.RickAgsConstants.URL_SCOREBOARD_PREFIX;
import static com.rickags.constants.RickAgsConstants.URL_SCOREBOARD_SUFFIX;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class UrlGeneratorService
{

   public String generateNcaaBasketballScoreboardUrl (LocalDate startDate)
   {
      return URL_SCOREBOARD_PREFIX + startDate.getYear() + String.format("%02d", startDate.getMonthValue()) + String.format("%02d", startDate.getDayOfMonth()) + URL_SCOREBOARD_SUFFIX;
   }
}
