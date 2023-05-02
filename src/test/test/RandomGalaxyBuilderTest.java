/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package test;

import main.galaxy.Galaxy;
import main.galaxyBuilder.GalaxyBuilder;
import main.galaxyBuilder.RandomGalaxyBuilder;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomGalaxyBuilderTest {

    @BeforeAll
    static void startTesting(){
        System.out.println("Starting RandomGalaxyBuilder tests...");
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
    void newRandomGalaxyBuilderTest(){
        GalaxyBuilder galaxyBuilder = new RandomGalaxyBuilder();
        assertNotNull(galaxyBuilder);
    }

    @Test
    void createNewGalaxyTest(){
        GalaxyBuilder galaxyBuilder = new RandomGalaxyBuilder();
        Assertions.assertTrue(galaxyBuilder.createGalaxy() instanceof Galaxy);
    }
}
