package pl.ant.magica.spells;

import org.bukkit.entity.Player;
import pl.ant.magica.Magica;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arthoom on 27.11.2016, 20:32
 */
public enum SpellEnum {
    PROJECTILE_EXAMPLE(new pl.ant.magica.spells.projectile.Example()),
    BUFF_EXAMPLE(new pl.ant.magica.spells.buff.Example());
    //etc...

    private final Spell spell;
    private static Map<SpellEnum, Integer> ids;
    private static Map<Integer, SpellEnum> enums;
    //below: key: spell name, value: id
    //temporary (because I'm waiting for Tooster's Dumbledore / Mage / Wizard class
    private static Map<String, Integer> nameOfSpell;
    private static int greatestID = 0;

    SpellEnum(Spell spell) {
        this.spell = spell;
    }

    public static void onEnable() {
        ids = new EnumMap<>(SpellEnum.class);
        enums = new HashMap<>();
        nameOfSpell = new HashMap<>();
        for(SpellEnum spellEnum : SpellEnum.values()) {
            addSpell(spellEnum);
        }
    }

    private static void addSpell(SpellEnum spellEnum) {
        ids.put(spellEnum, ++greatestID);
        enums.put(greatestID, spellEnum);
        nameOfSpell.put(spellEnum.spell.getName(), greatestID);
    }

    public static int getGreatestID() {
        return greatestID;
    }

    public void execute(Player player) {
        spell.execute(player);
    }

    public String getName() {
        return spell.getName();
    }

    @SuppressWarnings("unused")
    public int getID() {
        return ids.get(this);
    }

    public static SpellEnum getSpellEnumFromID(int id) {
        return enums.get(id);
    }

    //temporary
    public static int getIDFromSpellName(String name) {
        return nameOfSpell.get(name);
    }
}