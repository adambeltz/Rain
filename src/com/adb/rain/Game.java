//finished game programming episode 7 from the cherno project
package com.adb.rain;

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

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    //Create an image
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    //Converting image object into an array of integers-the pixels
    //Accesing the image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        frame = new JFrame();

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
        while(running) {
            // Update will be limited to 60fps
            update();
            render();

        }

    }

    public void update(){

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        // if statement is so that the bufferstrategy is not done an unlimited amount of times per second
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        // Set the color of g - will fill Rect with this color
        g.setColor(Color.BLACK);
        // Fill the frame with the size
        g.fillRect(0, 0, getWidth(), getHeight());

        // Removes the graphics from the screen after they have been displayed
        g.dispose();
        // Diplay the buffer strategy
        bs.show();


    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        // .pack pulls information from PreferredSize
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
