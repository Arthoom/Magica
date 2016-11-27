package pl.ant.magica.spells;

import org.bukkit.entity.Player;

/**
 * Created by Arthoom on 27.11.2016, 15:29.
 */
public abstract class Spell {
    protected int getNumberOfRepeats(Player player, int defaultNumberOfRepeats) {
        return defaultNumberOfRepeats;
    }

    protected int getPeriodInTick(Player player, int defaultPeriodInTick) {
        return defaultPeriodInTick;
    }

    protected int getMaxNumberOfExecutions(Player player, int defaultMaxNumberOfExecutions) {
        return defaultMaxNumberOfExecutions;
    }

    public abstract void execute(Player player);
}
