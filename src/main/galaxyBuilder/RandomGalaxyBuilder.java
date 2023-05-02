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
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The {@code RandomGalaxyBuilder} is a {@link GalaxyBuilder}. It is used to create
 * a random {@link Galaxy} with Mecatol Rex {@link Planet} in the center system.
 *
 * <p>
 *     The method to be used should be {@code createGalaxy()} which creates all the
 *     {@link Player players}, {@link Units units}, {@link Planet planets} and {@link System systems}
 *     of the {@link Galaxy}, and returns the {@code Galaxy}.
 * </p>
 *
 * Example of how to use this builder to build a random {@code galaxy} object:
 *
 * <blockqoute><pre>
 *     RandomGalaxyBuilder randomGalaxyBuilder = new RandomGalaxyBuilder();
 *     Galaxy galaxy = RandomGalaxyBuilder.createGalaxy();
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
//Problem 12
public class RandomGalaxyBuilder implements GalaxyBuilder{
    /**
     * This refers to the {@code Galaxy} that are being created.
     */
    Galaxy galaxy;

    /**
     * an empty constructor to be able to start the build of a {@code Galaxy}
     */
    public RandomGalaxyBuilder() {
    }

    /**
     * creates a random {@link Galaxy} with Mecatol Rex in the middle. The method
     * catches the {@link IllegalSystemException} if a created {@link System} is not
     * legal.
     * @return a random {@code Galaxy}.
     */
    @Override
    public Galaxy createGalaxy() {

        try {
            reset();

            createPlayers();

            createCenterSystem();

            createOtherSystems();

            setNeighborSystems();

        }catch (IllegalSystemException e){
            java.lang.System.out.println("error creating system: " + e.getMessage());
        }catch (IllegalArgumentException e){
            java.lang.System.out.println("error with randomizer.nextInt :" + e.getMessage());
        }
        return this.galaxy;
    }

    /**
     * Override from {@link GalaxyBuilder}. Creates an empty {@code Galaxy} object
     * to be filled by the builder.
     */
    @Override
    public void reset() {
        this.galaxy = new Galaxy();
    }

    /**
     * creates a random number of {@link Player players} and adds them to the
     * {@code galaxy}.
     */
    @Override
    public void createPlayers() {
        List<Races> races = new LinkedList<>(Arrays.asList(Races.values()));
        List<Colors> colors = new LinkedList<>(Arrays.asList(Colors.values()));
        List<Player> players = new ArrayList<>();

        Random randomizer = new Random();
        int numberOfPlayers = 2 + randomizer.nextInt(4);

        for (int i = 0; i < numberOfPlayers; i++) {
            byte[] nameArray = new byte[10];
            randomizer.nextBytes(nameArray);
            String randomName = new String(nameArray, StandardCharsets.UTF_8);

            int randomRaceIndex = randomizer.nextInt(races.size());
            Races randomRace = races.get(randomRaceIndex);

            int randomColorIndex = randomizer.nextInt(colors.size());
            Colors randomColor = colors.get(randomColorIndex);

            Player player = new Player(randomName, randomRace, randomColor);
            players.add(player);
            races.remove(randomRaceIndex);
            colors.remove(randomColorIndex);
        }
        this.galaxy.setPlayers(players);
    }

