/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main;

import main.galaxyBuilder.GalaxyBuilder;
import main.galaxyBuilder.RandomGalaxyBuilder;
import main.galaxyBuilder.SpecificGalaxyBuilder;
import main.galaxy.Galaxy;
import main.system.SystemPositions;
import main.units.Units;

//Demo class to show a few of the functionalities implemented.
public class Demo {
    public static void main(String[] args) {

        GalaxyBuilder specificGalaxyBuilder = new SpecificGalaxyBuilder();
        Galaxy specificGalaxy = specificGalaxyBuilder.createGalaxy();

        Units specificUnit = specificGalaxy.getAllShipsOwnedByPlayer(specificGalaxy.getPlayers().get(1)).get(0);
        specificGalaxy.moveUnit(specificUnit, SystemPositions.NORTH, SystemPositions.NORTH_EAST);

        specificGalaxy.writePlanetaryControl();

        RandomGalaxyBuilder randomGalaxyBuilder = new RandomGalaxyBuilder();
        Galaxy randomGalaxy = randomGalaxyBuilder.createGalaxy();
        System.out.println("Random Galaxy legality is: " + randomGalaxy.isLegalGalaxy());
    }
}
