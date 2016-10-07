package com.adb.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int SIZE;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_level.png", 48);

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    // load in image
    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            // we need to deal with individual pixels rather than images
            int w = image.getWidth();
            int h = image.getHeight();
            //this loads the image as pixels into the pixels array
            image.getRGB(0,0,w,h,pixels,0,w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
