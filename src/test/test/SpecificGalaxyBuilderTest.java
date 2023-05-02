/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package test;

import main.galaxy.Galaxy;
import main.galaxyBuilder.GalaxyBuilder;
import main.galaxyBuilder.SpecificGalaxyBuilder;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpecificGalaxyBuilderTest {

    @BeforeAll
    static void startTesting(){
        System.out.println("Starting SpecificGalaxyBuilder tests...");
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
    void createSpecificGalaxyBuilderTest(){
        GalaxyBuilder galaxyBuilder = new SpecificGalaxyBuilder();
        assertNotNull(galaxyBuilder);
    }

    @Test
    void createGalaxyTest(){
        GalaxyBuilder galaxyBuilder = new SpecificGalaxyBuilder();
        Assertions.assertTrue(galaxyBuilder.createGalaxy() instanceof Galaxy);
    }
}
