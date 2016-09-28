package com.adb.rain.level.tile;

import com.adb.rain.graphics.Screen;
import com.adb.rain.graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){ // only add this as an override to classes that need to be set to true
        return false;

    }
}
