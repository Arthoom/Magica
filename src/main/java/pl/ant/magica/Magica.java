package pl.ant.magica;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created on 2016-11-26.
 */
public class Magica extends JavaPlugin implements Listener{

    private Logger logger;

    //private static String version;

    public Magica() {}

    private static Magica instance;

    public static Magica getInstance() {    return instance;    }

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        logger.info(ChatColor.YELLOW + "enabling Magica...");

        logger.info(ChatColor.YELLOW + "Magica enabled");
    }

    @Override
    public void onDisable() {
        logger.info(ChatColor.YELLOW + "disabling Magica...");

        logger.info(ChatColor.YELLOW + "Magica disabled");
    }
}
