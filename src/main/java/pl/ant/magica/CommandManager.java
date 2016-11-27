package pl.ant.magica;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ant.magica.spells.SpellHeal;
import pl.ant.magica.spells.Test;

/**
 * Created by Arthoom on 26.11.2016, 23:16.
 */
public class CommandManager {
    public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player) || args == null) {
            return false;
        }
        Player player = (Player) sender;
        if(args[0].equals("test")) {
            Test.execute(player);
        }
        return true;
    }
}
