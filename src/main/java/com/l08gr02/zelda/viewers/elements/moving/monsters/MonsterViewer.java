package com.l08gr02.zelda.viewers.elements.moving.monsters;

import com.l08gr02.zelda.gui.GUI;
import com.l08gr02.zelda.models.Sprite;
import com.l08gr02.zelda.models.elements.moving.monsters.Monster;
import com.l08gr02.zelda.viewers.elements.moving.MoverViewer;

import static com.l08gr02.zelda.presenters.GameplayPresenter.ACTION;

public abstract class MonsterViewer<T extends Monster> extends MoverViewer<T> {
    // constructor
    public MonsterViewer(Sprite sprite){
        super(sprite);
    }

    // methods
    @Override
    public void draw(GUI gui, T model){
        super.draw(gui, model);
    }

    @Override
    public abstract void setSprite(ACTION action);
}
