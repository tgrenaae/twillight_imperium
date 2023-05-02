package main.neighborSystem;

import main.system.System;
import main.system.SystemPositions;

import java.util.Map;

/**
 * this class is used to keep track of which {@link System systems} are neighbors to a given system. Is used by the
 * {@link main.galaxyBuilder.GalaxyBuilder GalaxyBuilders} to set the neighbors of each given {@code System} of the
 * {@code Galaxy}.
 *
 * @author Tommy Grenaae
 * @see main.galaxyBuilder.GalaxyBuilder
 * @see main.galaxyBuilder.SpecificGalaxyBuilder
 * @see main.galaxyBuilder.RandomGalaxyBuilder
 * @see System
 */
public class NeighborSystem {
    /**
     * this represents the neighbors to a {@link System}.
     */
    private Map<SystemPositions, System> neighborSystems;

    /**
     * Getter for the neighbor systems to a {@link System}.
     * @return a Map where all {@link System systems} are mapped to their {@link SystemPositions}.
     */
    public Map<SystemPositions, System> getNeighborSystems() {
        return neighborSystems;
    }

    /**
     * Setter for the Map of neighbors to a {@link System}.
     * @param neighborSystems the Map of neighbors to be set.
     */
    public void setNeighborSystems(Map<SystemPositions, System> neighborSystems) {
        this.neighborSystems = neighborSystems;
    }

    /**
     * toString Override.
     * @return a {@link String} with data about the neighbor systems to a {@link System}.
     */
    @Override
    public String toString() {
        return "NeighborSystem{" +
                "neighborSystems=" + neighborSystems +
                '}';
    }
}
