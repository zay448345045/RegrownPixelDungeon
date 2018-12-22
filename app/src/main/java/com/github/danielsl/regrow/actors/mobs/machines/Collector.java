package com.github.danielsl.regrow.actors.mobs.machines;

import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.items.Dewdrop;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.utils.GLog;

import java.util.ArrayList;

public class Collector extends Machine{


    protected static final float TIME_TO_PICK_UP = 1.0f;

    ArrayList<Item> collectedItems = new ArrayList<>();

    public void doWork() {


        for (int n : Level.NEIGHBOURS8) {
            int c = this.pos + n + 2;

            if(Dungeon.level.heaps.get(c)!=null)
            collectedItems.add(Dungeon.level.heaps.get(c).pickUp());
        }
    }

    @Override
    public void interact(){
        for(Item i : collectedItems) {
            if(!i.doPickUpNoTime(Dungeon.hero)) i.doDrop(Dungeon.hero);

        }
        Dungeon.hero.spendAndNext(TIME_TO_PICK_UP);
        collectedItems.clear();
    }

}
