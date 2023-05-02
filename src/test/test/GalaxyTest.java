/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package test;

import main.galaxy.Galaxy;
import main.galaxy.LegalGalaxyException;
import main.galaxyBuilder.GalaxyBuilder;
import main.galaxyBuilder.SpecificGalaxyBuilder;
import main.galaxyBuilder.TestGalaxyBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//Problem 8
class GalaxyTest {

    @BeforeAll
    static void startTesting(){
        System.out.println("Starting Galaxy tests...");
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
    void isLegalGalaxyTrueTest(){
        GalaxyBuilder specificGalaxyBuilder = new SpecificGalaxyBuilder();
        Galaxy newTestGalaxy = specificGalaxyBuilder.createGalaxy();
        assert(newTestGalaxy.isLegalGalaxy());
        assertEquals(true, newTestGalaxy.isLegalGalaxy());
    }

    @Test
    void islegalGalaxyFalseTest(){
        //the TestGalaxyBuilder will on purpose return an illegal Galaxy
        GalaxyBuilder testGalaxyBuilder = new TestGalaxyBuilder();
        Galaxy newTestGalaxy = testGalaxyBuilder.createGalaxy();
        assertEquals(false, newTestGalaxy.isLegalGalaxy());
    }

    @Test
    void LegalGalaxyExceptionMecatolRexTest(){
        //the TestGalaxyBuilder will on purpose return an illegal Galaxy
        GalaxyBuilder testGalaxyBuilder = new TestGalaxyBuilder();
        Galaxy newTestGalaxy = testGalaxyBuilder.createGalaxy();
        assertThrows(LegalGalaxyException.class, ()-> {newTestGalaxy.isMecatolRex();});
    }

    @Test
    void LegalGalaxyExceptionUniquePlanetsTest(){
        //the TestGalaxyBuilder will on purpose return an illegal Galaxy
        GalaxyBuilder testGalaxyBuilder = new TestGalaxyBuilder();
        Galaxy newTestGalaxy = testGalaxyBuilder.createGalaxy();
        assertThrows(LegalGalaxyException.class, ()-> {newTestGalaxy.isUniquePlanets();});
    }
}