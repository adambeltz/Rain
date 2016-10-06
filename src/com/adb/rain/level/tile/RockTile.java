package com.adb.rain.level.tile;

import com.adb.rain.graphics.Screen;
import com.adb.rain.graphics.Sprite;

public class RockTile extends Tile {
    public RockTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid(){ // only add this as an override to classes that need to be set to true
        return true;

    }

}
