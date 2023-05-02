/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package test;

import main.planet.Planet;
import main.planet.PlanetNames;
import main.system.IllegalSystemException;
import main.system.System;
import main.units.Units;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SystemTest {
    @BeforeAll
    static void startTesting(){
        java.lang.System.out.println("Starting System tests...");
    }

    @BeforeEach
    void setupNewTestGalaxy(){
        java.lang.System.out.println("Next test...");
    }

    @AfterAll
    static void endingTesting(){
        java.lang.System.out.println("Tests finished.");
    }

    @Test
    void createSystemTest(){
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();
        try{
            System system = new System(planets,units);
            assertNotNull(system);
        } catch (IllegalSystemException e){

        }
    }

    @Test
    void illegalSystemExceptionTest(){
        List<Planet> planets = new ArrayList<>();
        Planet mecatolRex = new Planet(PlanetNames.MECATOL_REX, 6);
        Planet industrex = new Planet(PlanetNames.INDUSTREX, 5);
        Planet hopesEnd = new Planet(PlanetNames.HOPES_END, 3);
        Planet velnor = new Planet(PlanetNames.VELNOR, 4);
        Collections.addAll(planets, mecatolRex, industrex, hopesEnd, velnor);
        List<Units> units = new ArrayList<>();

        assertThrows(IllegalSystemException.class, ()-> new System(planets,units));
    }

}
