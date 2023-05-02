/*
 * Tommy Grenaae
 * tgrena20@student.aau.dk
 * */

package main.units;

import java.util.Comparator;

/**
 * comparator class to sort {@link Units} by combat value.
 * if equal combat value, it sorts by resource cost.
 *
 * @author Tommy Grenaae
 * @see Units
 */
public class CombatValueComparator implements Comparator<Units> {

    /**
     * compare method override. First compares {@link Units} on combat value, secondarily on resource cost - lowest
     * being first.
     * @param unit1 this represent the first {@link Units unit} to be compared
     * @param unit2 this represent the second {@link Units unit} to be compared
     * @return {@code int} that indicate unit order
     */
    @Override
    public int compare(Units unit1, Units unit2) {
        if(unit1.getCombatValue() > unit2.getCombatValue()){
            return -1;
        }else if(unit1.getCombatValue() < unit2.getCombatValue()){
            return 1;
        }else{
            if(unit1.getResourceCost() > unit2.getResourceCost()){
                return -1;
            }else if(unit1.getResourceCost() < unit2.getResourceCost()){
                return 1;
            }else{
                return 0;
            }
        }
    }
}
