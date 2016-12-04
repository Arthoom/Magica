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
    double getDistanceMultiplier(Player player, SpellEnum spellEnum, double defaultDistanceMultiplier) {
        return defaultDistanceMultiplier;
    }

    //method used to calculate the max instability of the spell (spell's vibrations).
    @SuppressWarnings("unused")
    double getMaxInstability(Player player, SpellEnum spellEnum, double defaultMaxInstability) {
        if(hasInstability()) {
            return defaultMaxInstability;
        }
        else {
            return 0;
        }
    }

    //method used to calculate players' multiplier of effect.
    @SuppressWarnings("unused")
    double getEffectMultiplier(Player player, SpellEnum spellEnum) {
        if(hasEffectMultiplier()) {
            return 1;
        }
        else {
            return 1;
        }
    }

    //method used to launch the spell
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

    //get the spell's name.
    //Example of a name: Projectile Example.
    //Each spell MUST have an unique name.
    public abstract String     getName();
    public abstract SpellEnum  getSpellEnum();
    protected abstract void    executeWhenCast(Player player);
    protected abstract int     getNumberOfRepeatsInLoop();//number of repeats of an update in 1 tick (periodInTick)
    protected abstract int     getPeriodInTick();         //time between updating in ticks (and in this tick, update will execute itself numberOfRepeatsInLoop times)
    protected abstract int     getMaxNumberOfExecutions();//the limit of the number of updates
    protected abstract boolean hasInstability();
    protected abstract boolean hasEffectMultiplier();
}
