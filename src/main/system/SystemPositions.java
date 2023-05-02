/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.system;

/**
 * this enum represents the different locations that a {@link System} can have in a game. It is used when mapping
 * a given {@code System} to a location in the {@link main.galaxy.Galaxy}.
 *
 * @author Tommy Grenaae
 * @see System
 * @see main.galaxy.Galaxy
 */
public enum SystemPositions {
    CENTER,
    NORTH_WEST,
    NORTH,
    NORTH_EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST
}
