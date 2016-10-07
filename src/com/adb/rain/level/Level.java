package com.adb.rain.level;

import com.adb.rain.graphics.Screen;
import com.adb.rain.level.tile.Tile;

public class Level {


    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new Level("/levels/spawn.png");

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level (String path){
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path){

    }

    public void update() {

    }

    private void time(){

    }

    public void render (int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        // added 4 corner pins to define the area we want to render
        int x0 = xScroll >> 4;  // going from pixel to tile precision to render the tiles that will be on the screen
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        // this code cycles through every number that is on teh screen---between the 4 corner pins
        for(int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
               getTile(x,y).render(x,y,screen);
            }
        }
    }

    public Tile getTile(int x, int y){
        if ( x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x+y*width] == 0xff00ff00) return Tile.grass;
        if (tiles[x+y*width] == 0xffffff00) return Tile.flower;
        if (tiles[x+y*width] == 0xff7f7f00) return Tile.rock;
        return Tile.voidTile;

    }


}
