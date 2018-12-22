package com.github.danielsl.regrow.actors.mobs.machines;

import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.blobs.Blob;
import com.github.danielsl.regrow.actors.blobs.Water;
import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.items.Dewdrop;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.levels.Terrain;
import com.github.danielsl.regrow.scenes.GameScene;
import com.github.danielsl.regrow.utils.GLog;

import java.util.ArrayList;

public class Waterer extends Machine{

    ArrayList<Item> collectedItems = new ArrayList<>();

    public void doWork() {


            for (int i : Level.NEIGHBOURS8) {
                int p = this.pos + i + 2*Level.getWidth();

                if (Dungeon.visible[p]) {
                    int c = Dungeon.level.map[p];

                    if (c == Terrain.GRASS) {
                        GameScene.add(Blob.seed(p, (2) * 20, Water.class));
                    }
                }
            }

    }

    @Override
    public void interact(){
        for(Item i : collectedItems) {
            i.doPickUp(Dungeon.hero);
        }
        collectedItems.clear();
    }

}
