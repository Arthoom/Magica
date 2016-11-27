package pl.ant.magica;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
    private static ProtocolManager protocolManager;

    private static Magica Instance;

    public static Magica getInstance() {    return Instance;    }

    @Override
    public void onEnable() {
        Instance = this;
        logger.log(Level.INFO, "enabling Magica...");
        protocolManager = ProtocolLibrary.getProtocolManager();
        logger.log(Level.INFO, "Magica enabled");
    }

    public static ProtocolManager getProtocolManager() { return protocolManager; }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "disabling Magica...");

        logger.log(Level.INFO, "Magica disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return CommandManager.onCommand(sender, cmd, label, args);
    }

    public void registerEvents(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
