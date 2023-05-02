/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.planet;

/**
 * the class represents a {@code Planet} in a {@link System}. the class have a name and an int representing
 * the resource production of the Planet.
 *
 * <p>
 *     the constructor takes an {@link PlanetNames Planetname} enum value as name and an int for the resource production.
 *     the constructor have a helper method that throws an {@link ResourceProductionException} if resource prudction value
 *     is not legal.
 *     The data is not mutable after instantiation.
 * </p>
 *
 * Example of usage:
 * <blockquote><pre>
 *     Planet planet = new Planet({@link PlanetNames MECATOL_REX, 6}.
 * </pre></blockquote>
 */
//Problem 4
public class Planet {
    final private PlanetNames planetName;
    private int resourceProduction;

    public Planet(PlanetNames planetName, int resourceProduction) {
        this.planetName = planetName;
        try{
            isResourceProductionBounderies(resourceProduction);
        } catch (ResourceProductionException e){
            System.out.println("error: resource production input was: " + e.getResourceProduction() + e.getMessage());
        }

    }

    /**
     * helper method to check if resource production is legal.
     * @throws ResourceProductionException is value is not legal.
     * @param resourceProduction, the {@code int} value representing the resource production to be checked.
     */
    public void isResourceProductionBounderies(int resourceProduction){
        if(resourceProduction < 0 || resourceProduction > 6){
            throw new ResourceProductionException("Resource Production out of bounce [0,6]", resourceProduction);
        }
        this.resourceProduction = resourceProduction;
    }

    /**
     * Getter for the {@link PlanetNames planet name}.
     * @return the {@link PlanetNames planet name} of the {@code Planet}.
     */
    public PlanetNames getPlanetName() {
        return planetName;
    }

    /**
     * Getter for the resource production value.
     * @return an {@code int} representing the production value.
     */
    public int getResourceProduction() {
        return resourceProduction;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "planetname=" + planetName +
                ", resourceProduction=" + resourceProduction +
                "}\n";
    }
}
