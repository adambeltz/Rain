package com.adb.rain.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {




    public SpawnLevel(String path) {
        super(path);
    }


    protected void loadLevel(String path) { // load a level from a file
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w*h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file");
        }

    }

    // Grass = 0xff00ff00
    // Flower = 0xffffff00
    // Rock = 0xff7f7f00

    protected void generateLevel() { // convert array of integers into an array of tiles
        System.out.println("tiles: " + tiles[0]);

    }

}
