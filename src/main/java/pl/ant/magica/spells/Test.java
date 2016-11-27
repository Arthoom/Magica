package pl.ant.magica.spells;

import com.comphenix.packetwrapper.WrapperPlayServerExplosion;
import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;
import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import pl.ant.magica.Magica;

/**
 * Created by Arthoom on 26.11.2016, 23:28.
 */
public class Test {
    public static void execute(final Player player) {
        new BukkitRunnable() {
            Location location = player.getLocation();
            Vector direction = location.getDirection().normalize();
            int time = 0;
            public void run() {



                double x = direction.getX() * time;
                double y = direction.getY() * time + 1.5;
                double z = direction.getZ() * time;
                location.add(x,y,z);
                //p.sendMessage(x + " " + y + " " + z);

                WrapperPlayServerExplosion fakeExplosion = new WrapperPlayServerExplosion();
                fakeExplosion.setX(location.getX());
                fakeExplosion.setY(location.getY());
                fakeExplosion.setZ(location.getZ());
                fakeExplosion.setRadius(3);
                fakeExplosion.sendPacket(player);

                WrapperPlayServerWorldParticles particle = new WrapperPlayServerWorldParticles();
                particle.setParticleType(EnumWrappers.Particle.CRIT_MAGIC);
                particle.setX((float) location.getX());
                particle.setY((float) location.getY());
                particle.setZ((float) location.getZ());
                particle.setOffsetX(1);
                particle.setOffsetY(1);
                particle.setOffsetZ(1);
                particle.setNumberOfParticles(1000);
                particle.sendPacket(player);

                WrapperPlayServerNamedSoundEffect sound = new WrapperPlayServerNamedSoundEffect();
                sound.setSoundEffect(Sound.ENTITY_ENDERDRAGON_DEATH);
                sound.setVolume(1);
                sound.setPitch(1);
                sound.setEffectPositionX((int) location.getX() * 8);
                sound.setEffectPositionY((int) location.getY() * 8);
                sound.setEffectPositionZ((int) location.getZ() * 8);
                sound.setSoundCategory(EnumWrappers.SoundCategory.NEUTRAL);
                sound.sendPacket(player);

                location.subtract(x,y,z);


                ++time;
                if(time > 20)
                {
                    cancel();
                }
            }
        }.runTaskTimer(Magica.getInstance(), 0, 5);
    }
}