    /**
     * creates the center {@link System} of the {@code Galaxy} and adds it to
     * the {@code galaxy}.
     * @throws IllegalSystemException if {@link System} is not legal.
     */
    @Override
    public void createCenterSystem() throws IllegalSystemException {
        List<Units> units = this.createRandomNumberOfUnits();
        List<Planet> planets = new ArrayList<>();
        Planet mecatolRex = new Planet(PlanetNames.MECATOL_REX, 6);
        planets.add(mecatolRex);

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
        List<Planet> remainingPlanets = createPlanetArray();

        for (SystemPositions systemPosition: SystemPositions.values()) {
            if(systemPosition != SystemPositions.CENTER && remainingPlanets.size() != 0){
                createRandomSystem(remainingPlanets, systemPosition);
                for (Planet planet: this.galaxy.getSystemByPosition(systemPosition).getPlanetsInSystem()) {
                    remainingPlanets.remove(planet);
                }
            }
        }
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

    /**
     * private helper method to create a random number of {@link Units} to be added
     * to the current built system.
     * @return a list of {@code Units} to be used in the constructor of the {@link System}.
     */
    private List<Units> createRandomNumberOfUnits(){
        Random randomizer = new Random();

        ArrayList<Units> units = new ArrayList<>();
        ArrayList<Units> unitsToChooseFrom = new ArrayList<>();
        for (Player player: galaxy.getPlayers()) {
            unitsToChooseFrom.add(new Carrier(player));
            unitsToChooseFrom.add(new Cruiser(player));
            unitsToChooseFrom.add(new Destroyer(player));
            unitsToChooseFrom.add(new Dreadnought(player));
        }

        int randomNumberOfUnits = randomizer.nextInt(4);

        for (int i = 0; i < randomNumberOfUnits; i++) {
            units.add(unitsToChooseFrom.get(randomizer.nextInt(unitsToChooseFrom.size())));
        }
        return units;
    }

    /**
     * private helper method - creates Planet array without Mecatol Rex.
     * to be used when making random {@link System systems}.
     * @return {@link Planet} array without Mecatol Rex.
     */
    private List<Planet> createPlanetArray(){
        List<Planet> remainingPlanets = new ArrayList<>();
        Planet vegaMinor = new Planet(PlanetNames.VEGA_MINOR, 2);
        Planet vegaMajor = new Planet(PlanetNames.VEGA_MAJOR, 4);
        Planet industrex = new Planet(PlanetNames.INDUSTREX, 5);
        Planet rigelI = new Planet(PlanetNames.RIGEL_I,1);
        Planet rigelII = new Planet(PlanetNames.RIGEL_II, 6);
        Planet mirage = new Planet(PlanetNames.MIRAGE, 1);
        Planet velnor = new Planet(PlanetNames.VELNOR, 3);
        Planet perimeter = new Planet(PlanetNames.PERIMETER, 4);
        Planet hopesEnd = new Planet(PlanetNames.HOPES_END, 3);
        Collections.addAll(remainingPlanets, vegaMinor, vegaMajor, industrex, rigelI, rigelII, mirage, velnor, perimeter, hopesEnd);

        return remainingPlanets;
    }

    /**
     * private helper method - is used for creating a random number of planets [0;3]
     * @param remainingPlanets a list of {@link Planet planets} with the remaining planets
     *                         which are not used already in another {@link System}.
     * @return a list of {@code Planet} which is used in the {@link System} constructor.
     */
    private List<Planet> createRandomNumberOfPlanets(List<Planet> remainingPlanets){
        ArrayList<Planet> planets = new ArrayList<>();
        Random randomizer = new Random();
        int randomNumberOfPlanets = randomizer.nextInt(3);
        for (int i = 0; i < randomNumberOfPlanets; i++) {
            int planetIndex = randomizer.nextInt(remainingPlanets.size());
            planets.add(remainingPlanets.get(planetIndex));
            remainingPlanets.remove(planetIndex);
        }
        return planets;
    }

    /**
     * private helper method to create a random {@link System}. Uses other helper
     * methods to get the lists to enable the constructor to work. Adds the random system
     * to the {@code galaxy} in the {@link SystemPositions systemPosition}.
     * @param remainingPlanets array of {@link Planet planets} that are not used in the {@code galaxy} yet.
     * @param systemPosition {@link SystemPositions systemPosition} of the system placement in the {@code galaxy}.
     */
    private void createRandomSystem(List<Planet> remainingPlanets, SystemPositions systemPosition) throws IllegalSystemException {
        List<Units> units = this.createRandomNumberOfUnits();

        List<Planet> planets = this.createRandomNumberOfPlanets(remainingPlanets);

        System system = new System(planets, units);
        galaxy.getAllSystems().put(systemPosition, system);
    }

}
