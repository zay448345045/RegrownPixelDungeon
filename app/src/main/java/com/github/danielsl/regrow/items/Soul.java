package com.github.danielsl.regrow.items;

import com.github.danielsl.regrow.Assets;
import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.Actor;
import com.github.danielsl.regrow.actors.Char;
import com.github.danielsl.regrow.actors.hero.Hero;
import com.github.danielsl.regrow.actors.mobs.Bestiary;
import com.github.danielsl.regrow.actors.mobs.Mob;
import com.github.danielsl.regrow.actors.mobs.Rat;
import com.github.danielsl.regrow.effects.Splash;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.scenes.GameScene;
import com.github.danielsl.regrow.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;

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

    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (isEquipped(hero) && !cursed) {
            actions.add(AC_SUMMON);
        }
        return actions;
    }

    public Item setSoulName(String soulName){
        Soul item = this;

        item.soulName = soulName;

        return item;
    }

    public void execute(Hero hero, String action) {
        if (action.equals(AC_SUMMON)) {

            if (!isEquipped(hero)){

            }

        }
        else	{
            super.execute(hero, action);
        }
    }


    @Override
    protected void onThrow(int cell) {
        if (Level.pit[cell]) {
            super.onThrow(cell);
        } else {
            Dungeon.level.drop(shatter(null, cell), cell);
        }
    }

    public Item shatter(Char owner, int pos) {

        if (Dungeon.visible[pos]) {
            Sample.INSTANCE.play(Assets.SND_SHATTER);
            Splash.at(pos, 0xffd500, 5);
        }

        int newPos = pos;
        if (Actor.findChar(pos) != null) {
            ArrayList<Integer> candidates = new ArrayList<Integer>();
            boolean[] passable = Level.passable;

            for (int n : Level.NEIGHBOURS4) {
                int c = pos + n;
                if (passable[c] && Actor.findChar(c) == null) {
                    candidates.add(c);
                }
            }

            newPos = candidates.size() > 0 ? Random.element(candidates) : -1;
        }

        if (newPos != -1) {


            Mob mob = Bestiary.getMobByName(this.soulName);
            mob.state = mob.WANDERING;
            mob.pos = newPos;

            GameScene.add(mob);

            Sample.INSTANCE.play(Assets.SND_BEE);
            return new ShatteredGlass();
        } else {
            return this;
        }
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
