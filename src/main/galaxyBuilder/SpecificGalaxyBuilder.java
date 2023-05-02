/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.galaxyBuilder;

import main.galaxy.Galaxy;
import main.neighborSystem.NeighborSystem;
import main.neighborSystemBuilder.NeighborSystemBuilder;
import main.planet.Planet;
import main.planet.PlanetNames;
import main.player.Colors;
import main.player.Player;
import main.player.Races;
import main.system.IllegalSystemException;
import main.system.System;
import main.system.SystemPositions;
import main.units.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code SpecificGalaxyBuilder} is a {@link GalaxyBuilder}. It is used to create
 * a specific {@link Galaxy} with the specifications mentioned in the exam paper.
 *
 * <p>
 *     The method to be used should be {@code createGalaxy()} which creates all the
 *     {@link Player players}, {@link Units units}, {@link Planet planets} and {@link System systems}
 *     of the {@link Galaxy}, and returns the {@code Galaxy}.
 * </p>
 *
 * Example of how to use this builder to build a specific {@code galaxy} object:
 *
 * <blockqoute><pre>
 *     SpecificGalaxyBuilder specificGalaxyBuilder = new specificGalaxyBuilder();
 *     Galaxy galaxy = SpecificGalaxyBuilder.createGalaxy();
 * </pre></blockqoute>
 *
 *
 * @author Tommy Grenaae
 * @see GalaxyBuilder
 * @see Galaxy
 * @see System
 * @see Planet
 * @see Units
 * @see Player
 */
//Problem 7
public class SpecificGalaxyBuilder implements GalaxyBuilder {
    /**
     * This refers to the {@code Galaxy} that are being created.
     */
    Galaxy galaxy;

    /**
     * an empty constructor to be able to start the build of a {@code Galaxy}
     */
    public SpecificGalaxyBuilder() {
    }

    /**
     * creates a specific {@link Galaxy} with criteria from the exam paper. The method
     * catches the {@link IllegalSystemException} if a created {@link System} is not
     * legal.
     * @return a specific {@code Galaxy}.
     */
    @Override
    public Galaxy createGalaxy(){

        try {
            reset();

            createPlayers();

            createCenterSystem();

            createOtherSystems();

            setNeighborSystems();

        }catch (IllegalSystemException e){
            java.lang.System.out.println("error creating system: " + e.getMessage() + e.getLowerBound() +", " + e.getUpperBound());
        }
        return this.galaxy;
    }
    /**
     * Override from {@link GalaxyBuilder}. Creates an empty {@code Galaxy} object
     * to be filled by the builder.
     */
    @Override
    public void reset(){
        this.galaxy = new Galaxy();
    }

    /**
     * creates the specific {@link Player players} and adds them to the
     * {@code galaxy}.
     */
    @Override
    public void createPlayers(){
        List<Player> players = new ArrayList<>();
        Player crassus = new Player("Crassus", Races.THE_EMIRATES_OF_HACAN, Colors.BLUE);
        Player pompey = new Player("Pompey", Races.THE_FEDERATION_OF_SOL, Colors.RED);
        players.add(crassus);
        players.add(pompey);
        this.galaxy.setPlayers(players);
    }


    /**
     * creates the center {@link System} of the {@code Galaxy} and adds it to
     * the {@code galaxy}.
     * @throws IllegalSystemException if {@link System} is not legal.
     */
    @Override
    public void createCenterSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();
        Planet mecatolRex = new Planet(PlanetNames.MECATOL_REX, 6);
        Units dreadnought1 = new Dreadnought(galaxy.getPlayers().get(0));
        Units dreadnought2 = new Dreadnought(galaxy.getPlayers().get(0));
        Units destroyer = new Destroyer(galaxy.getPlayers().get(0));

        planets.add(mecatolRex);
        units.add(dreadnought1);
        units.add(dreadnought2);
        units.add(destroyer);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.CENTER, system);
    }

    /**
     * creates the six other {@link System systems} surrounding the center system, and then
     * adds them to {@code galaxy}.
     * @throws IllegalSystemException if {@link System} is not legal.
     */
    @Override
    public void createOtherSystems() throws IllegalSystemException {

        createNorthSystem();

        createNorthEastSystem();

        createSouthEastSystem();

        createSouthSystem();

        createSouthWestSystem();

        createNorthWestSystem();
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions NORTH}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createNorthSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet vegaMinor = new Planet(PlanetNames.VEGA_MINOR, 2);
        Planet vegaMajor = new Planet(PlanetNames.VEGA_MAJOR, 4);

        Units cruiser1 = new Cruiser(galaxy.getPlayers().get(1));
        Units cruiser2 = new Cruiser(galaxy.getPlayers().get(1));
        Units carrier = new Carrier(galaxy.getPlayers().get(1));

        planets.add(vegaMinor);
        planets.add(vegaMajor);
        units.add(cruiser1);
        units.add(cruiser2);
        units.add(carrier);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.NORTH, system);
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions NORTH_EAST}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createNorthEastSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.NORTH_EAST, system);
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions SOUTH_EAST}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createSouthEastSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet industrex = new Planet(PlanetNames.INDUSTREX, 5);
        planets.add(industrex);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.SOUTH_EAST, system);
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions SOUTH}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createSouthSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet rigelI = new Planet(PlanetNames.RIGEL_I,1);
        Planet rigelII = new Planet(PlanetNames.RIGEL_II, 6);

        planets.add(rigelI);
        planets.add(rigelII);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.SOUTH, system);
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions SOUTH_WEST}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createSouthWestSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.SOUTH_WEST, system);
    }

    /**
     * private method that creates the specific {@link System} to the {@link SystemPositions NORTH_WEST}
     * @throws IllegalSystemException if {@link System} creation is not legal.
     */
    private void createNorthWestSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet mirage = new Planet(PlanetNames.MIRAGE, 1);
        planets.add(mirage);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.NORTH_WEST, system);
    }

    /**
     * last method to be run by the Builder. Is run after the creation of all {@link System systems}
     * to set which systems are neighbors for each of the systems in the {@code galaxy}.
     */
    @Override
    public void setNeighborSystems() {
        NeighborSystemBuilder neighborSystemBuilder = new NeighborSystemBuilder(this.galaxy);
        for (SystemPositions systemPosition: SystemPositions.values()) {
            NeighborSystem neighborSystem = neighborSystemBuilder.createNeighborSystem(systemPosition);
            this.galaxy.getSystemByPosition(systemPosition).setNeighborSystems(neighborSystem);
        }
    }
}
