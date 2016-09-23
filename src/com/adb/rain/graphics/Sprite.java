package com.adb.rain.graphics;

public class Sprite {
    private final int SIZE;

    private int x, y;  //Coordinates of the sprite
    public int[] pixels;

    private SpriteSheet sheet; //to set which sheet the sprite is coming from

    public Sprite(int size, int x, int y, SpriteSheet sheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE]; // An array the size of the sprite
        // Set location of target sprite in spritesheet
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();

    }

    // Access spritesheets pixels and find right sprite then extracting pixels from appropriate sprite
    private void load() {
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y)*sheet.SIZE];
            }
        }

    }
}