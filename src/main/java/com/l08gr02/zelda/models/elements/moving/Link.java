package com.l08gr02.zelda.models.elements.moving;

import java.awt.*;

public class Link extends MovingElement {
    private static int WALK_SPEED = 2, SPRINT_SPEED = 4;

    // constructor
    public Link(int x, int y){
        super(x, y);
        hitbox = new Rectangle(x + 15, y + 24, 16, 16);

        hearts = 3;
        speed = WALK_SPEED;
    }

    // methods
    @Override
    public void attack() {
    }

    public void walk(){
        setSpeed(WALK_SPEED);
    }

    public void sprint(){
        setSpeed(SPRINT_SPEED);
    }

}