package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int playerX, playerY;
    public int gemX, gemY;
    public int speed;

    public BufferedImage down1, down2, up1, up2, left1, left2, right1, right2, down1Blast, down2Blast,
            up1Blast, up2Blast, left1Blast, left2Blast, right1Blast, right2Blast, fireBlast, blastImage;

    public String direction;



    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
