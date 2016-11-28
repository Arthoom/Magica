package pl.ant.magica.spells;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.ant.magica.Magica;
import pl.ant.magica.utils.MaterialUtils;

/**
 * Created by Arthoom on 27.11.2016, 15:29
 */
public abstract class ProjectileSpell  extends Spell {
    protected abstract void    executeWhenCast(Player player);
    protected abstract void    executeWhenHitEntity(Player player, Entity entity);
    protected abstract boolean checkWhetherEntityOK(Entity entity);                 //Should return true when you want to execute executeWhenHitEntity() for a given entity.
    protected abstract void    executeWhenFailure(Player player, Location location);
    protected abstract void    update(Location location);                           //DON'T update location, just (for example) particles
    protected abstract int     getDefaultNumberOfRepeatsInLoop();                   //number of repeats of an update in 1 tick (periodInTick)
    protected abstract int     getDefaultPeriodInTick();                            //time between updating in ticks (and in this tick, update will execute itself numberOfRepeatsInLoop times)
    protected abstract int     getDefaultMaxNumbersOfExecutions();                  //the limit of the number of updates
    protected abstract double  getDefaultDistanceMultiplier();                      //in each update the spell moves <0, 1> block (varies from direction). DistanceMultiplier multiplies it.

    private class Runnable extends BukkitRunnable { //this class is created and run() is executed when a new spell is launched (via execute())
        private Player   player;
        private Location location;
        private Vector   direction;
        private int      time; //number of already repeated updates
        private int      numberOfRepeatsInLoop;
        private int      periodInTick;
        private int      maxNumberOfExecutions;
        private double   distanceMultiplier;

        Runnable(Player player) {
            this.player           = player;
            location              = player.getLocation();
            direction             = location.getDirection().normalize();
            time                  = 0;
            numberOfRepeatsInLoop = getNumberOfRepeats(player, getDefaultNumberOfRepeatsInLoop());
            periodInTick          = getPeriodInTick(player, getDefaultPeriodInTick());
            maxNumberOfExecutions = getMaxNumberOfExecutions(player, getDefaultMaxNumbersOfExecutions());
            distanceMultiplier    = getDistanceMultiplier(player, getDefaultDistanceMultiplier());
            executeWhenCast(player);
        }

        @Override
        public void run() {
            for(int i = 0; i < numberOfRepeatsInLoop; ++i) {
                double x = direction.getX() * time * distanceMultiplier;
                double y = direction.getY() * time * distanceMultiplier + 1.5;
                double z = direction.getZ() * time * distanceMultiplier;
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

        int getPeriod() {
            return periodInTick;
        }
    }

    public void execute(Player player) {
        Runnable runnable = new Runnable(player);
        int periodInTick = runnable.getPeriod();
        runnable.runTaskTimer(Magica.getInstance(), 0, periodInTick);
    }
}
