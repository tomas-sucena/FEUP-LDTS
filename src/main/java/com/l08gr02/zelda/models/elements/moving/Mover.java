package com.l08gr02.zelda.models.elements.moving;

import com.l08gr02.zelda.models.elements.CollidingElement;
import com.l08gr02.zelda.models.elements.actions.Movement;

import static com.l08gr02.zelda.presenters.GameplayPresenter.ACTION;

public abstract class Mover extends CollidingElement implements Movement {
    private int speed;
    private ACTION direction;

    // constructor
    public Mover(int x, int y) {
        super(x, y);
    }

    // methods
    @Override
    public int getSpeed(){
        return speed;
    }

    @Override
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public ACTION getDirection() {
        return direction;
    }

    public void setDirection(ACTION direction) {
        this.direction = direction;
    }

    @Override
    public void up(int i){
        setPosition(getPosition().up(i));

        // mover a hitbox
        setHitbox(getHitbox().up(i));
    }

    @Override
    public void down(int i){
        setPosition(getPosition().down(i));

        // move the hitbox
        setHitbox(getHitbox().down(i));
    }

    @Override
    public void left(int i){
        setPosition(getPosition().left(i));

        // move the hitbox
        setHitbox(getHitbox().left(i));
    }

    @Override
    public void right(int i){
        setPosition(getPosition().right(i));

        // move the hitbox
        setHitbox(getHitbox().right(i));
    }
}
