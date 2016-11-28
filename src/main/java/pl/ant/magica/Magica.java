package pl.ant.magica;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created on 2016-11-26.
 */
public class Magica extends JavaPlugin implements Listener{

    private static final Logger logger = Logger.getLogger("Magica");
    //private static String version;
    private static Magica instance;

    public Magica() {}

    @Override
    public void onEnable() {
        instance = this;
        logger.info(ChatColor.YELLOW + "enabling Magica...");
        logger.info(ChatColor.YELLOW + "Magica enabled");

    }



    @Override
    public void onDisable() {
        logger.info(ChatColor.YELLOW + "disabling Magica...");

        logger.info(ChatColor.YELLOW + "Magica disabled");
    }




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return CommandManager.onCommand(sender, args);
    }

    //public void registerEvents(Listener listener) {
    //    getServer().getPluginManager().registerEvents(listener, this);
    //}




    public static Magica getInstance() {    return instance;    }
}
