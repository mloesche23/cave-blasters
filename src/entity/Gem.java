package entity;

import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Gem extends Entity{

    // 1024 x 768

    public int gemX;
    public int gemY;

    TileManager tm = new TileManager();

    public void getRubyImage() throws IOException {
        try{
            File f1 = new File("./src/player/ruby.png");

            BufferedImage ruby = ImageIO.read(f1);


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        //BufferedImage image = ruby;
        BufferedImage blastImage = null;

//        if (ruby.exists()) {
//            if (tm.getMapNum() == 1) {
//                gemX = 1000;
//                gemY = 350;
//            }
//            g2.drawImage();
//        }


    }


}
