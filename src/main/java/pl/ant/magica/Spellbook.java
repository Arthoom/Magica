package pl.ant.magica;

import pl.ant.magica.spells.SpellEnum;

import javax.annotation.Nullable;

/**
 * Created by Mikołaj on 29.12.2016.
 */
public class Spellbook {

    Spellbook(){}

    ///TODO spellbook - podstawowe metody
    @Nullable
    public SpellEnum getSelectedSpell(){
        return null;
    }

    public void getSpellList(){}

    public void insert(Spellbook spellbook){}
    public void erase(Spellbook spellbook){}
}
