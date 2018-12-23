package com.github.danielsl.regrow.items;

import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.mobs.Mob;
import com.github.danielsl.regrow.actors.mobs.Rat;
import com.github.danielsl.regrow.actors.mobs.npcs.Ghost;
import com.github.danielsl.regrow.sprites.ItemSpriteSheet;
import com.watabou.utils.Bundle;

public class Soul extends Item {


    {
        name = "Monster Soul";
        image = ItemSpriteSheet.SOUL;

        unique = true;
    }

    public Class<? extends Mob> monsterClass;

    public Soul(Class<? extends Mob> monsterClass){
        this.monsterClass = monsterClass;
    }

    @Override
    public boolean isUpgradable() {
        return false;
    }

    @Override
    public boolean isIdentified() {
        return true;
    }


    @Override
    public String info() {
        return "A monster soul.";
    }

    public static final String MONSTER_CLASS = "monsterClass";

    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(MONSTER_CLASS, this.monsterClass.getName());
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        try {
            this.monsterClass = Class.forName(bundle.getString(MONSTER_CLASS)).asSubclass(Mob.class);
        } catch (ClassNotFoundException e){
            this.monsterClass = Rat.class;
            e.printStackTrace();
        }
    }
}
