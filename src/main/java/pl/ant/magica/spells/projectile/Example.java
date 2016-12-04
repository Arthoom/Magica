package pl.ant.magica.spells.projectile;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.ant.magica.Magica;
import pl.ant.magica.spells.ProjectileSpell;
import pl.ant.magica.spells.SpellEnum;

import java.util.function.Consumer;

/**
 * Created by Arthoom on 27.11.2016, 18:58
 */
@SuppressWarnings("FieldCanBeLocal")
public class Example extends ProjectileSpell {
    private String    name = "Example Projectile";
    private SpellEnum spellEnum = SpellEnum.PROJECTILE_EXAMPLE;
    private int       numberOfRepeatsInLoop = 1;
    private int       periodInTick = 1;
    private int       maxNumberOfExecutions = 200;
    private double    defaultDistanceMultiplier = 1;
    private double    defaultMaxInstability = 0.4;
    private boolean   hasInstability = true;
    private boolean   hasEffectMultiplier = true;

    @Override
    protected void executeWhenCast(Player player) {

    }

    @Override
    protected void executeWhenHitEntity(Player player, Entity entity) {
        Damageable hitEntity = (Damageable) entity;
        hitEntity.damage(5);
    }

    @Override
    protected boolean checkWhetherEntityOK(Entity entity) {
        return entity instanceof Damageable;
    }

    @Override
    protected void executeWhenFailure(Player player, Location location) {
         player.sendMessage(";<!");
    }

    @Override
    protected void update(Location location) {
        WrapperPlayServerWorldParticles particle = new WrapperPlayServerWorldParticles();
        particle.setParticleType(EnumWrappers.Particle.DRIP_LAVA);
        particle.setX((float) location.getX());
        particle.setY((float) location.getY());
        particle.setZ((float) location.getZ());
        particle.setOffsetX((float) 0.1);
        particle.setOffsetY((float) 0.1);
        particle.setOffsetZ((float) 0.1);
        particle.setNumberOfParticles(1);
        Magica.getInstance().getServer().getOnlinePlayers().forEach((Consumer<Player>) particle::sendPacket);
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
    protected double getDefaultDistanceMultiplier() {
        return defaultDistanceMultiplier;
    }

    @Override
    protected double getDefaultMaxInstability() {
        return defaultMaxInstability;
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
