package com.rickags.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.services.NcaaBasketballScheduleDataUpdateService;
import com.rickags.services.ScheduleDataUpdateService;

public class ScheduleDataUpdateServiceFactoryTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public ScheduleDataUpdateServiceFactory scheduleDataUpdateServiceFactory = new ScheduleDataUpdateServiceFactory();

   @Test
   public void testCreateScheduleDataUpdateService_foundScheduleDataUpdateService ()
   {
      List<ScheduleDataUpdateService> scheduleDataUpdateServiceList = new ArrayList<>();
      ScheduleDataUpdateService scheduleDataUpdateService = new NcaaBasketballScheduleDataUpdateService();
      scheduleDataUpdateServiceList.add(scheduleDataUpdateService);

      scheduleDataUpdateServiceFactory.scheduleDataUpdateServices = scheduleDataUpdateServiceList;
      ScheduleDataUpdateService scheduleDataUpdateServiceChosen = scheduleDataUpdateServiceFactory.createScheduleDataUpdateService("NCAABB");
      assertEquals(scheduleDataUpdateServiceChosen, scheduleDataUpdateService);
   }

   @Test
   public void testCreateScheduleDataUpdateService_noServiceFound ()
   {
      List<ScheduleDataUpdateService> scheduleDataUpdateServiceList = new ArrayList<>();
      ScheduleDataUpdateService scheduleDataUpdateService = new NcaaBasketballScheduleDataUpdateService();
      scheduleDataUpdateServiceList.add(scheduleDataUpdateService);

      scheduleDataUpdateServiceFactory.scheduleDataUpdateServices = scheduleDataUpdateServiceList;
      assertNull(scheduleDataUpdateServiceFactory.createScheduleDataUpdateService("OTHER"));
   }
}
