package com.github.danielsl.regrow.actors.mobs.machines;

import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.scenes.GameScene;
import com.github.danielsl.regrow.utils.GLog;
import com.github.danielsl.regrow.windows.WndConstructionCore;

public class Harverster extends Machine {

    public void doWork() {


        for (int n : Level.NEIGHBOURS8) {
            int c = this.pos + n;

            Dungeon.level.press(c, this);
        }
    }

    @Override
    public void interact(){

    }
}
