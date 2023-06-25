package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.facade.EspnEventFacade;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.YearSummary;

public class NcaaBasketballTeamDataServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   JsonMapper jsonMapper;

   @Mock
   DateEvaluationService dateEvaluationService;

   @Mock
   JsonFileScheduleDataManipulatorService jsonFileScheduleDataManipulatorService;

   @Mock
   BasicObjectFactory basicObjectFactory;

   @InjectMocks
   public NcaaBasketballTeamDataService ncaaBasketballTeamDataService = new NcaaBasketballTeamDataService();

   @Test
   public void testRetrieveData_FileExists()
      throws IOException
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      File file = mock(File.class);
      FileInputStream fileInputStream = mock(FileInputStream.class);
      DataInputStream dataInputStream = mock(DataInputStream.class);
      byte[] bytes = "stuff to bytes".getBytes();

      given(espnEventFacade.getTeam1Id()).willReturn("blah");
      given(espnEventFacade.getTeam1Name()).willReturn("blahName");
      given(espnEventFacade.getTeam2Id()).willReturn("blah2");
      given(espnEventFacade.getTeam2Name()).willReturn("blah2Name");
      given(basicObjectFactory.createNcaaTeam()).willReturn(new NcaaTeam());
      given(basicObjectFactory.createFile(Mockito.anyString())).willReturn(file);
      given(file.exists()).willReturn(true);
      given(basicObjectFactory.createFileInputStream(file)).willReturn(fileInputStream);
      given(basicObjectFactory.createDataInputStream(fileInputStream)).willReturn(dataInputStream);
      given(dataInputStream.readAllBytes()).willReturn(bytes);
      given(jsonMapper.deserializeResponse("stuff to bytes", "", NcaaTeam.class)).willReturn(new NcaaTeam());

      NcaaTeam ncaaTeam = ncaaBasketballTeamDataService.retrieveData(espnEventFacade, 2);
      assertNull(ncaaTeam.getTeamId());
      assertNull(ncaaTeam.getTeamName());

   }

   @Test
   public void testRetrieveData_FileDoesntExist()
      throws IOException
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      File file = mock(File.class);

      given(espnEventFacade.getTeam1Id()).willReturn("blah");
      given(espnEventFacade.getTeam1Name()).willReturn("blahName");
      given(basicObjectFactory.createNcaaTeam()).willReturn(new NcaaTeam());
      given(basicObjectFactory.createFile(Mockito.anyString())).willReturn(file);
      given(file.exists()).willReturn(false);

      NcaaTeam ncaaTeam = ncaaBasketballTeamDataService.retrieveData(espnEventFacade, 1);
      assertEquals("blah", ncaaTeam.getTeamId());
      assertEquals("blahName", ncaaTeam.getTeamName());

   }

   @Test
   public void testRetrieveData_FileParam()
      throws IOException
   {

      File file = mock(File.class);
      FileInputStream fileInputStream = mock(FileInputStream.class);
      DataInputStream dataInputStream = mock(DataInputStream.class);
      byte[] bytes = "stuff to bytes".getBytes();
      given(basicObjectFactory.createFileInputStream(file)).willReturn(fileInputStream);
      given(basicObjectFactory.createDataInputStream(fileInputStream)).willReturn(dataInputStream);
      given(dataInputStream.readAllBytes()).willReturn(bytes);
      given(jsonMapper.deserializeResponse("stuff to bytes", "", NcaaTeam.class)).willReturn(new NcaaTeam());

      NcaaTeam ncaaTeam = ncaaBasketballTeamDataService.retrieveData(file);

   }


   @Test
   public void testSaveData()
      throws IOException
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      File file = mock(File.class);
      FileOutputStream fileOutputStream = mock(FileOutputStream.class);
      DataOutputStream dataOutputStream = mock(DataOutputStream.class);
      NcaaTeam ncaaTeam = new NcaaTeam();
      byte[] bytes = "stuff to bytes".getBytes();

      given(espnEventFacade.getTeam1Id()).willReturn("blah");
      given(espnEventFacade.getTeam1Name()).willReturn("blahName");
      given(basicObjectFactory.createFile(Mockito.anyString())).willReturn(file);
      given(basicObjectFactory.createFileOutputStream(file)).willReturn(fileOutputStream);
      given(basicObjectFactory.createDataOutputStream(fileOutputStream)).willReturn(dataOutputStream);
      given(jsonMapper.serializeResponseToJson(ncaaTeam)).willReturn("stuff to bytes");

      ncaaBasketballTeamDataService.saveData(ncaaTeam, espnEventFacade, 1);
      ncaaBasketballTeamDataService.saveData(ncaaTeam, espnEventFacade, 2);
   }

   @Test
   public void testCreateAndAddData()
      throws IOException
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      NcaaTeam ncaaTeam = new NcaaTeam();
      LocalDate startDate = LocalDate.now();

      given(dateEvaluationService.returnSeasonYear(startDate)).willReturn("2022");
      given(jsonFileScheduleDataManipulatorService.prepareYearSummary(ncaaTeam, "2022", espnEventFacade, 1)).willReturn(new YearSummary());
      ncaaBasketballTeamDataService.createAndAddData(ncaaTeam, startDate, espnEventFacade, 1);
   }

}
