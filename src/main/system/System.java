/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.system;

import main.neighborSystem.NeighborSystem;
import main.planet.Planet;
import main.player.Player;
import main.units.CombatValueComparator;
import main.units.Units;

import java.util.*;

/**
 * This class represents a System in the {@link main.galaxy.Galaxy}. The {@code System} have a list of {@link Planet planets}
 * included in the System, a list of which {@link Units} which are currently in the System and a {@link NeighborSystem}
 * that included information about which Systems are considered to be neighbors to a given System. The constructor will
 * throw an {@link IllegalSystemException} if the number of {@link Planet planets} is not within the legal bounderies, and
 * the exception should be handled by the user.
 *
 * <p>
 *     Once created, the {@link Planet planets} and {@link NeighborSystem} are not mutable, while the {@link Units} in
 *     the {@code System} can be changed. There are introduced a Setter method for the {@code NeighborSystem} but this
 *     is meant to only be used by the {@link main.neighborSystemBuilder.NeighborSystemBuilder}. Therefore, the
 *     {@link NeighborSystem} is not included when constructing a given {@code System} but is in stead set later, once
 *     all the Systems of the {@code Galaxy} have been created.
 * </p>
 *
 * Example of usage:
 * <blockquote><pre>
 *     List<Planet> planets = new ArrayList<>();
 *     List<Units> units = new ArrayList<>();
 *     System system = new System(planets, units);
 * </pre></blockquote>
 *
 * @author Tommy Grenaae
 * @see main.galaxy.Galaxy
 * @see Planet
 * @see Units
 * @see IllegalSystemException
 * @see NeighborSystem
 * @see main.neighborSystemBuilder.NeighborSystemBuilder
 */
public class System {
    final private List<Planet> planetsInSystem;
    private NeighborSystem neighborSystems;
    private List<Units> unitsInSystem;

    /**
     * constructor for the class. throws {@link IllegalSystemException} if number of {@link Planet planets} are not
     * within legal bounderies.
     * @param planetsInSystem , a list of {@link Planet planets}.
     * @param unitsInSystem , a list of {@link Units}.
     * @throws IllegalSystemException if number of {@link Planet planets} are not within legal bounderies.
     */
    //Problem 5
    public System(List<Planet> planetsInSystem, List<Units> unitsInSystem) throws IllegalSystemException {
        int planetLowerBound = 0;
        int planetUpperBound = 3;
        if(isBounderies(planetsInSystem, planetLowerBound, planetUpperBound)){
            this.planetsInSystem = planetsInSystem;
        }else{
            throw new IllegalSystemException("Number of planets in System out of bounce: ", planetLowerBound, planetUpperBound);
        }
        this.unitsInSystem = unitsInSystem;
    }

    /**
     * helper method to check the number of {@link Planet planets} are within bounderies.
     * @param list input to check if bounderies are okay
     * @param lowerBound lower bound to check
     * @param upperBound upper bound to check
     * @return {@code boolean} indicating if number of {@link Planet planets} are okay
     */
    private boolean isBounderies(List list, int lowerBound, int upperBound){
        if(list.size() < lowerBound || list.size() > upperBound){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Getter for the {@link Planet planets} of a {@code System}.
     * @return a list of {@link Planet planets}.
     */
    public List<Planet> getPlanetsInSystem() {
        return planetsInSystem;
    }

    /**
     * Getter for the {@link NeighborSystem} of a {@code System}.
     * @return a {@link NeighborSystem}.
     */
    public NeighborSystem getNeighborSystems() {
        return neighborSystems;
    }

    /**
     * Setter for the {@link NeighborSystem} - should only be used together with NeighborSystemBuilder!
     * @param neighborSystems should be output from a {@link main.neighborSystemBuilder.NeighborSystemBuilder}.
     */
    public void setNeighborSystems(NeighborSystem neighborSystems){
        this.neighborSystems = neighborSystems;
    }

    /**
     * Getter for the {@link Units} of a {@code System}.
     * @return a list of {@link Units}.
     */
    public List<Units> getUnitsInSystem() {
        return unitsInSystem;
    }

    /**
     * Adds a given {@link Units Unit} to the {@code System}.
     * @param unit , a {@link Units Unit} to be added.
     */
    public void addUnitInSystem(Units unit){
        this.unitsInSystem.add(unit);
    }

    /**
     * Removes a given {@link Units Unit} from the {@code System}.
     * @param unit , a {@link Units Unit} to be removed.
     */
    public void removeUnitFromSystem(Units unit){
        this.unitsInSystem.remove(unit);
    }

    /**
     * Helper method to find all {@link Units} in a {@code System} belonging to a specific {@link Player}.
     * @param player target {@link Player} to find units for.
     * @return a filtered array with units from target player with lowest combat value first.
     */
    private List<Units> filterUnitsByPlayer(Player player){
        ArrayList<Units> playerUnits = new ArrayList<>();
        for (Units unit: this.getUnitsInSystem()) {
            if (unit.getPlayer().equals(player)) {
                playerUnits.add(unit);
            }
        }
        Collections.sort(playerUnits, new CombatValueComparator());
        return playerUnits;
    }

    /**
     * Helper method to find the number of hits in a space battle.
     * @param units , a list of {@link Units} to fight in a space battle.
     * @return {@code int} score to indicate how many hits were made.
     */
    private int getHitScore(List<Units> units){
        Random randomizer = new Random();
        int hitScore = 0;
        for (Units unit: units) {
            int dieValue = 1 + randomizer.nextInt(10);
            if (dieValue >= unit.getCombatValue()){
                hitScore++;
            }
        }
        return hitScore;
    }

    /**
     * simulates a space battle between two {@link Player players}.
     * @param firstPlayer first Player in battle
     * @param secondPlayer second Player in battle
     * @return {@link Player} who won the battle
     */
    //Problem 13
    public Player spaceBattle(Player firstPlayer, Player secondPlayer){
        List<Units> firstPlayerUnits = filterUnitsByPlayer(firstPlayer);
        List<Units> secondPlayerUnits = filterUnitsByPlayer(secondPlayer);

        while (!firstPlayerUnits.isEmpty() && !secondPlayerUnits.isEmpty()){
            int firstPlayerScore = getHitScore(firstPlayerUnits);
            int secondPlayerScore = getHitScore(secondPlayerUnits);
            for (int i = 0; i < firstPlayerScore; i++) {
                if(secondPlayerUnits.size() == 0){
                    break;
                } else {
                    this.removeUnitFromSystem(secondPlayerUnits.remove(0));
                    secondPlayerUnits.remove(0);
                }
            }
            for (int i = 0; i < secondPlayerScore; i++) {
                if (firstPlayerUnits.size() == 0){
                    break;
                } else {
                    this.removeUnitFromSystem(secondPlayerUnits.remove(0));
                    firstPlayerUnits.remove(0);
                }
            }
        }

        if(firstPlayerUnits.isEmpty() && !secondPlayerUnits.isEmpty()){
            return secondPlayer;
        } else if(secondPlayerUnits.isEmpty() && !firstPlayerUnits.isEmpty()){
            return firstPlayer;
        } else {
            throw new IllegalArgumentException("Both players have no ships left");
        }
    }

    @Override
    public String toString() {
        return "System{" +
                "planetsInSystem=" + planetsInSystem +
                ", unitsInSystem=" + unitsInSystem +
                "}\n\n";
    }
}
