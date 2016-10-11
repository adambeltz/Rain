package com.adb.rain.entity.mob;

import com.adb.rain.entity.Entity;
import com.adb.rain.entity.projectile.Projectile;
import com.adb.rain.entity.projectile.WizardProjectile;
import com.adb.rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;  // 0 is north...etc- dir is direction
    protected  boolean moving = false;
    protected boolean walking = false;

    protected List<Projectile> projectiles = new ArrayList<Projectile>();

    public void move(int xa, int ya) {//parameters represent change on x and y axis
        if (xa != 0 && ya != 0) {//splits movement up into two if the player is moving on a diagonal
            move(xa, 0);
            move(0,ya);
            return;// exit out so that on a diagonal the player doesnt move twice
        }

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

    protected void shoot(int x, int y, double dir) {
        //dir *= 180 / Math.PI;
        Projectile p = new WizardProjectile(x, y, dir);
        projectiles.add(p);
        level.add(p);

    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        //checks all four corners to see if a tile is solid
        for (int c = 0; c < 4; c++) {
            //these 2 lines control the area that is collidable
            int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + c / 2 * 12 + 3) / 16;

            if (level.getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    public void render() {
    }
}
