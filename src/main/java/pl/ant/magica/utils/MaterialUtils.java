package pl.ant.magica.utils;


import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Created by Arthoom on 27.11.2016, 21:37
 */
public class MaterialUtils {
    public static boolean isSolidBlockInThisLocation(Location location) {
        Material material = location.getBlock().getType();
        switch(material)
        {
            case AIR:
            case GRASS:
            case WATER:
            case WATER_LILY:
                return false;

            default:
                return true;
        }
    }
}
