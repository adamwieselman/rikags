package com.rickags.factories;

import static junit.framework.TestCase.assertTrue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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

public class BasicObjectFactoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    public BasicObjectFactory basicObjectFactory = new BasicObjectFactory();

    @Test
    public void testCreateJsonMapper() {
        assertTrue(basicObjectFactory.createJsonMapper() instanceof JsonMapper);
    }

    @Test
    public void testCreateFile(){
        assertTrue(basicObjectFactory.createFile("blah") instanceof File);
    }

    @Test
    public void testCreateRequestContext(){
        assertTrue(basicObjectFactory.createRequestContext() instanceof RequestContext);
    }

    @Test
    public void testCreateKeyValueMap(){
        assertTrue(basicObjectFactory.createKeyValueMap() instanceof Map);
    }

    @Test
    public void testCreateKeyObjectMap(){assertTrue(basicObjectFactory.createKeyObjectMap() instanceof Map);}

    @Test
    public void testCreateGameDataInfo() {assertTrue(basicObjectFactory.createGameDataInfo() instanceof GameData);}

    @Test
    public void testCreateVenue() {assertTrue(basicObjectFactory.createVenue() instanceof Venue);}

    @Test
    public void testCreateTeam() {assertTrue(basicObjectFactory.createTeam() instanceof Team);}

    @Test
    public void testCreateConference() {assertTrue(basicObjectFactory.createConference() instanceof Conference);}

    @Test
    public void testCreateGameDataInfoTeam() {assertTrue(basicObjectFactory.createGameDataInfoTeam() instanceof GameDataTeam);}

    @Test
    public void testCreateTeamVenue() {assertTrue(basicObjectFactory.createTeamVenue() instanceof TeamVenue);}

    @Test
    public void testCreateConferenceTeam() {assertTrue(basicObjectFactory.createConferenceTeam() instanceof ConferenceTeam);}

    @Test
    public void testCreateConferenceList() {assertTrue(basicObjectFactory.createConferenceList() instanceof List<Conference>);}

    @Test
    public void testCreateTeamList() {assertTrue(basicObjectFactory.createTeamList() instanceof List<Team>);}

    @Test
    public void testCreateNcaaTeamList() {assertTrue(basicObjectFactory.createNcaaTeam() instanceof NcaaTeam);}

    @Test
    public void testCreatePossession() {assertTrue(basicObjectFactory.createPossession() instanceof Possession);}

    @Test
    public void testCreateEspnPlayByPlays() {assertTrue(basicObjectFactory.createEspnPlayByPlays() instanceof List<EspnPlayByPlay>);}

    @Test
    public void testCreateEspnPlayByPlay() {assertTrue(basicObjectFactory.createEspnGamePlayByPlay() instanceof EspnGamePlayByPlay);}

    @Test
    public void testCreateEspnEventFacadeList() {
        assertTrue(basicObjectFactory.createEspnEventFacade(new EspnEvent()) instanceof EspnEventFacade);
    }

    @Test
    public void testCreateFileInputStreamAndDataInputStream() throws FileNotFoundException {
        File file = basicObjectFactory.createFile("C:\\Users\\Owner\\Downloads\\football.xlsx");
        try(FileInputStream fis = basicObjectFactory.createFileInputStream(file)){
            assertTrue(fis instanceof FileInputStream);
            assertTrue(basicObjectFactory.createDataInputStream(fis) instanceof DataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateFileOutputStreamAndDataOutputStream() throws FileNotFoundException {
        File file = basicObjectFactory.createFile("C:\\Users\\Owner\\Downloads\\football.xlsx");
        try(FileOutputStream fis = basicObjectFactory.createFileOutputStream(file)){
            assertTrue(fis instanceof FileOutputStream);
            assertTrue(basicObjectFactory.createDataOutputStream(fis) instanceof DataOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

