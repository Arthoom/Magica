package pl.ant.magica.spells.buff;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.ant.magica.Magica;
import pl.ant.magica.spells.BuffSpell;
import pl.ant.magica.spells.SpellEnum;

import java.util.function.Consumer;

/**
 * Created by Arthoom on 04.12.2016, 14:36, 14:46
 */

@SuppressWarnings("FieldCanBeLocal")
public class Example extends BuffSpell {
    private String    name = "Example Buff";
    private SpellEnum spellEnum = SpellEnum.BUFF_EXAMPLE;
    private int       numberOfRepeatsInLoop = 1;
    private int       periodInTick = 1;
    private int       maxNumberOfExecutions = 10;
    private boolean   hasInstability = false;
    private boolean   hasEffectMultiplier = true;

    @Override
    protected void executeWhenSpellEnded(Player player) {
        if(player.getHealth() + 5 > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
        else {
            player.setHealth(player.getHealth() + 5);
        }
    }

    @Override
    protected void executeWhenCast(Player player) {

    }

    @Override
    protected void update(Player player) {
        Location location = player.getLocation();
        location.setY(location.getY() + 1.5);
        WrapperPlayServerWorldParticles particle = new WrapperPlayServerWorldParticles();
        particle.setParticleType(EnumWrappers.Particle.CLOUD);
        particle.setX((float) location.getX());
        particle.setY((float) location.getY());
        particle.setZ((float) location.getZ());
        particle.setOffsetX((float) 0.5);
        particle.setOffsetY((float) 0.5);
        particle.setOffsetZ((float) 0.5);
        particle.setNumberOfParticles(20);
        Magica.getInstance().getServer().getOnlinePlayers().forEach((Consumer<Player>) particle::sendPacket);
        location.setY(location.getY() - 1.5);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SpellEnum getSpellEnum() {
        return spellEnum;
    }

    @Override
    protected int getNumberOfRepeatsInLoop() {
        return numberOfRepeatsInLoop;
    }

    @Override
    protected int getPeriodInTick() {
        return periodInTick;
    }

    @Override
    protected int getMaxNumberOfExecutions() {
        return maxNumberOfExecutions;
    }

    @Override
    protected boolean hasInstability() {
        return hasInstability;
    }

    @Override
    protected boolean hasEffectMultiplier() {
        return hasEffectMultiplier;
    }
}
