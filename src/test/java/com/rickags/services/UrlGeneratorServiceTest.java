package com.rickags.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UrlGeneratorServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public UrlGeneratorService urlGeneratorService = new UrlGeneratorService();

   @Test
   public void generateNcaaBasketballScoreboardUrl ()
   {
      LocalDate testDate = LocalDate.of(2022, 10, 12);
      assertEquals("http://cdn.espn.com/core/mens-college-basketball/scoreboard/_/group/50/date/20221012?xhr=1&render=true&device=desktop&country=us&lang=en&region=us&site=espn&edition-host=espn.com&one-site=true&site-type=full", urlGeneratorService.generateNcaaBasketballScoreboardUrl(testDate));
   }
}
