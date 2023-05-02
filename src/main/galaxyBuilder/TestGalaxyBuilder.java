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
 * purely for testing exceptions - SHOULD NOT BE USED TO CREATE LEGAL GALAXIES!!
 */
public class TestGalaxyBuilder implements GalaxyBuilder {
    Galaxy galaxy;

    public TestGalaxyBuilder() {
    }

    @Override
    public Galaxy createGalaxy(){

        try {
            reset();

            createPlayers();

            createCenterSystem();

            createOtherSystems();

            setNeighborSystems();

        }catch (IllegalSystemException e){
            java.lang.System.out.println("error creating system: " + e.getMessage());
        }
        return this.galaxy;
    }

    @Override
    public void reset(){
        this.galaxy = new Galaxy();
    }

    @Override
    public void createPlayers(){
        List<Player> players = new ArrayList<>();
        Player crassus = new Player("Crassus", Races.THE_EMIRATES_OF_HACAN, Colors.BLUE);
        Player pompey = new Player("Pompey", Races.THE_FEDERATION_OF_SOL, Colors.RED);
        players.add(crassus);
        players.add(pompey);
        this.galaxy.setPlayers(players);
    }

    @Override
    public void createCenterSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();
        Planet mecatolRex1 = new Planet(PlanetNames.MECATOL_REX, 6);
        Planet mecatolRex2 = new Planet(PlanetNames.MECATOL_REX, 6);
        Planet mecatolRex3 = new Planet(PlanetNames.MECATOL_REX, 6);
        Units dreadnought1 = new Dreadnought(galaxy.getPlayers().get(0));
        Units dreadnought2 = new Dreadnought(galaxy.getPlayers().get(0));
        Units destroyer = new Destroyer(galaxy.getPlayers().get(0));

        planets.add(mecatolRex1);
        planets.add(mecatolRex2);
        planets.add(mecatolRex3);
        //planets.add(mecatolRex4);
        units.add(dreadnought1);
        units.add(dreadnought2);
        units.add(destroyer);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.CENTER, system);
    }

    @Override
    public void createOtherSystems() throws IllegalSystemException {

        createNorthSystem();

        createNorthEastSystem();

        createSouthEastSystem();

        createSouthSystem();

        createSouthWestSystem();

        createNorthWestSystem();
    }

    @Override
    public void setNeighborSystems() {
        NeighborSystemBuilder neighborSystemBuilder = new NeighborSystemBuilder(this.galaxy);
        for (SystemPositions systemPosition: SystemPositions.values()) {
            NeighborSystem neighborSystem = neighborSystemBuilder.createNeighborSystem(systemPosition);
            this.galaxy.getSystemByPosition(systemPosition).setNeighborSystems(neighborSystem);
        }
    }

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

    private void createNorthEastSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.NORTH_EAST, system);
    }

    private void createSouthEastSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet industrex = new Planet(PlanetNames.INDUSTREX, 5);
        planets.add(industrex);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.SOUTH_EAST, system);
    }

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

    private void createSouthWestSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.SOUTH_WEST, system);
    }

    private void createNorthWestSystem() throws IllegalSystemException {
        List<Planet> planets = new ArrayList<>();
        List<Units> units = new ArrayList<>();

        Planet mirage = new Planet(PlanetNames.MIRAGE, 1);
        planets.add(mirage);
        System system = new System(planets, units);
        galaxy.getAllSystems().put(SystemPositions.NORTH_WEST, system);
    }
}
