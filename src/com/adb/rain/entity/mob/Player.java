package com.adb.rain.entity.mob;

import com.adb.rain.graphics.Screen;
import com.adb.rain.graphics.Sprite;
import com.adb.rain.input.Keyboard;

public class Player extends Mob {
    private Keyboard input;

    public Player(Keyboard input){
        this.input = input;

    }

    public Player (int x, int y, Keyboard input) { //To create a player at specific location(x,y)
        this.x = x; //This refers to x and y in Entity class
        this.y = y;
        this.input = input;

    }

    public void update(){
        int xa = 0, ya = 0;
        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) move(xa, ya);

    }

    public void render(Screen screen) {
        screen.renderPlayer(x, y, Sprite.player0);

    }
}
