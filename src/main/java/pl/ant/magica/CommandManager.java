package pl.ant.magica;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.ant.magica.spells.SpellEnum;

/**
 * Created by Arthoom on 26.11.2016, 23:16
 */
class CommandManager {
    static boolean onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player) || args == null) {
            return false;
        }
        Player player = (Player) sender;
        if(args[0].equals("test")) {
            SpellEnum.PROJECTILE_EXAMPLE.execute(player);
        }
        return true;
    }
}