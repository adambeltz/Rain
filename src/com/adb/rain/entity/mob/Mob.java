package com.adb.rain.entity.mob;

import com.adb.rain.entity.Entity;
import com.adb.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;  // 0 is north...etc- dir is direction
    protected  boolean moving = false;
    protected boolean walking = false;

    public void move(int xa, int ya) {//parameters represent change on x and y axis
        if (xa > 0) dir = 1;
        if (xa < 0) dir = 3;
        if (ya > 0) dir = 2;
        if (ya < 0) dir = 0;


        if (!collision(xa, ya)){
            x += xa;
            y += ya;
        }

    }

    public void update(){

    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        if (level.getTile((x+xa)/16, (y+ya)/16).solid()) solid = true;
        //System.out.println((x+xa)/16 + " "+ (y + ya)/16);
        return solid;
    }

    public void render() {
    }
}
