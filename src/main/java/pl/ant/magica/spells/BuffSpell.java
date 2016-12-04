package pl.ant.magica.spells;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.ant.magica.Magica;

/**
 * Created by Arthoom on 27.11.2016, 15:28
 */

public abstract class BuffSpell extends Spell {
    protected abstract void executeWhenSpellEnded(Player player);
    protected abstract void update(Player player); //particles etc.

    private class Runnable extends BukkitRunnable {
        private Player player;
        private int    time; //number of already repeated updates
        private int    numberOfRepeatsInLoop;
        private int    periodInTick;
        private int    maxNumberOfExecutions;

        Runnable(Player player) {
            this.player           = player;
            time                  = 0;
            numberOfRepeatsInLoop = getNumberOfRepeatsInLoop();
            periodInTick          = getPeriodInTick();
            maxNumberOfExecutions = getMaxNumberOfExecutions();
            executeWhenCast(player);
        }

        @Override
        public void run() {
            for(int i = 0; i < numberOfRepeatsInLoop; ++i) {
                update(player);
                ++time;

                if(time > maxNumberOfExecutions) {
                    executeWhenSpellEnded(player);
                    cancel();
                }
            }
        }

        int getPeriod() {
            return periodInTick;
        }
    }

    @Override
    public void execute(Player player) {
        Runnable runnable = new Runnable(player);
        int periodInTick = runnable.getPeriod();
        runnable.runTaskTimer(Magica.getInstance(), 0, periodInTick);
    }
}
