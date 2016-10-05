package com.adb.rain.graphics;


import com.adb.rain.level.tile.Tile;

import java.util.Random;

public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();



    public Screen(int width, int height) {
        // this.width is setting the variable above "width" to the width that is passed
        // into Screen's calling as a parameter
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        // Setting up a map of 64 X 64 tiles
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }


    }

    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }


    // Renders individual Tiles
    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            // setting absolute
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                // setting absolute location
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
                    break; // renders what is only visible on Screen-very important
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; // the tiles dont get offset-their location does.
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < 16; y++) {
            // setting absolute
            int ya = y + yp;
            for (int x = 0; x < 16; x++) {
                // setting absolute location
                int xa = x + xp;
                if (xa < -16 || xa >= width || ya < 0 || ya >= height)
                    break; // renders what is only visible on Screen-very important
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = sprite.pixels[x + y * 16]; // the tiles dont get offset-their location does.
            }
        }

    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }



}
