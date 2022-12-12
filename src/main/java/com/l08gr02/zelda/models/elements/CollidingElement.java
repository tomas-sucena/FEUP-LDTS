package com.l08gr02.zelda.models.elements;

import com.l08gr02.zelda.models.elements.actions.Collision;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class CollidingElement extends Element implements Collision {
    protected Hitbox hitbox;
    private List<CollidingElement> obstacles;

    // constructors
    public CollidingElement(int x, int y){
        super(x, y);

        obstacles = new LinkedList<>();
    }

    // methods
    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public List<CollidingElement> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<CollidingElement> obstacles) {
        this.obstacles = obstacles;
    }

    public void resetObstacles(){
        obstacles.clear();
    }

}
