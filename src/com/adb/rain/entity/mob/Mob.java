package com.adb.rain.entity.mob;

import com.adb.rain.entity.Entity;
import com.adb.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;  // 0 is north...etc- dir is direction
    protected  boolean moving = false;

    public void move() {

    }

    public void update(){

    }

    private boolean collision() {
        return false;
    }

    public void render() {
    }
}
