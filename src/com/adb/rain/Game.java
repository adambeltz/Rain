//finished game programming episode 20 from the cherno project
package com.adb.rain;

import com.adb.rain.graphics.Screen;
import com.adb.rain.input.Keyboard;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = width / 16* 9;
    public static int scale = 3;
    public static String title = "Rain";

    private Thread thread;
    private JFrame frame;

    private Keyboard key; // Imported from keyboard.java-as its a different package
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
        addKeyListener(key); // add this after key = new Keyboard();


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

    int x = 0, y = 0;

    public void update(){
        key.update();
        if (key.up) y--;
        if (key.down) y++;
        if (key.left) x--;
        if (key.right) x++;


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

        screen.render(x, y);

        //sets pixel array data from Screen class to pixel array in game class
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        // Set the color of g - will fill Rect with this color
        g.setColor(Color.BLACK);
        // Fill the frame with the size
        g.fillRect(0, 0, getWidth(), getHeight());


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
