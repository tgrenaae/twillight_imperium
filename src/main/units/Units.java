/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.units;

import main.player.Player;

/**
 * the interface represents all units in a game. All units have a resource cost, combat value, movement speed and
 * capacity. Furthermore, each units must have a {@link Player} that owns the unit.
 */
//Problem 2
public interface Units {

    /**
     * Getter for the resource cost.
     * @return {@code int} indicating the unit resource cost.
     */
    public int getResourceCost();

    /**
     * Getter for the combat value.
     * @return {@code int} indicating the unit combat value.
     */
    public int getCombatValue();

    /**
     * Getter for the movement speed.
     * @return {@code int} indicating the unit movement speed.
     */
    public int getMovementSpeed();

    /**
     * Getter for the capacity.
     * @return {@code int} indicating the unit capacity.
     */
    public int getCapacity();

    /**
     * Getter for the {@link Player} that owns the unit.
     * @return {@code Player}.
     */
    public Player getPlayer();

    /**
     * compareTo default method. Defines the way all units are sorted. lowest combat value will be first.
     * If tied on combat value, the compare will happen on resource cost in stead.
     * @param compareUnit , the {@code Unit} that are going to be compared with a given object.
     * @return {@code int} to indicate sorting.
     */
    default int compareTo(Units compareUnit) {
        if(this.getCombatValue() > compareUnit.getCombatValue()){
            return 1;
        }else if(this.getCombatValue() < compareUnit.getCombatValue()){
            return -1;
        }else{
            if(this.getResourceCost() > compareUnit.getResourceCost()){
                return 1;
            } else if(this.getResourceCost() < compareUnit.getResourceCost()){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
