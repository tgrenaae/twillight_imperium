package main.neighborSystemBuilder;

import main.galaxy.Galaxy;
import main.neighborSystem.NeighborSystem;
import main.system.System;
import main.system.SystemPositions;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import static main.system.SystemPositions.*;

/**
 * this class functions as a Builder for the {@link NeighborSystem neighbor systems} for each {@System}.
 * the method to be used is intended to be {@code createNeighborSystem} to create a {@code NeighborSystem} for a specific
 * {@code System}. As in input for the constructor, a {@link Galaxy} is needed.
 *
 * <p>
 *     the {@code Galaxy} is not mutable but is the foundation for creating the neighbor systems for each {@code System}
 *     in the {@code Galaxy}.
 * </p>
 *
 * Example of usage:
 * <blockquote><pre>
 *     Galaxy galaxy = SpecificGalaxyBuilder.createGalaxy();
 *     NeighborSystemBuilder neighborSystemBuilder = new NeighborSystemBuilder(galaxy);
 *     NeighborSystem neighborSystem = neighborSystemBuilder.createNeighborSystem({@link SystemPositions CENTER};
 * </pre></blockquote>
 *
 * @author Tommy Grenaae
 * @see NeighborSystem
 * @see System
 * @see Galaxy
 */
public class NeighborSystemBuilder {

    /**
     * this represents the {@System systems} which are considered to be neighbors to a specific {@code System}.
     */
    NeighborSystem neighborSystems;

    /**
     * this is the required {@link Galaxy} for the constructor. Not mutable.
     */
    Galaxy galaxy;

    /**
     * constructor for the class. You create a {@code NeighborSystemBuilder} for a specific {@link Galaxy}
     * which are already created.
     * @param galaxy a {@link Galaxy} for which you wish to find neighbor systems for.
     */
    public NeighborSystemBuilder(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    /**
     * main method to be used in the class. Creates a {@link NeighborSystem} and returns it to the caller of
     * the method.
     * @param targetPosition a {@link SystemPositions system position} for the target {@link System}.
     * @return a {@link NeighborSystem}.
     */
    public NeighborSystem createNeighborSystem(SystemPositions targetPosition){
        reset();
        setNeighborSystems(targetPosition);

        return this.neighborSystems;

    }

    /**
     * creates a new {@link NeighborSystem} that are going to be built.
     */
    public void reset(){
        neighborSystems = new NeighborSystem();
    }

    /**
     * Setter for {@link NeighborSystem} that are being built.
     * @param targetPosition a {@link SystemPositions} for the target {@link System}.
     */
    private void setNeighborSystems(SystemPositions targetPosition){
        List<SystemPositions> neighborPositions = getNeighbors(targetPosition);
        Map<SystemPositions, System> neighbors = new EnumMap<>(SystemPositions.class);
        for (SystemPositions position: neighborPositions) {
            neighbors.put(position, galaxy.getSystemByPosition(position));
        }
        this.neighborSystems.setNeighborSystems(neighbors);
    }

    /**
     * Getter for which neighbors are there for a given {@link System} with a
     * given {@link SystemPositions system position}.
     * @param systemPosition the {@code SystemPosition} for the target {@code System}.
     * @return a list of {@link SystemPositions} indicating which are the neighbors of the target system.
     */
    public List<SystemPositions> getNeighbors(SystemPositions systemPosition){
        List<SystemPositions> systemPositions = new ArrayList<>();
        switch (systemPosition) {
            case CENTER -> {
                systemPositions.add(NORTH);
                systemPositions.add(NORTH_EAST);
                systemPositions.add(SOUTH_EAST);
                systemPositions.add(SOUTH);
                systemPositions.add(SOUTH_WEST);
                systemPositions.add(NORTH_WEST);
                return systemPositions;
            }
            case NORTH -> {
                systemPositions.add(NORTH_EAST);
                systemPositions.add(CENTER);
                systemPositions.add(NORTH_WEST);
                return systemPositions;
            }
            case NORTH_EAST -> {
                systemPositions.add(NORTH);
                systemPositions.add(CENTER);
                systemPositions.add(SOUTH_EAST);
                return systemPositions;
            }
            case SOUTH_EAST -> {
                systemPositions.add(NORTH_EAST);
                systemPositions.add(CENTER);
                systemPositions.add(SOUTH);
                return systemPositions;
            }
            case SOUTH -> {
                systemPositions.add(SOUTH_EAST);
                systemPositions.add(CENTER);
                systemPositions.add(SOUTH_WEST);
                return systemPositions;
            }
            case SOUTH_WEST -> {
                systemPositions.add(SOUTH);
                systemPositions.add(CENTER);
                systemPositions.add(NORTH_WEST);
                return systemPositions;
            }
            case NORTH_WEST -> {
                systemPositions.add(NORTH);
                systemPositions.add(CENTER);
                systemPositions.add(SOUTH_WEST);
                return systemPositions;
            }
        }
        return null;
    }
}
