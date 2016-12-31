package pl.ant.magica;

import org.bukkit.entity.Player;

import java.io.Serializable;

/**
 * Created by Mikołaj on 29.12.2016.
 */
public class Mage implements Serializable{
    private final Controller controller;
    private Player player;
    private Spellbook spellbook;
    private Stats stats;
    private EQ eq;

    Mage(Controller controller){
        this.controller = controller;
    }

    public Player getPlayer() {
        return player;
    }

    public Spellbook getSpellbook() {
        return spellbook;
    }

    public EQ getEq() {
        return eq;
    }

    public Stats getStats() {
        return stats;
    }

    // TODO zaimplementować spellbook żeby poniższe metody działały
    public void cast() {
        spellbook.getSelectedSpell().execute(player);
    }

    public void teach(Spellbook spellbook){
        spellbook.insert(spellbook);
    }

    public void unteach(Spellbook spellbook){
        spellbook.erase(spellbook);
    }

}
