package pl.ant.magica;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.ant.magica.spells.SpellEnum;

/**
 * Created by Arthoom on 03.12.2016, 15:23
 */
class Wand implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().getType() != Material.STICK) return;
        switch(event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                onLeftClick(player);
                return;
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                if(player.isSneaking())
                {
                    onRightClickAndShift(player);
                }
                else
                {
                    onRightClick(player);
                }
                return;
            default:
        }
    }

    //WARNING!!! Until Dumbledore / Mage / Wizard class doesn't exist, I'm doing it in a slow and pointless way!
    private void onLeftClick(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String name = itemMeta.getDisplayName();
        if(name == null || name.equals("Stick")) {
            onRightClick(player);
        }
        int spellID = SpellEnum.getIDFromSpellName(name);
        SpellEnum spell = SpellEnum.getSpellEnumFromID(spellID);
        spell.execute(player);
    }

    private void onRightClick(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String name = itemMeta.getDisplayName();
        if(name == null || name.equals("Stick")) {
            String newName = SpellEnum.getSpellEnumFromID(1).getName();
            itemMeta.setDisplayName(newName);
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            return;
        }
        int spellID = SpellEnum.getIDFromSpellName(name);
        if(++spellID > SpellEnum.getGreatestID()) {
            spellID = 1;
        }
        String newName = SpellEnum.getSpellEnumFromID(spellID).getName();
        itemMeta.setDisplayName(newName);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(itemStack);
    }

    private void onRightClickAndShift(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = itemStack.getItemMeta();
        String name = itemMeta.getDisplayName();
        if(name == null || name.equals("Stick")) {
            String newName = SpellEnum.getSpellEnumFromID(1).getName();
            itemMeta.setDisplayName(newName);
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            return;
        }
        int spellID = SpellEnum.getIDFromSpellName(name);
        if(--spellID == 0) {
            spellID = SpellEnum.getGreatestID();
        }
        String newName = SpellEnum.getSpellEnumFromID(spellID).getName();
        itemMeta.setDisplayName(newName);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(itemStack);
    }
}