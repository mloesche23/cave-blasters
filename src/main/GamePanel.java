package main;

import entity.Player;

import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {
    int FPS = 60;
    final int originalTileSize = 16;
    final int scale = 4;

    public int gemCounter = 0;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;




    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int Fps = 0;





        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime- lastTime)/ drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >=1) {
                update();
                repaint();
                delta--;
                Fps++;
            }
            if (timer>= 1000000000){
                System.out.println("FPS:"+ Fps);
                Fps = 0;
                timer = 0;
            }

        }
    }
    public void update(){

        player.update();
        tileManager.updatePlayerSpot(player);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;




        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}

