package com.adb.rain.level.tile;

import com.adb.rain.graphics.Screen;
import com.adb.rain.graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){ // only add this as an override to classes that need to be set to true
        return false;

    }
}
