/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.galaxyBuilder;

import main.galaxy.Galaxy;
import main.system.IllegalSystemException;

/**
 * The {@GalaxyBuilder} interface represents the steps needed to create a {@link Galaxy}
 *
 * @author Tommy Grenaae
 * @see Galaxy
 * @see SpecificGalaxyBuilder
 * @see RandomGalaxyBuilder
 */
//Problem 7
public interface GalaxyBuilder {
    Galaxy createGalaxy();

    void reset();

    void createPlayers();

    void createCenterSystem() throws IllegalSystemException;

    void createOtherSystems() throws IllegalSystemException;

    void setNeighborSystems();
}
