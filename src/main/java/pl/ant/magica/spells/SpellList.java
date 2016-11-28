package pl.ant.magica.spells;

import org.bukkit.entity.Player;

/**
 * Created by Arthoom on 27.11.2016, 20:32
 */
public enum SpellList {
    SPELL_PROJECTILE_EXAMPLE(new pl.ant.magica.spells.projectile.Example());
    //etc...





    private final Spell spell;
    SpellList(Spell spell) {
        this.spell = spell;
    }

    public void execute(Player player) {
        spell.execute(player);
    }
}
