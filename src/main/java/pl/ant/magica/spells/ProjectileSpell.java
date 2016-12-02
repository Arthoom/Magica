package pl.ant.magica.spells;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.ant.magica.Magica;
import pl.ant.magica.utils.MaterialUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Arthoom on 27.11.2016, 15:29
 */
public abstract class ProjectileSpell  extends Spell {
    protected abstract void    executeWhenCast(Player player);
    protected abstract void    executeWhenHitEntity(Player player, Entity entity);
    protected abstract boolean checkWhetherEntityOK(Entity entity);                 //Should return true when you want to execute executeWhenHitEntity() for a given entity.
    protected abstract void    executeWhenFailure(Player player, Location location);
    protected abstract void    update(Location location);                           //DON'T update location, just (for example) particles
    protected abstract int     getNumberOfRepeatsInLoop();                          //number of repeats of an update in 1 tick (periodInTick)
    protected abstract int     getPeriodInTick();                                   //time between updating in ticks (and in this tick, update will execute itself numberOfRepeatsInLoop times)
    protected abstract int     getMaxNumberOfExecutions();                          //the limit of the number of updates
    protected abstract double  getDefaultDistanceMultiplier();                      //in each update the spell moves <0, 1> block (varies from direction). DistanceMultiplier multiplies it.
    protected abstract double  getDefaultMaxInstability();                          //instability = vibrations of the projectile. 0 -> none.

    private class Runnable extends BukkitRunnable { //this class is created and run() is executed when a new spell is launched (via execute())
        private Player   player;
        private Location location;
        private Vector   direction;
        private int      time; //number of already repeated updates
        private int      numberOfRepeatsInLoop;
        private int      periodInTick;
        private int      maxNumberOfExecutions;
        private double   distanceMultiplier;
        private int      variable; //variable used when launched more than 1 projectile in execute() to calculate differences in position etc.
        private double   maxInstability = getMaxInstability(player, getDefaultMaxInstability());

        Runnable(Player player, int variable) {
            this.player           = player;
            location              = player.getLocation();
            location.setY(location.getY() + 1.5);
            direction             = location.getDirection().normalize();
            time                  = 0;
            numberOfRepeatsInLoop = getNumberOfRepeatsInLoop();
            periodInTick          = getPeriodInTick();
            maxNumberOfExecutions = getMaxNumberOfExecutions();
            distanceMultiplier    = getDistanceMultiplier(player, getDefaultDistanceMultiplier());
            this.variable         = variable;
            executeWhenCast(player);
        }

        @Override
        public void run() {
            for(int i = 0; i < numberOfRepeatsInLoop; ++i) {
                double randomInstabilityX = ThreadLocalRandom.current().nextDouble(-maxInstability, maxInstability);
                double randomInstabilityY = ThreadLocalRandom.current().nextDouble(-maxInstability, maxInstability);
                double randomInstabilityZ = ThreadLocalRandom.current().nextDouble(-maxInstability, maxInstability);

                location.setX(location.getX() + direction.getX() * distanceMultiplier + randomInstabilityX + customX(location, distanceMultiplier, time, variable));
                location.setY(location.getY() + direction.getY() * distanceMultiplier + randomInstabilityY + customY(location, distanceMultiplier, time, variable));
                location.setZ(location.getZ() + direction.getZ() * distanceMultiplier + randomInstabilityZ + customZ(location, distanceMultiplier, time, variable));

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
            }
        }

        int getPeriod() {
            return periodInTick;
        }
    }

    //Override it when you're launching more than 1 projectile
    public void execute(Player player) {
        createProjectile(player, 0);
    }

    //set variable to 0 if you're not launching more than 1 projectile. Variable used to calculate differences in position etc.
    private void createProjectile(Player player, int variable) {
        Runnable runnable = new Runnable(player, variable);
        int periodInTick = runnable.getPeriod();
        runnable.runTaskTimer(Magica.getInstance(), 0, periodInTick);
    }

    //below methods used to calculate differences in position etc. when launching more than 1 projectile.
    @SuppressWarnings("unused")
    //should be protected, is private to suppress warning
    private double customX(Location location, double distanceMultiplier, int time, int variable) {
        return 0;
    }

    @SuppressWarnings("unused")
    private double customY(Location location, double distanceMultiplier, int time, int variable) {
        return 0;
    }

    @SuppressWarnings("unused")
    private double customZ(Location location, double distanceMultiplier, int time, int variable) {
        return 0;
    }
}
