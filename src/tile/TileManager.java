package tile;

import entity.Player;
import entity.Gem;
import main.GamePanel;
import java.lang.System;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Timer;

import static java.lang.System.currentTimeMillis;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNumber;

    Timer timer = new Timer();

    public TileManager() {}

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[11];
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/tiles/dirt.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/tiles/ocean.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/tiles/grass.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/tiles/sand.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/tiles/rubyNew.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("src/tiles/sandywater.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/tiles/door.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("src/tiles/dirt.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("src/tiles/grassGun.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new File("src/tiles/Tunnel.png"));





        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private int mapNum = 1;
    private String strMapNum = String.valueOf(mapNum);
    private String mapName = "/maps/map" + strMapNum + ".txt";

    public int getMapNum() {
        return mapNum;
    }

    public Timer getTimer() {
        return timer;
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            long startTime = currentTimeMillis();

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);


                    mapTileNumber[col][row] = num;
                    col++;


                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateGemSpot(Gem gem) {
        int GemX = gem.gemX;
        int GemY = gem.gemY;

    }

    public void updatePlayerSpot(Player player){
        int playerX = (player.playerX + 32) / gp.tileSize;
        int playerY = (player.playerY + 32) / gp.tileSize;

        if (playerX >= 0 && playerX < mapTileNumber.length &&
                playerY >= 0 && playerY < mapTileNumber[0].length) {
            int tilenum = mapTileNumber[playerX][playerY];
            System.out.println(tilenum);

            if(tilenum == 0){
                player.setSpeed(2);
            }
            if(tilenum == 2){
                player.setSpeed(4);
            }

            if(tilenum == 4){
                System.out.println("Got it");
                gp.gemCounter++;
                mapNum = mapNum + 1;
                String strMapNum = String.valueOf(mapNum);
                changeMap("/maps/map" + strMapNum + ".txt");
            }
            if(tilenum == 1) {
                System.out.println("water");
                mapNum = 1;
                player.setHasBlaster(false);
                String strMapNum = String.valueOf(mapNum);
                changeMap("/maps/map" + strMapNum + ".txt");
            }
            if(tilenum == 7) {
                System.exit(0);
            }
            if(tilenum == 8) {
                changeMap("/maps/map11.txt");
            }
            if(tilenum == 9) {
                player.setHasBlaster(true);
            }
            if (tilenum == 10) {
                Random rand = new Random();
                mapNum = rand.nextInt(1, 9);
                String strMapNum = String.valueOf(mapNum);
                changeMap("/maps/map" + strMapNum + ".txt");
            }
        }


    }
    private void changeMap(String newMapFileName) {
        mapName = newMapFileName;
        loadMap();
    }
    public void draw(Graphics2D g2){
       int col = 0;
       int row = 0;
       int x = 0;
       int y = 0;


       while(col < gp.maxScreenCol && row < gp.maxScreenRow){

           int tileNum = mapTileNumber[col][row];
           g2.drawImage(tile[tileNum].image, x, y, gp.tileSize,gp.tileSize, null);
           col++;
           x += gp.tileSize;

           if(col == gp.maxScreenCol){
               col = 0;
               x = 0;
               row++;
               y += gp.tileSize;

           }
       }
    }
}
