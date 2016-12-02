package pl.ant.magica.spells.projectile;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.ant.magica.Magica;
import pl.ant.magica.spells.ProjectileSpell;

/**
 * Created by Arthoom on 27.11.2016, 18:58
 */
public class Example extends ProjectileSpell {

    @Override
    protected void executeWhenCast(Player player) {

    }

    @Override
    protected void executeWhenHitEntity(Player player, Entity entity) {
        player.sendMessage("BRAWO!");
    }

    @Override
    protected boolean checkWhetherEntityOK(Entity entity) {
        return true;
    }

    @Override
    protected void executeWhenFailure(Player player, Location location) {
         player.sendMessage(";<!");
    }

    @Override
    protected void update(Location location) {
        WrapperPlayServerWorldParticles particle = new WrapperPlayServerWorldParticles();
        particle.setParticleType(EnumWrappers.Particle.CRIT_MAGIC);
        particle.setX((float) location.getX());
        particle.setY((float) location.getY());
        particle.setZ((float) location.getZ());
        particle.setOffsetX((float) 0.1);
        particle.setOffsetY((float) 0.1);
        particle.setOffsetZ((float) 0.1);
        particle.setNumberOfParticles(20);
        for(Player player : Magica.getInstance().getServer().getOnlinePlayers()) {
            particle.sendPacket(player);
        }
    }

    @Override
    protected int getNumberOfRepeatsInLoop() {
        return 1;
    }

    @Override
    protected int getPeriodInTick() {
        return 1;
    }

    @Override
    protected int getMaxNumberOfExecutions() {
        return 200;
    }

    @Override
    protected double getDefaultDistanceMultiplier() {
        return 1;
    }

    @Override
    protected double getDefaultMaxInstability() {
        return 0.4;
    }
}
