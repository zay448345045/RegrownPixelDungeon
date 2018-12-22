package com.github.danielsl.regrow.actors.mobs.machines;

import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.effects.CheckedCell;
import com.github.danielsl.regrow.items.Dewdrop;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.items.armor.Armor;
import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
import com.github.danielsl.regrow.items.weapon.Weapon;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.scenes.GameScene;
import com.github.danielsl.regrow.utils.GLog;
import com.github.danielsl.regrow.windows.WndCollector;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class Collector extends Machine{


    {
        name = "Collector";
    }

    protected static final float TIME_TO_PICK_UP = 1.0f;

    ArrayList<Item> collectedItems = new ArrayList<>();

    @Override
    public void showAOE() {
        for (int n : Level.NEIGHBOURS9) {
            int c = this.pos + n + 2 * getAOE();
            sprite.parent.addToBack(new CheckedCell(c));
        }
    }
    public void doWork() {


        for (int n : Level.NEIGHBOURS9) {
            int c = this.pos + n + 2*getAOE();

            if(Dungeon.level.heaps.get(c)!=null)
            collectedItems.add(Dungeon.level.heaps.get(c).pickUp());
        }
    }

    @Override
    public void interact(){
        GameScene.show(new WndCollector(this));
    }

    public void collectItems(){
        for(Item i : collectedItems) {
            if(!i.doPickUpNoTime(Dungeon.hero)) i.doDrop(Dungeon.hero);

        }
        Dungeon.hero.spendAndNext(TIME_TO_PICK_UP);
        collectedItems.clear();
    }

}
