package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    boolean hasBlaster = false;

    int speed = 4;

    int blastX = playerX;
    int blastY = playerY;

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public void setHasBlaster(boolean hasBlaster) {
        this.hasBlaster = hasBlaster;
    }

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();



    }
    public void setDefaultValues() {
        playerX = 50;
        playerY = 35;


        direction = "down";
    }
    public void getPlayerImage(){
        try{
            File f1 = new File("./src/player/down1.png");
            File f2 = new File("./src/player/down2.png");
            File f3 = new File("./src/player/up1.png");
            File f4 = new File("./src/player/up2.png");
            File f5 = new File("./src/player/right1.png");
            File f6 = new File("./src/player/right2.png");
            File f7 = new File("./src/player/left1.png");
            File f8 = new File("./src/player/left2.png");
            File f9 = new File("./src/player/down1Blast.png");
            File f10 = new File("./src/player/down2Blast.png");
            File f11 = new File("./src/player/up1Blast.png");
            File f12 = new File("./src/player/up2Blast.png");
            File f13 = new File("./src/player/right1Blast.png");
            File f14 = new File("./src/player/right2Blast.png");
            File f15 = new File("./src/player/left1Blast.png");
            File f16 = new File("./src/player/left2Blast.png");
            File f17 = new File("./src/tiles/fireBlast.png");
            File f18 = new File("./src/tiles/blastImage.png");
            down1 = ImageIO.read(f1);
            down2 = ImageIO.read(f2);
            up1 = ImageIO.read(f3);
            up2 = ImageIO.read(f4);
            right1 = ImageIO.read(f5);
            right2 = ImageIO.read(f6);
            left1 = ImageIO.read(f7);
            left2 = ImageIO.read(f8);
            down1Blast = ImageIO.read(f9);
            down2Blast = ImageIO.read(f10);
            up1Blast = ImageIO.read(f11);
            up2Blast = ImageIO.read(f12);
            right1Blast = ImageIO.read(f13);
            right2Blast = ImageIO.read(f14);
            left1Blast = ImageIO.read(f15);
            left2Blast = ImageIO.read(f16);
            fireBlast = ImageIO.read(f17);
            blastImage = ImageIO.read(f18);



        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void update() {


        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
            if(keyH.upPressed){
                if (playerY < 0) {
                    playerY = 0;
                } else {
                    direction = "up";
                    playerY -= speed;
                }

            }
            else if(keyH.downPressed){
                if (playerY > gp.screenHeight - gp.tileSize) {
                    playerY = gp.screenHeight - gp.tileSize;
                } else {
                    direction = "down";
                    playerY += speed;
                }
            }
            else if(keyH.leftPressed){
                if (playerX < 0) {
                    playerX = 0;
                } else {
                    direction = "left";
                    playerX -= speed;
                }
            }
            else if(keyH.rightPressed){
                if (playerX > gp.screenWidth - gp.tileSize) {
                    playerX = gp.screenWidth - gp.tileSize;
                } else {
                    direction = "right";
                    playerX += speed;
                }
            }
            else if(keyH.spacePressed){
                System.out.println("shoot");;
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNumber == 1){
                    spriteNumber = 2;

                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;

                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        BufferedImage blastImage = null;


        if (!hasBlaster) {
            switch (direction) {
                case "up":
                    if (spriteNumber == 1) {
                        image = up1;
                    } else if (spriteNumber == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNumber == 1) {
                        image = down1;
                    } else if (spriteNumber == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNumber == 1) {
                        image = left1;
                    } else if (spriteNumber == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNumber == 1) {
                        image = right1;
                    } else if (spriteNumber == 2) {
                        image = right2;
                    }
                    break;
            }
        }
        if (hasBlaster) {
            switch (direction) {
                case "up":
                    if (spriteNumber == 1) {
                        image = up1Blast;
                    } else if (spriteNumber == 2) {
                        image = up2Blast;
                    }
                    break;
                case "down":
                    if (spriteNumber == 1) {
                        image = down1Blast;
                    } else if (spriteNumber == 2) {
                        image = down2Blast;
                    }
                    break;
                case "left":
                    if (spriteNumber == 1) {
                        image = left1Blast;
                    } else if (spriteNumber == 2) {
                        image = left2Blast;
                    }
                    break;
                case "right":
                    if (spriteNumber == 1) {
                        image = right1Blast;
                    } else if (spriteNumber == 2) {
                        image = right2Blast;
                    }
                    break;
                case "shoot":
                    if (keyH.spacePressed) {
                        blastImage = fireBlast;


                    }
            }
        }
        g2.drawImage(image, playerX, playerY, gp.tileSize,gp.tileSize,null);
        g2.drawImage(blastImage, blastX, blastY, null);
    }


}
