package com.adb.rain.graphics;


import java.util.Random;

public class Screen {

    private int width, height;
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
            int yy = y + yOffset;
            // to keep from going off the screen and breaking the code
            //if (yy < 0 || yy >= height) break;

            for (int x = 0; x < width; x++){
                int xx = x + xOffset;
                //if (xx < 0 || xx >= width) break;

                int tileIndex = ((xx >> 4) & MAP_SIZE_MASK)+ ((yy >> 4 & MAP_SIZE_MASK))* MAP_SIZE;

                pixels[x + y * width] = tiles[tileIndex];


            }
        }


    }

}
