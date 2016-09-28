package com.adb.rain.graphics;


import com.adb.rain.level.tile.Tile;

import java.util.Random;

public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;

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

    public void render(int xOffset, int yOffset) {
        for (int y = 0; y < height; y++) {
            int yp = y + yOffset;
            if (yp < 0 || yp >= height) continue;
            for (int x = 0; x < width; x++){
                int xp = x + xOffset;
                if (xp < 0 || xp >= width) continue;
                pixels[xp + yp * width] = Sprite.grass.pixels[(x&15) + (y&15) * Sprite.grass.SIZE];


            }
        }


    }
    // Renders individual Tiles
    public void renderTile(int xp, int yp, Tile tile) {
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            // setting absolute
            int ya = y + yp;
            for (int x = 0; y < tile.sprite.SIZE; x++) {
                // setting absolute location
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= width) break; // renders what is only visible on Screen-very important
                pixels[xa+ya*width] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; // the tiles dont get offset-their location does.

        }
    }

}}
