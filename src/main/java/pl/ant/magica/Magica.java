package pl.ant.magica;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import sun.util.logging.PlatformLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created on 2016-11-26.
 */
public class Magica extends JavaPlugin implements Listener{

    public static final Logger logger = Logger.getLogger("Magica");
    private static String version;
    private Magica() {}

    private static Magica Instance = new Magica();

    public static Magica getInstance() {    return Instance;    }

    @Override
    public void onEnable() {
        logger.log(Level.INFO, "enabling Magica...");

        logger.log(Level.INFO, "Magica enabled");
    }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "disabling Magica...");

        logger.log(Level.INFO, "Magica disabled");
    }
}
