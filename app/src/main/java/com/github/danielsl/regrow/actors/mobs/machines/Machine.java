/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.github.danielsl.regrow.actors.mobs.machines;

import java.util.HashSet;

import com.github.danielsl.regrow.Assets;
import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.Journal;
import com.github.danielsl.regrow.Statistics;
import com.github.danielsl.regrow.actors.Actor;
import com.github.danielsl.regrow.actors.Char;
import com.github.danielsl.regrow.actors.blobs.Blob;
import com.github.danielsl.regrow.actors.blobs.Fire;
import com.github.danielsl.regrow.actors.blobs.StenchGas;
import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.actors.buffs.Burning;
import com.github.danielsl.regrow.actors.buffs.Ooze;
import com.github.danielsl.regrow.actors.buffs.Paralysis;
import com.github.danielsl.regrow.actors.buffs.Poison;
import com.github.danielsl.regrow.actors.buffs.Roots;
import com.github.danielsl.regrow.actors.mobs.Crab;
import com.github.danielsl.regrow.actors.mobs.Gnoll;
import com.github.danielsl.regrow.actors.mobs.Mob;
import com.github.danielsl.regrow.actors.mobs.Rat;
import com.github.danielsl.regrow.actors.mobs.npcs.Ghost;
import com.github.danielsl.regrow.actors.mobs.npcs.NPC;
import com.github.danielsl.regrow.effects.CellEmitter;
import com.github.danielsl.regrow.effects.Speck;
import com.github.danielsl.regrow.items.Generator;
import com.github.danielsl.regrow.items.Gold;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.items.SewersKey;
import com.github.danielsl.regrow.items.TenguKey;
import com.github.danielsl.regrow.items.armor.Armor;
import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
import com.github.danielsl.regrow.items.food.MysteryMeat;
import com.github.danielsl.regrow.items.journalpages.SafeSpotPage;
import com.github.danielsl.regrow.items.wands.Wand;
import com.github.danielsl.regrow.items.weapon.Weapon;
import com.github.danielsl.regrow.items.weapon.missiles.CurareDart;
import com.github.danielsl.regrow.items.weapon.missiles.ForestDart;
import com.github.danielsl.regrow.items.weapon.missiles.MissileWeapon;
import com.github.danielsl.regrow.levels.Level;
import com.github.danielsl.regrow.levels.SewerLevel;
import com.github.danielsl.regrow.levels.features.HighGrass;
import com.github.danielsl.regrow.levels.traps.LightningTrap;
import com.github.danielsl.regrow.mechanics.Ballistica;
import com.github.danielsl.regrow.scenes.GameScene;
import com.github.danielsl.regrow.sprites.CharSprite;
import com.github.danielsl.regrow.sprites.FetidRatSprite;
import com.github.danielsl.regrow.sprites.GhostSprite;
import com.github.danielsl.regrow.sprites.GnollArcherSprite;
import com.github.danielsl.regrow.sprites.GnollTricksterSprite;
import com.github.danielsl.regrow.sprites.GreatCrabSprite;
import com.github.danielsl.regrow.sprites.TowerSprite;
import com.github.danielsl.regrow.utils.GLog;
import com.github.danielsl.regrow.utils.Utils;
import com.github.danielsl.regrow.windows.WndConstructionCore;
import com.github.danielsl.regrow.windows.WndQuest;
import com.github.danielsl.regrow.windows.WndSadGhost;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Highlighter;
import com.watabou.utils.Random;

public abstract class Machine extends NPC {

    {
        name = "arcane construction core";
        spriteClass = TowerSprite.class;

        flying = false;

        state = PASSIVE;
    }

    @Override
    protected boolean act() {
        super.act();

        boolean justAlerted = alerted;
        alerted = false;

        this.doWork();

        boolean enemyInFOV = enemy != null && enemy.isAlive()
                && Level.fieldOfView[enemy.pos] && enemy.invisible<=0 ;

        return state.act(enemyInFOV, justAlerted);
    }

    public abstract void doWork();


    public Machine() {
        super();
    }

    @Override
    public int defenseSkill(Char enemy) {
        return 1000;
    }

    @Override
    public String defenseVerb() {
        return "evaded";
    }

    @Override
    public float speed() {
        return 1f;
    }

    @Override
    protected Char chooseEnemy() {
        return null;
    }

    @Override
    public void damage(int dmg, Object src) {
    }

    @Override
    public void add(Buff buff) {
    }

    @Override
    public boolean reset() {
        return true;
    }

    private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
    static {
        IMMUNITIES.add(Paralysis.class);
        IMMUNITIES.add(Roots.class);
    }

    @Override
    public HashSet<Class<?>> immunities() {
        return IMMUNITIES;
    }

}
