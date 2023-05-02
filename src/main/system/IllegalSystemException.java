/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.system;

import main.planet.Planet;

/**
 * this is a customized {@link Exception} to check if a {@link System} has a legal number of
 * {@link Planet planets} on creation. The class gathers information about the bounds that the number should
 * be included within and can be used for user feedback.
 *
 * @author Tommy Grenaae
 * @see System
 * @see Planet
 */
public class IllegalSystemException extends Exception{
    int lowerBound;
    int upperBound;

    public IllegalSystemException(String message, int lowerBound, int upperBound) {
        super(message);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Getter for the lower bound of the amount of {@link Planet planets} there can be in a {@link System}.
     * @return an {@code int} indicating the lower bound.
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Getter for the upper bound of the amount of {@link Planet planets} there can be in a {@link System}.
     * @return an {@code int} indicating the upper bound.
     */
    public int getUpperBound() {
        return upperBound;
    }
}
