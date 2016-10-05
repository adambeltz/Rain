package com.adb.rain.entity;

import com.adb.rain.graphics.Screen;
import com.adb.rain.level.Level;

import java.util.Random;

public abstract class Entity {
    public int x, y; // keeps track of where entity is located on the map
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() {

    }

    public void render(Screen screen){

    }

    public void remove(){
        // Remove from level
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }


}
