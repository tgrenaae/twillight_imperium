/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.planet;

/**
 * the class is an exception class extended from {@link RuntimeException}. the class is used when checking the
 * resource production when creating new {@link Planet planets}.
 */
public class ResourceProductionException extends RuntimeException{
    int resourceProduction;

    public ResourceProductionException(String message, int resourceProduction) {
        super(message);
        this.resourceProduction = resourceProduction;
    }

    /**
     * Getter for the resource production value that the class registers when exception is thrown.
     * @return an {@code int} value representing the resource production.
     */
    public int getResourceProduction() {
        return resourceProduction;
    }
}
