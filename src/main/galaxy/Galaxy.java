/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.galaxy;

import main.planet.Planet;
import main.planet.PlanetNames;
import main.player.Player;
import main.system.System;
import main.system.SystemPositions;
import main.units.CombatValueComparator;
import main.units.Units;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The {@code Galaxy} class represents the galaxy where Twilight Imperium takes place.
 * A {@Galaxy} will contain all the {@link System systems} of the galaxy, as well as
 * the {@link Player players} in the game.
 *
 * <p>
 *     The {@code Galaxy} does not have a contructor, but is in stead created
 *     by using a {@link main.galaxyBuilder.GalaxyBuilder} to create a specific
 *     type of galaxy (either {@link main.galaxyBuilder.SpecificGalaxyBuilder specific}
 *     or {@link main.galaxyBuilder.RandomGalaxyBuilder random}. Once a {@code Galaxy} is
 *     created, information about players and the systems are not mutable.
 * </p>
 *
 * Example of how to create a specific {@code Galaxy}:
 * <blockquote><pre>
 *      SpecificGalaxyBuilder specificGalaxyBuilder = new SpecificGalaxyBuilder();
 *      Galaxy galaxy = specificGalaxyBuilder.createGalaxy();
 * </pre></blockquote>
 *
 * @author Tommy Grenaae
 * @see main.galaxyBuilder.GalaxyBuilder
 * @see main.galaxyBuilder.SpecificGalaxyBuilder
 * @see main.galaxyBuilder.RandomGalaxyBuilder
 * @see System
 * @see Player
 */
//Problem 6
public class Galaxy {
    /**
     * {@code systemHexagonGrid} represents the {@link System systems} of the {@code Galaxy}
     * mapped by their {@link SystemPositions}.
     */
    private Map<SystemPositions, System> systemHexagonGrid = new EnumMap<>(SystemPositions.class);

    /**
     * {@code players} represents all the players that are playing in the {@code Galaxy}
     */
    private List<Player> players;

    /**
     * Getter for the players in the Galaxy.
     * @return a {@code List<{@link Player}>} with the players of the {@code Galaxy}
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Setter for the players in the Galaxy. Only used by the {@link main.galaxyBuilder.GalaxyBuilder}.
     * @param players , a list of {@link Player players} who are playing the game.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Getter for all the {@link System systems} of the {@code Galaxy}.
     * @return a {@code Map<SystemPositions, System>} with all systems in the {@code Galaxy}
     * mapped to their {@link SystemPositions}.
     */
    public Map<SystemPositions, System> getAllSystems(){
        return this.systemHexagonGrid;
    }

    /**
     * Getter for a specific {@link System} in the {@code Galaxy}.
     * @param position , a {@link SystemPositions system position} enum value
     *                 corresponding to the system's position in the {@code Galaxy}.
     * @return a {@link System} that are mapped to the {@code position}.
     */
    public System getSystemByPosition(SystemPositions position){
        return this.getAllSystems().get(position);
    }

    /**
     * Getter for all the {@link Planet planets} in the {@code Galaxy}.
     * @return a list of {@code Planet} in the {@code Galaxy}.
     */
    public List<Planet> getAllPlanets(){
        List<Planet> allPlanets = new ArrayList<>();

        for (System systems: this.getAllSystems().values()) {
            allPlanets.addAll(systems.getPlanetsInSystem());
        }
        return allPlanets;
    }

    /**
     * Getter for alle the {@link Units} in the {@code Galaxy}.
     * @return a list of {@code Units} in the {@code Galaxy}.
     */
    public List<Units> getAllUnits(){
        List<Units> allUnits = new ArrayList<>();

        for (System systems: this.getAllSystems().values()){
            allUnits.addAll(systems.getUnitsInSystem());
        }

        return allUnits;
    }

    /**
     * Getter for all the {@link Units} owned by a given {@link Player}.
     * @param player , target {@link Player} to find {@code Units} for.
     * @return a list of {@code Units}.
     */
    //Problem 10
    public List<Units> getAllShipsOwnedByPlayer(Player player){
        List<Units> allUnitsOwnedByPlayer = new ArrayList<>();

        for (System systems: this.getAllSystems().values()) {
            for (Units unit: systems.getUnitsInSystem()) {
                if(unit.getPlayer() == player){
                    allUnitsOwnedByPlayer.add(unit);
                }
            }
        }
        Collections.sort(allUnitsOwnedByPlayer, new CombatValueComparator());

        return allUnitsOwnedByPlayer;
    }

    /**
     * check if a galaxy forfills all the criteria of legality.
     * Will catch a {@link LegalGalaxyException} if a criteria is not met.
     * @return boolean value to indicate if galaxy is legal
     */
    //Problem 9
    public boolean isLegalGalaxy(){
        try{
            this.isMecatolRex();
            this.isUniquePlanets();
            this.isAtMostThreePlanetsInSystems();
        }catch (LegalGalaxyException e){
            java.lang.System.out.println("legal galaxy criteria error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * checks first criteria of legality (Mecatol Rex is the only {@link Planet} in
     * the center system of the {@link Galaxy}.
     * throws an {@link LegalGalaxyException} if the Mecatol Rex planet is not in the middle
     * or if there are more than one planet called Mecatol Rex
     */
    public void isMecatolRex(){
        int numberOfMecatolRex = 0;
        List<Planet> centerPlanets = this.getSystemByPosition(SystemPositions.CENTER).getPlanetsInSystem();

        for (Planet planet: centerPlanets) {
            if(planet.getPlanetName() != PlanetNames.MECATOL_REX) {
                throw new LegalGalaxyException("Mecatol is not the only planet in center system.");
            } else{
                numberOfMecatolRex++;
            }
        }
        if (numberOfMecatolRex != 1){
            throw new LegalGalaxyException("Multiple Mecatol Rex planets in system.");
        }
    }

    /**
     * checks the second criteria of legality (if the {@link Planet planets} are unique.
     * throws an {@link LegalGalaxyException} if planets are not unique
     */
    public void isUniquePlanets(){
        List<Planet> allPlanets = new ArrayList<>();

        for (Planet planet: this.getAllPlanets()) {
            for (Planet planetInAllPlanets: allPlanets) {
                if(planet.getPlanetName() == planetInAllPlanets.getPlanetName()){
                    throw new LegalGalaxyException("Unique planet criteria not met.");
                }
            }
            allPlanets.add(planet);
        }
    }

    /**
     * checks the third criteria of legality (there are between 0 and 3 {@link Planet planets}
     * in every {@link System} in the {@code Galaxy}.
     * throws an error if there are at more than three planets in the system
     */
    public void isAtMostThreePlanetsInSystems(){
        for (System systems: this.getAllSystems().values()) {
            if(systems.getPlanetsInSystem().size() > 3){
                throw new LegalGalaxyException("At most three planets criteria not met.");
            }
        }
    }

    /**
     * writes the planetary control of each {@link Player} to a txt-file.
     */
    //Problem 11
    public void writePlanetaryControl(){
        try {

            ArrayList<Planet> planets = new ArrayList<>();
            File planetaryControl = new File("planetaryControl.txt");
            FileWriter fileWriter = new FileWriter(planetaryControl);

            for (Player player: this.getPlayers()) {
                for (System system: this.getAllSystems().values()) {
                    int numberOfPlayerUnits = 0;
                    for (Units unit: system.getUnitsInSystem()) {
                        if(unit.getPlayer() == player){
                            numberOfPlayerUnits++;
                        }
                        if(numberOfPlayerUnits == system.getUnitsInSystem().size()){
                            planets.addAll(system.getPlanetsInSystem());
                        }
                    }
                }
                fileWriter.write(player.getColor().toString() + " player named " + player.getName() +
                                " plays "+ player.getRace() + " and have planetary control over \n");
                for (Planet planet: planets) {
                    fileWriter.write(planet.getPlanetName().toString() + "\n");
                }
                planets.clear();
            }
            fileWriter.close();
            java.lang.System.out.println("Planetary control written to file.");
        } catch (IOException error){
            error.printStackTrace();
        }
    }

    /**
     * Method represents a player moving a unit from a {@link System} to another. returns true if move is legal,
     * and false if not.
     * @param unit , the {@link Units Unit} the {@link Player} wishes to move.
     * @param fromSystemPosition , a {@link SystemPositions} enum value indicating where the unit is moving from.
     * @param toSystemPosition  ,  a {@link SystemPositions} enum value indicating where the unit is moving to.
     * @return a {@code boolean} (true is move was legal, false if not).
     */
    //Problem 15
    public boolean moveUnit(Units unit, SystemPositions fromSystemPosition, SystemPositions toSystemPosition){
        if(this.getSystemByPosition(fromSystemPosition).getNeighborSystems().
            getNeighborSystems().containsValue(this.getSystemByPosition(toSystemPosition))){
            //toSystem is neighbor! (distance is 1)
            if(unit.getMovementSpeed() >= 1){
                this.getSystemByPosition(toSystemPosition).addUnitInSystem(unit);
                this.getSystemByPosition(fromSystemPosition).removeUnitFromSystem(unit);
                return true;
            }
        } else {
            //toSystem is NOT neighbor! (distance is 2)
            if (unit.getMovementSpeed() >= 2){
                this.getSystemByPosition(toSystemPosition).addUnitInSystem(unit);
                this.getSystemByPosition(fromSystemPosition).removeUnitFromSystem(unit);
                return true;
            }
        }
        return false;
    }

    /**
     * toString Override for {@code Galaxy}.
     * @return a {@link String} with info about {@link Player players} and
     * {@link System systems} with {@link SystemPositions} of the {@code Galaxy}.
     */
    @Override
    public String toString() {
        return "Galaxy{" +
                "systemHexagonGrid=" + systemHexagonGrid +
                ", players=" + players +
                '}';
    }
}
