package pl.ant.magica.spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.ant.magica.Magica;
import pl.ant.magica.utils.MaterialUtils;

/**
 * Created by Arthoom on 27.11.2016, 15:29.
 */
public abstract class ProjectileSpell  extends Spell {
    protected abstract void    executeWhenCast(Player player);
    protected abstract void    executeWhenHitEntity(Player player, Entity entity);
    protected abstract boolean checkWhetherEntityOK(Entity entity);
    protected abstract void    executeWhenFailure(Player player, Location location);
    protected abstract void    update(Location location); //DON'T update location, just (for example) particles
    protected abstract int     getDefaultNumberOfRepeatsInLoop();
    protected abstract int     getDefaultPeriodInTick();
    protected abstract int     getDefaultMaxNumbersOfExecutions();

    private class Runnable extends BukkitRunnable {
        private Player   player;
        private Location location;
        private Vector   direction;
        private int      time; //number of already repeated operations
        private int      numberOfRepeatsInLoop;
        private int      periodInTick;
        private int      maxNumberOfExecutions;
        Runnable(Player player) {
            this.player           = player;
            location              = player.getLocation();
            direction             = location.getDirection().normalize();
            time                  = 0;
            numberOfRepeatsInLoop = getNumberOfRepeats(player, getDefaultNumberOfRepeatsInLoop());
            periodInTick          = getPeriodInTick(player, getDefaultPeriodInTick());
            maxNumberOfExecutions = getMaxNumberOfExecutions(player, getDefaultMaxNumbersOfExecutions());
            executeWhenCast(player);
        }

        @Override
        public void run() {
            for(int i = 0; i < numberOfRepeatsInLoop; ++i) {
                double x = direction.getX() * time;
                double y = direction.getY() * time + 1.5;
                double z = direction.getZ() * time;
                location.add(x,y,z);

                update(location);

                Entity entity = getEntityInThisLocation(location, player);
                if(entity != null && checkWhetherEntityOK(entity)) {
                    executeWhenHitEntity(player, entity);
                    cancel();
                }

                if(MaterialUtils.isSolidBlockInThisLocation(location) || time > maxNumberOfExecutions) {
                    executeWhenFailure(player, location);
                    cancel();
                }

                ++time;
                location.subtract(x,y,z);
            }
        }

        public int getPeriod() {
            return periodInTick;
        }
    }

    public void execute(Player player) {
        Runnable runnable = new Runnable(player);
        int periodInTick = runnable.getPeriod();
        runnable.runTaskTimer(Magica.getInstance(), 0, periodInTick);
    }

    protected Entity getEntityInThisLocation(Location location, Player player) {
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
