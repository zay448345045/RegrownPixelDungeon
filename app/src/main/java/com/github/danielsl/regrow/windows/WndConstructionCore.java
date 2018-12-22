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
package com.github.danielsl.regrow.windows;

import com.github.danielsl.regrow.Challenges;
import com.github.danielsl.regrow.Dungeon;
import com.github.danielsl.regrow.actors.hero.Hero;
import com.github.danielsl.regrow.actors.mobs.npcs.Ghost;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.scenes.PixelScene;
import com.github.danielsl.regrow.sprites.FetidRatSprite;
import com.github.danielsl.regrow.ui.RedButton;
import com.github.danielsl.regrow.ui.Window;
import com.github.danielsl.regrow.utils.GLog;
import com.watabou.noosa.BitmapTextMultiline;

public class WndConstructionCore extends Window {

    private static final String TXT_RAT = "Thank you, that horrid rat is slain and I can finally rest..."
            + "I wonder what twisted magic created such a foul creature...\n\n";
    private static final String TXT_GNOLL = "Thank you, that scheming gnoll is slain and I can finally rest..."
            + "I wonder what twisted magic made it so smart...\n\n";
    private static final String TXT_CRAB = "Thank you, that giant crab is slain and I can finally rest..."
            + "I wonder what twisted magic allowed it to live so long...\n\n";
    private static final String TXT_GIVEITEM = "Please take one of these items, they are useless to me now... "
            + "Maybe they will help you in your journey...\n\n"
            + "Also... There is an item lost in this dungeon that is very dear to me..."
            + "If you ever... find my... rose......";
    private static final String TXT_WEAPON = "Ghost's weapon";
    private static final String TXT_ARMOR = "Ghost's armor";

    private static final int WIDTH = 120;
    private static final int BTN_HEIGHT = 20;
    private static final float GAP = 2;

    public WndConstructionCore(/*final ConstructionCore core*/) {

        super();

        IconTitle titlebar = new IconTitle();
        BitmapTextMultiline message;

                titlebar.icon(new FetidRatSprite());
                titlebar.label("DEFEATED FETID RAT");
                message = PixelScene.createMultiline(TXT_RAT + TXT_GIVEITEM, 6);



        titlebar.setRect(0, 0, WIDTH, 0);
        add(titlebar);

        message.maxWidth = WIDTH;
        message.measure();
        message.y = titlebar.bottom() + GAP;
        add(message);

        RedButton btnWeapon = new RedButton("ACTIVATE") {
            @Override
            protected void onClick() {
                //selectReward(ghost, Ghost.Quest.weapon);
            }
        };
        btnWeapon.setRect(0, message.y + message.height() + GAP, WIDTH,
                BTN_HEIGHT);
        add(btnWeapon);

        if (!Dungeon.isChallenged(Challenges.NO_ARMOR)) {
            RedButton btnArmor = new RedButton(TXT_ARMOR) {
                @Override
                protected void onClick() {
                    //selectReward(ghost, Ghost.Quest.armor);
                }
            };
            btnArmor.setRect(0, btnWeapon.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnArmor);

            resize(WIDTH, (int) btnArmor.bottom());
        } else {
            resize(WIDTH, (int) btnWeapon.bottom());
        }
    }

    private void selectReward(Ghost ghost, Item reward) {

        hide();

        if (reward.doPickUp(Dungeon.hero)) {
            GLog.i(Hero.TXT_YOU_NOW_HAVE, reward.name());
        } else {
            Dungeon.level.drop(reward, ghost.pos).sprite.drop();
        }

        ghost.yell("Farewell, adventurer!");
        ghost.die(null);

        Ghost.Quest.complete();
    }
}
