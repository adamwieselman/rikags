package com.rickags.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class WelcomeControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    public WelcomeController welcomeController = new WelcomeController();

    @Test
    public void testIndex_happyPath() {

        String response = welcomeController.index();
        assertEquals("Greetings from Spring Boot!", response);
    }

}

