package com.rickags.factories;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rickags.beans.RequestContext;
import com.rickags.facade.EspnEventFacade;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.Conference;
import com.rickags.models.ConferenceTeam;
import com.rickags.models.GameData;
import com.rickags.models.GameDataTeam;
import com.rickags.models.Team;
import com.rickags.models.TeamVenue;
import com.rickags.models.Venue;
import com.rickags.models.json.espn.ncabb.EspnEvent;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.Possession;

@Component
public class BasicObjectFactory {

    public RequestContext createRequestContext() {
        return new RequestContext();
    }

    public JsonMapper createJsonMapper() {
        return new JsonMapper();
    }

    public File createFile(String filePath){
        return new File(filePath);
    }

    public Map<String, String> createKeyValueMap() {
        return new HashMap<>();
    }

    public FileInputStream createFileInputStream(File myFile) throws FileNotFoundException {
        return new FileInputStream(myFile);
    }

    public Map<String, Object> createKeyObjectMap() {
        return new HashMap<>();
    }

    public GameData createGameDataInfo() {
        return new GameData();
    }

    public Venue createVenue() {
        return new Venue();
    }

    public Team createTeam() { return new Team(); }

    public Conference createConference() { return new Conference();
    }

    public GameDataTeam createGameDataInfoTeam() {return new GameDataTeam();}

    public TeamVenue createTeamVenue() {return new TeamVenue();}

    public ConferenceTeam createConferenceTeam() {return new ConferenceTeam();}

    public List<Conference> createConferenceList() {
        return new ArrayList<>();
    }

    public List<Team> createTeamList() {
        return new ArrayList<>();
    }

    public EspnEventFacade createEspnEventFacade (EspnEvent espnEvent)
    {
        return new EspnEventFacade(espnEvent);
    }

   public DataInputStream createDataInputStream (FileInputStream fileIn)
   {
       return new DataInputStream(fileIn);
   }

    public NcaaTeam createNcaaTeam ()
    {
        return new NcaaTeam();
    }

    public FileOutputStream createFileOutputStream (File file)
       throws FileNotFoundException
    {
        return new FileOutputStream(file);
    }

    public DataOutputStream createDataOutputStream (FileOutputStream output)
    {
        return new DataOutputStream(output);
    }

   public Possession createPossession ()
   {
      return new Possession();
   }

   public EspnGamePlayByPlay createEspnGamePlayByPlay()
   {
      return new EspnGamePlayByPlay();
   }

   public List<EspnPlayByPlay> createEspnPlayByPlays()
   {
      return new ArrayList<>();
   }
}

