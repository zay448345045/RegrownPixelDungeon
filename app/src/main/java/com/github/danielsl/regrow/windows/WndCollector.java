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
import com.github.danielsl.regrow.actors.mobs.machines.Collector;
import com.github.danielsl.regrow.actors.mobs.npcs.Ghost;
import com.github.danielsl.regrow.items.Item;
import com.github.danielsl.regrow.scenes.PixelScene;
import com.github.danielsl.regrow.sprites.FetidRatSprite;
import com.github.danielsl.regrow.sprites.TowerSprite;
import com.github.danielsl.regrow.ui.RedButton;
import com.github.danielsl.regrow.ui.Window;
import com.github.danielsl.regrow.utils.GLog;
import com.watabou.noosa.BitmapTextMultiline;

public class WndCollector extends Window {

    private static final int WIDTH = 120;
    private static final int BTN_HEIGHT = 20;
    private static final float GAP = 2;

    public WndCollector(final Collector collector) {

        super();

        IconTitle titlebar = new IconTitle();
        BitmapTextMultiline message;

        titlebar.icon(new TowerSprite());
        titlebar.label("Collector");
        message = PixelScene.createMultiline("collector blah blah blah sample text blah blah blah", 6);



        titlebar.setRect(0, 0, WIDTH, 0);
        add(titlebar);

        message.maxWidth = WIDTH;
        message.measure();
        message.y = titlebar.bottom() + GAP;
        add(message);

        RedButton btnRotate = new RedButton("ROTATE") {
            @Override
            protected void onClick() {
                collector.rotate();
                collector.showAOE();
                hide();
            }
        };
        btnRotate.setRect(0, message.y + message.height() + GAP, WIDTH,
                BTN_HEIGHT);
        add(btnRotate);

            RedButton btnCollect = new RedButton("COLLECT ITEMS") {
                @Override
                protected void onClick() {
                    collector.collectItems();
                    hide();

                }
            };
            btnCollect.setRect(0, btnRotate.bottom() + GAP, WIDTH, BTN_HEIGHT);
            add(btnCollect);

            resize(WIDTH, (int) btnCollect.bottom());

    }

}
