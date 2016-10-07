package com.adb.rain.level.tile.spawn_level;

import com.adb.rain.graphics.Screen;
import com.adb.rain.graphics.Sprite;
import com.adb.rain.level.tile.Tile;

public class SpawnHedgeTile extends Tile {
    public SpawnHedgeTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }

    public boolean breakable() {
        return true;
    }


}
