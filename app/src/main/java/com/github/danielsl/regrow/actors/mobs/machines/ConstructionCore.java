///*
// * Pixel Dungeon
// * Copyright (C) 2012-2014  Oleg Dolya
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>
// */
//package com.github.danielsl.regrow.actors.mobs.machines;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//import com.github.danielsl.regrow.Assets;
//import com.github.danielsl.regrow.Dungeon;
//import com.github.danielsl.regrow.Journal;
//import com.github.danielsl.regrow.Statistics;
//import com.github.danielsl.regrow.actors.Actor;
//import com.github.danielsl.regrow.actors.Char;
//import com.github.danielsl.regrow.actors.blobs.Blob;
//import com.github.danielsl.regrow.actors.blobs.Fire;
//import com.github.danielsl.regrow.actors.blobs.StenchGas;
//import com.github.danielsl.regrow.actors.buffs.Buff;
//import com.github.danielsl.regrow.actors.buffs.Burning;
//import com.github.danielsl.regrow.actors.buffs.Ooze;
//import com.github.danielsl.regrow.actors.buffs.Paralysis;
//import com.github.danielsl.regrow.actors.buffs.Poison;
//import com.github.danielsl.regrow.actors.buffs.Roots;
//import com.github.danielsl.regrow.actors.mobs.Crab;
//import com.github.danielsl.regrow.actors.mobs.Gnoll;
//import com.github.danielsl.regrow.actors.mobs.Mob;
//import com.github.danielsl.regrow.actors.mobs.Rat;
//import com.github.danielsl.regrow.actors.mobs.npcs.Ghost;
//import com.github.danielsl.regrow.actors.mobs.npcs.NPC;
//import com.github.danielsl.regrow.effects.CellEmitter;
//import com.github.danielsl.regrow.effects.Speck;
//import com.github.danielsl.regrow.items.Generator;
//import com.github.danielsl.regrow.items.Gold;
//import com.github.danielsl.regrow.items.Item;
//import com.github.danielsl.regrow.items.SewersKey;
//import com.github.danielsl.regrow.items.TenguKey;
//import com.github.danielsl.regrow.items.armor.Armor;
//import com.github.danielsl.regrow.items.artifacts.SandalsOfNature;
//import com.github.danielsl.regrow.items.food.MysteryMeat;
//import com.github.danielsl.regrow.items.journalpages.SafeSpotPage;
//import com.github.danielsl.regrow.items.wands.Wand;
//import com.github.danielsl.regrow.items.weapon.Weapon;
//import com.github.danielsl.regrow.items.weapon.missiles.CurareDart;
//import com.github.danielsl.regrow.items.weapon.missiles.ForestDart;
//import com.github.danielsl.regrow.items.weapon.missiles.MissileWeapon;
//import com.github.danielsl.regrow.levels.Level;
//import com.github.danielsl.regrow.levels.SewerLevel;
//import com.github.danielsl.regrow.levels.features.HighGrass;
//import com.github.danielsl.regrow.levels.traps.LightningTrap;
//import com.github.danielsl.regrow.mechanics.Ballistica;
//import com.github.danielsl.regrow.scenes.GameScene;
//import com.github.danielsl.regrow.sprites.CharSprite;
//import com.github.danielsl.regrow.sprites.FetidRatSprite;
//import com.github.danielsl.regrow.sprites.GhostSprite;
//import com.github.danielsl.regrow.sprites.GnollArcherSprite;
//import com.github.danielsl.regrow.sprites.GnollTricksterSprite;
//import com.github.danielsl.regrow.sprites.GreatCrabSprite;
//import com.github.danielsl.regrow.sprites.TowerSprite;
//import com.github.danielsl.regrow.utils.GLog;
//import com.github.danielsl.regrow.utils.Utils;
//import com.github.danielsl.regrow.windows.machineWindows.WndCollector.WndConstructionCore;
//import com.github.danielsl.regrow.windows.WndQuest;
//import com.github.danielsl.regrow.windows.WndSadGhost;
//import com.watabou.noosa.audio.Sample;
//import com.watabou.utils.Bundle;
//import com.watabou.utils.Highlighter;
//import com.watabou.utils.Random;
//
//public class ConstructionCore extends NPC {
//
//    {
//        name = "arcane construction core";
//        spriteClass = TowerSprite.class;
//
//        flying = false;
//
//        state = PASSIVE;
//    }
//
//
//    ArrayList<Machine> linkedMachines = new ArrayList<>();
//
//
//    @Override
//    protected boolean act() {
//        super.act();
//
//        boolean justAlerted = alerted;
//        alerted = false;
//
//        this.doWork();
//
//        boolean enemyInFOV = enemy != null && enemy.isAlive()
//                && Level.fieldOfView[enemy.pos] && enemy.invisible<=0 ;
//
//        return state.act(enemyInFOV, justAlerted);
//    }
//
//    public void doWork(){
//
//        for(Machine m : linkedMachines){
//            m.doWork();
//        }
//
//        Buff.affect(this, SandalsOfNature.Naturalism.class);
//        for (int n : Level.NEIGHBOURS8) {
//            int c = this.pos + n;
//
//            Dungeon.level.press(c, this);
//        }
//    }
//
//
//    public ConstructionCore() {
//        super();
//
//        Sample.INSTANCE.load(Assets.SND_TRAP);
//    }
//
//    @Override
//    public int defenseSkill(Char enemy) {
//        return 1000;
//    }
//
//    @Override
//    public String defenseVerb() {
//        return "evaded";
//    }
//
//    @Override
//    public float speed() {
//        return 0.0f;
//    }
//
//    @Override
//    protected Char chooseEnemy() {
//        return null;
//    }
//
//    @Override
//    public void damage(int dmg, Object src) {
//    }
//
//    @Override
//    public void add(Buff buff) {
//    }
//
//    @Override
//    public boolean reset() {
//        return true;
//    }
//
//    @Override
//    public void interact() {
//
//
//        Sample.INSTANCE.play(Assets.SND_TRAP);
//        GameScene.show(new WndConstructionCore(this));
//
//    }
//
//
//
//
//    @Override
//    public String description() {
//        return "The ghost is barely visible. It looks like a shapeless "
//                + "spot of faint light with a sorrowful face.";
//    }
//
//    private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
//    static {
//        IMMUNITIES.add(Paralysis.class);
//        IMMUNITIES.add(Roots.class);
//    }
//
//    @Override
//    public HashSet<Class<?>> immunities() {
//        return IMMUNITIES;
//    }
//
//    public static class Quest {
//
//        private static boolean spawned;
//
//        private static int type;
//
//        private static boolean given;
//        public static boolean processed;
//
//        private static int depth;
//
//        public static Weapon weapon;
//        public static Armor armor;
//
//        public static void reset() {
//            spawned = false;
//
//            weapon = null;
//            armor = null;
//        }
//
//        private static final String NODE = "sadGhost";
//
//        private static final String SPAWNED = "spawned";
//        private static final String TYPE = "type";
//        private static final String GIVEN = "given";
//        private static final String PROCESSED = "processed";
//        private static final String DEPTH = "depth";
//        private static final String WEAPON = "weapon";
//        private static final String ARMOR = "armor";
//
//        public static void storeInBundle(Bundle bundle) {
//
//            Bundle node = new Bundle();
//
//            node.put(SPAWNED, spawned);
//
//            if (spawned) {
//
//                node.put(TYPE, type);
//
//                node.put(GIVEN, given);
//                node.put(DEPTH, depth);
//                node.put(PROCESSED, processed);
//
//                node.put(WEAPON, weapon);
//                node.put(ARMOR, armor);
//            }
//
//            bundle.put(NODE, node);
//        }
//
//        public static void restoreFromBundle(Bundle bundle) {
//
//            Bundle node = bundle.getBundle(NODE);
//
//            if (!node.isNull() && (spawned = node.getBoolean(SPAWNED))) {
//
//                type = node.getInt(TYPE);
//                given = node.getBoolean(GIVEN);
//                processed = node.getBoolean(PROCESSED);
//
//                depth = node.getInt(DEPTH);
//
//                weapon = (Weapon) node.get(WEAPON);
//                armor = (Armor) node.get(ARMOR);
//            } else {
//                reset();
//            }
//        }
//
//        public static void spawn(SewerLevel level) {
//            if (!spawned && Dungeon.depth > 1
//                    && Random.Int(5 - Dungeon.depth) == 0) {
//
//                Ghost ghost = new Ghost();
//                do {
//                    ghost.pos = level.randomRespawnCell();
//                } while (ghost.pos == -1);
//                level.mobs.add(ghost);
//                Actor.occupyCell(ghost);
//
//                spawned = true;
//                // dungeon depth determines type of quest.
//                // depth2=fetid rat, 3=gnoll trickster, 4=great crab
//                type = Dungeon.depth - 1;
//
//                given = false;
//                processed = false;
//                depth = Dungeon.depth;
//
//                do {
//                    weapon = Generator.randomWeapon(10);
//                } while (weapon instanceof MissileWeapon);
//                armor = Generator.randomArmor(10);
//
//                for (int i = 1; i <= 3; i++) {
//                    Item another;
//                    do {
//                        another = Generator.randomWeapon(10 + i);
//                    } while (another instanceof MissileWeapon);
//                    if (another.level >= weapon.level) {
//                        weapon = (Weapon) another;
//                    }
//                    another = Generator.randomArmor(10 + i);
//                    if (another.level >= armor.level) {
//                        armor = (Armor) another;
//                    }
//                }
//
//                weapon.identify();
//                armor.identify();
//            }
//        }
//
//        public static void process() {
//            if (spawned && given && !processed && (depth == Dungeon.depth)) {
//                GLog.n("sad ghost: Thank you... come find me...");
//                Sample.INSTANCE.play(Assets.SND_GHOST);
//                processed = true;
//                Generator.Category.ARTIFACT.probs[10] = 1; // flags the dried
//                // rose as
//                // spawnable.
//            }
//        }
//
//        public static void complete() {
//            weapon = null;
//            armor = null;
//
//            Journal.remove(Journal.Feature.GHOST);
//        }
//    }
//
//
//}
