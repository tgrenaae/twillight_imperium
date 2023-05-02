/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package test;

import main.planet.Planet;
import main.planet.PlanetNames;
import main.planet.ResourceProductionException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    @BeforeAll
    static void startTesting(){
        System.out.println("Starting Planet tests...");
    }

    @BeforeEach
    void setupNewTestGalaxy(){
        System.out.println("Next test...");
    }

    @AfterAll
    static void endingTesting(){
        System.out.println("Tests finished.");
    }

    @Test
    void createPlanet(){
        Planet planet = new Planet(PlanetNames.MECATOL_REX, 6);
        assertNotNull(planet);
    }

    @Test
    void resourceProductionExceptionTest(){
        //only creates planet to be able to call isResourceProductionBounderies() on.
        Planet planet = new Planet(PlanetNames.MECATOL_REX, 6);
        assertThrows(ResourceProductionException.class, ()-> {planet.isResourceProductionBounderies(10);} );
    }
}
