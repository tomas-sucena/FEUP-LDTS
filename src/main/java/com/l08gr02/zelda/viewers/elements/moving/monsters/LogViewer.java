package com.l08gr02.zelda.viewers.elements.moving.monsters;

import com.l08gr02.zelda.models.Sprite;
import com.l08gr02.zelda.models.elements.moving.monsters.Log;

import static com.l08gr02.zelda.presenters.GameplayPresenter.ACTION;

public class LogViewer extends MonsterViewer<Log> {
    private boolean sleeping;

    // constructor
    public LogViewer(){
        super(new Sprite(32,32,"monsters","log"));
    }

    // methods
    public boolean isSleeping() {
        return sleeping;
    }

    @Override
    public void setSprite(ACTION action) {
        switch (action) {
            case UP -> yGrid = 1;

            case DOWN -> yGrid = 0;

            case LEFT -> yGrid = 3;

            case RIGHT -> yGrid = 2;

            case NOTHING -> {
                if (!sleeping){
                    sleeping = true;
                }
            }
        }

        xGrid = (xGrid >= 3) ? 0 : xGrid + 1;

        sprite.setPixels(xGrid, yGrid);
    }

}
