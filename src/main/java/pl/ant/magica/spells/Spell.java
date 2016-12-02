package pl.ant.magica.spells;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Created by Arthoom on 27.11.2016, 15:29
 */
abstract class Spell {
    //method used to calculate distance multiplier via MagicaPlayer & default distance multiplier
    @SuppressWarnings("unused")
    double getDistanceMultiplier(Player player, double defaultDistanceMultiplier) { return defaultDistanceMultiplier; }

    //method used to calculate the max instability of the spell (spell's vibrations).
    @SuppressWarnings("unused")
    double getMaxInstability(Player player, double defaultMaxInstability) { return defaultMaxInstability; }

    public abstract void execute(Player player);

    Entity getEntityInThisLocation(Location location, Player player) {
        for(Entity entity : location.getChunk().getEntities()) {
            if(entity.getLocation().distance(location) < 1.5) {
                if(entity instanceof Player) {
                    Player p = (Player) entity;
                    if(!p.equals(player)) {
                        return entity;
                    }
                }
                else {
                    return entity;
                }
            }
        }
        return null;
    }
}
