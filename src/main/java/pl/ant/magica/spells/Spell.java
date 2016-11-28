package pl.ant.magica.spells;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Created by Arthoom on 27.11.2016, 15:29
 */
abstract class Spell {
    @SuppressWarnings("unused")
    int getNumberOfRepeats(Player player, int defaultNumberOfRepeats) {
        return defaultNumberOfRepeats;
    }

    @SuppressWarnings("unused")
    int getPeriodInTick(Player player, int defaultPeriodInTick) {
        return defaultPeriodInTick;
    }

    @SuppressWarnings("unused")
    int getMaxNumberOfExecutions(Player player, int defaultMaxNumberOfExecutions) { return defaultMaxNumberOfExecutions; }

    @SuppressWarnings("unused")
    double getDistanceMultiplier(Player player, double defaultDistanceMultiplier) { return defaultDistanceMultiplier; }

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
