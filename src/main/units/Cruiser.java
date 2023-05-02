/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.units;

import main.player.Player;

/**
 * the class represents a specific {@link Units Unit}. the class have a resource cost, combat value, movement speed and
 * capacity. Furthermore, it is owned by a {@link Player}.
 *
 * <p>
 *     except the {@link Player}, all values are default and are not mutable. Once created, the {@code Player} are not
 *     mutable as well.
 * </p>
 *
 * @author Tommy Grenaae
 * @see Units
 * @see Player
 */

public class Cruiser implements Units, Comparable<Units>{
    /**
     * this represents the resource cost
     */
    private int resourceCost = 2;

    /**
     * this represents the combat value
     */
    private int combatValue = 7;

    /**
     * this represents the movement speed
     */
    private int movementSpeed = 2;

    /**
     * this represents the capacity
     */
    private int capacity = 0;

    /**
     * this represents the {@link Player} that owns the {@link Units unit}.
     */
    private Player player;

    /**
     * constructor for the class.
     * @param player , a {@link Player} that owns the unit.
     */
    public Cruiser(Player player) {
        this.player = player;
    }

    /**
     * Getter for the resource cost
     * @return {@code int}
     */
    @Override
    public int getResourceCost() {
        return this.resourceCost;
    }

    /**
     * Getter for the combat value
     * @return {@code int}
     */
    @Override
    public int getCombatValue() {
        return this.combatValue;
    }

    /**
     * Getter for the movement speed
     * @return {@code int}
     */
    @Override
    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     * Getter for the capacity
     * @return {@code int}
     */
    @Override
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Getter for the {@link Player} that owns the unit.
     * @return a {@link Player}.
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String toString() {
        return "Cruiser{" +
                "resourceCost=" + resourceCost +
                ", combatValue=" + combatValue +
                ", movementSpeed=" + movementSpeed +
                ", capacity=" + capacity +
                ", player=" + player +
                '}';
    }

    /**
     * override compareTo method from {@link Units}. simply calls the default method of the {@link Units} interface.
     * @param compareUnit , the {@code Unit} that are going to be compared with a given object.
     * @return {@code int} indicating sorting.
     */
    @Override
    public int compareTo(Units compareUnit) {
        return Units.super.compareTo(compareUnit);
    }
}
