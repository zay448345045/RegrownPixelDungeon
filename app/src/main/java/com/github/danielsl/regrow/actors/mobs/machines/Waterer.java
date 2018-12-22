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

        int positive = 0;
        int negative = 0;

        int distance = 1 + positive + negative;


        int cx = this.pos % Level.getWidth();
        int cy = this.pos / Level.getWidth();
        int ax = cx - distance;
        if (ax < 0) {
            ax = 0;
        }
        int bx = cx + distance;
        if (bx >= Level.getWidth()) {
            bx = Level.getWidth() - 1;
        }
        int ay = cy - distance;
        if (ay < 0) {
            ay = 0;
        }
        int by = cy + distance;
        if (by >= Level.HEIGHT) {
            by = Level.HEIGHT - 1;
        }


        for (int y = ay; y <= by; y++) {
            for (int x = ax, p = ax + y * Level.getWidth(); x <= bx; x++, p++) {

                if (Dungeon.visible[p]) {
                    int c = Dungeon.level.map[p];

                    if (c == Terrain.GRASS) {
                        GameScene.add(Blob.seed(p, (2) * 20, Water.class));
                    }
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
