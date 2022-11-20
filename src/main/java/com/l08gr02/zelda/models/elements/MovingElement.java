package com.l08gr02.zelda.models.elements;

public abstract class MovingElement extends Element {
    // constructors
    public MovingElement(int x, int y){
        super(x, y);
    }

    // methods
    public void up(int i){
        setPosition(getPosition().up(i));
    }

    public void down(int i){
        setPosition(getPosition().down(i));
    }

    public void left(int i){
        setPosition(getPosition().left(i));
    }

    public void right(int i){
        setPosition(getPosition().right(i));
    }
}