package com.rickags.services;

import static com.rickags.constants.RickAgsConstants.FILE_LOCATION_NCAABBDATA_JSON_SUFFIX;
import static com.rickags.constants.RickAgsConstants.FILE_LOCATION_NCAABBDATA_PREFIX;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.facade.EspnEventFacade;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.YearSummary;

@Component
public class NcaaBasketballTeamDataService
{

   @Autowired
   JsonMapper jsonMapper;

   @Autowired
   DateEvaluationService dateEvaluationService;

   @Autowired
   JsonFileScheduleDataManipulatorService jsonFileScheduleDataManipulatorService;

   @Autowired
   BasicObjectFactory basicObjectFactory;

   public NcaaTeam retrieveData (EspnEventFacade espnEventFacade, Integer eventCompetitorNumber)
      throws IOException
   {
      String teamId;
      String teamName;

      if(eventCompetitorNumber == 1){
         teamId = espnEventFacade.getTeam1Id();
         teamName = espnEventFacade.getTeam1Name();
      }else{
         teamId = espnEventFacade.getTeam2Id();
         teamName = espnEventFacade.getTeam2Name();
      }

      File file = basicObjectFactory.createFile(FILE_LOCATION_NCAABBDATA_PREFIX + teamId + FILE_LOCATION_NCAABBDATA_JSON_SUFFIX);

      if (file.exists()) {

         String json = null;
         //Instantiating the FileInputStream class
         try (FileInputStream fileIn = basicObjectFactory.createFileInputStream(file)) {
            //Instantiating the DataInputStream class
            try (DataInputStream inputStream = basicObjectFactory.createDataInputStream(fileIn)){
               //Reading UTF data from the DataInputStream
               json = new String(inputStream.readAllBytes(), "UTF-8");
            }
         }
         return jsonMapper.deserializeResponse(json, "", NcaaTeam.class);

      }else{
         System.out.println("creating file for " + teamName);
         NcaaTeam ncaaTeam = basicObjectFactory.createNcaaTeam();
         ncaaTeam.setTeamId(teamId);
         ncaaTeam.setTeamName(teamName);

         return ncaaTeam;
      }
   }

   public NcaaTeam retrieveData (File file)
      throws IOException
   {
      String json = null;
      //Instantiating the FileInputStream class
      try(FileInputStream fileIn = basicObjectFactory.createFileInputStream(file)) {
         //Instantiating the DataInputStream class
         try(DataInputStream inputStream = basicObjectFactory.createDataInputStream(fileIn)) {
            //Reading UTF data from the DataInputStream
            json = new String(inputStream.readAllBytes(),"UTF-8");
         }
      }

      NcaaTeam ncaaTeam = jsonMapper.deserializeResponse(json, "", NcaaTeam.class);
      
      return ncaaTeam;
   }

   public void saveData (NcaaTeam ncaaTeam,
                         EspnEventFacade espnEventFacade,
                         Integer eventCompetitorNumber)
      throws IOException
   {
      String teamId;

      if(eventCompetitorNumber == 1){
         teamId = espnEventFacade.getTeam1Id();
      }else{
         teamId = espnEventFacade.getTeam2Id();
      }

      File file = basicObjectFactory.createFile(FILE_LOCATION_NCAABBDATA_PREFIX + teamId + FILE_LOCATION_NCAABBDATA_JSON_SUFFIX);

      try (FileOutputStream output = basicObjectFactory.createFileOutputStream(file)){
         try(DataOutputStream outputStream = basicObjectFactory.createDataOutputStream(output)) {
            outputStream.write(jsonMapper.serializeResponseToJson(ncaaTeam).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
         }
      }
   }

   public void createAndAddData (NcaaTeam ncaaTeam,
                                 LocalDate startDate,
                                 EspnEventFacade espnEventFacade,
                                 int eventCompetitorNumber)
   {
      String year = dateEvaluationService.returnSeasonYear(startDate);
      YearSummary yearSummary = jsonFileScheduleDataManipulatorService.prepareYearSummary(ncaaTeam, year, espnEventFacade, eventCompetitorNumber);
      jsonFileScheduleDataManipulatorService.prepareGameResult(yearSummary, startDate, espnEventFacade, eventCompetitorNumber);
   }

}
