// Finished video 66
package com.adb.rain;

import com.adb.rain.entity.mob.Player;
import com.adb.rain.graphics.Screen;
import com.adb.rain.input.Keyboard;
import com.adb.rain.input.Mouse;
import com.adb.rain.level.Level;
import com.adb.rain.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    private static int width = 300;
    private static int height = width / 16* 9;
    private static int scale = 3;
    public static String title = "Rain";

    private Thread thread;
    private JFrame frame;

    private Keyboard key; // Imported from keyboard.java-as its a different package
    private Level level;
    private Player player;
    private boolean running = false;

    private Screen screen;

    //Create an image
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //Converting image object into an array of integers-the pixels
    //Accesing the image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();

        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        player.init(level);

        //adds keyboard and mouse to canvas-very important
        addKeyListener(key); // add this after key = new Keyboard();

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);



    }

    public static int getWindowWidth(){
        return width * scale;
    }

    public static int getWindowHeight(){
        return height * scale;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This method is automatically run when a new thread is started with thread.start()
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                // Update will be limited to 60fps
                update();
                updates++;
                delta--;
            }



            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title +"  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }

        }
        stop();

    }


    public void update(){
        key.update();
        player.update();
        level.update();



    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        // if statement is so that the bufferstrategy is not done an unlimited amount of times per second
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        //Clears graphics off of screen before rendering again
        screen.clear();
        int xScroll = player.x-screen.width/2;
        int yScroll = player.y-screen.height/2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);





        //sets pixel array data from Screen class to pixel array in game class
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();



        // draws from pixel data
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);


        // Removes the graphics from the screen after they have been displayed
        g.dispose();
        // Diplay the buffer strategy
        bs.show();


    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        // .pack pulls information from PreferredSize
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
