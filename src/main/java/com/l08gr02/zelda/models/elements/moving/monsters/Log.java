package com.l08gr02.zelda.models.elements.moving.monsters;

import com.l08gr02.zelda.models.elements.Hitbox;

public class Log extends Monster {
    // construtor
    public Log(int x, int y){
        super(x, y);
        setHitbox(new Hitbox(x + 8, y + 12, 16, 16));

        // definir os stats
        setHearts(2);
        WALK_SPEED = 1;

        setSpeed(WALK_SPEED);
    }

    // métodos
    @Override
    public void attack() {
    }

}
