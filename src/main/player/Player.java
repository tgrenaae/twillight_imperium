/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.player;

import java.util.*;

/**
 * the class represent a player in the game. A {@code Player} have a name, a race and a {@link Colors color}.
 *
 * <p>
 *     Once created, the {@code Player} is not mutable, meaning none of the fields can be changed during the game.
 *     Therefore, not Setters are introduced.
 * </p>
 *
 * Example of usage:
 * <blockquote><pre>
 *     Player player = new Player("player name", {@link Races THE_EMIRATES_OF_HACAN}, {@link Colors RED});
 * </pre></blockquote>
 *
 * @author Tommy Grenaae
 * @see Colors
 * @see Races
 */
//Problem 1
public class Player {
    /**
     * this represents the {@code Player} name.
     */
    final private String name;

    /**
     * this represents the {@code Player} {@link Races race}.
     */
    final private Races race;

    /**
     * this represents the {@code Player} {@link Colors color}.
     */
    final private Colors color;

    /**
     * Constructor for the {@code Player} class.
     * @param name , a {@code String} indicating Player name
     * @param race , a {@link Races} indicating which race the Player is playing the game as.
     * @param color, a {@link Colors} indicating which color the Player is playing the game as.
     */
    public Player(String name, Races race, Colors color) {
        this.name = name;
        this.race = race;
        this.color = color;
    }

    /**
     * Getter for the {@code Player} name.
     * @return a {@code String} representing the Player name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the {@code Player} {@link Races race}.
     * @return a {@link Races} representing the Player race.
     */
    public Races getRace() {
        return race;
    }

    /**
     * Getter for the {@code Player} {@link Colors color}.
     * @return a {@link Colors} representing the Player color.
     */
    public Colors getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name)  && color == player.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", race=" + race +
                ", color=" + color +
                '}';
    }
}
