package com.github.danielsl.regrow.items.weapon.melee;

import com.github.danielsl.regrow.actors.buffs.Buff;
import com.github.danielsl.regrow.actors.hero.Hero;
import com.github.danielsl.regrow.sprites.ItemSpriteSheet;

public class SacrificialDagger extends Dagger {

    {
        name = "Sacrificial Dagger";
        image = ItemSpriteSheet.SACRIFICIAL_DAGGER;
    }

    @Override
    public void activate(Hero hero) {
        super.activate(hero);
        new SoulReaper().attachTo(hero);
    }

    public class SoulReaper extends Buff {

    }
}



