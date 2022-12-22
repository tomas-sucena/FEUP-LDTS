package com.l08gr02.zelda.presenters.elements.monsters;

import com.l08gr02.zelda.gui.GUI;
import com.l08gr02.zelda.models.elements.moving.monsters.Monster;
import com.l08gr02.zelda.presenters.elements.FighterPresenter;
import com.l08gr02.zelda.viewers.Viewer;

public abstract class MonsterPresenter<T extends Monster> extends FighterPresenter<T> {
    // constructor
    public MonsterPresenter(T model, Viewer<T> viewer) {
        super(model, viewer);
    }

    // methods
    protected boolean mustUpdate(GUI gui){
        super.decreaseImmunity();

        return model.collidesWith(gui.getCamera());
    }

    @Override
    public abstract void update(GUI gui);
}
