package com.cs360.main;
import com.cs360.tile.Tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;
    int tile2Edit = 0;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[40];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
    }

    public void getTileImage() {
        File path = new File("res/tile");
        File[] allFiles = path.listFiles();

        for (int i = 0; i < tile.length; i++) {
            try {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(Objects.requireNonNull(allFiles)[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMap(String filePath) {

        if (gp.systemIsMacOS) {
            filePath = filePath.substring(0, filePath.length()-4) + "mac.txt";
        }

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();
                String[] numbers = line.split(" ");

                while(col < gp.maxScreenCol) {

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol  && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

    public void draw2Edit(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int tileSize = 50;
        int x = tileSize;
        int y = 2*tileSize;

        g2.setColor(Color.white);

        for (int i = 1; i <= 40; i++) {
            g2.drawImage(tile[i-1].image, x, y, tileSize, tileSize, null);
            g2.drawRect(x, y, tileSize, tileSize);
            y += tileSize;
            if (i % 10 == 0) {
                y = 2*tileSize;
                x += tileSize;
            }
        }

        x = tileSize ;
        y = 14 * tileSize;

        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("Selected: ", x, y);

        x = 4 * tileSize ;
        y = 13 * tileSize + 2*tileSize/5;
        g2.drawImage(tile[tile2Edit].image, x, y, tileSize, tileSize, null);

        x = 6 * tileSize;
        y = 2 * tileSize;

        while (col < gp.maxScreenCol  && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, tileSize, tileSize, null);
            g2.drawRect(x, y, tileSize, tileSize);
            col++;
            x += tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 6 * tileSize;
                row++;
                y += tileSize;
            }
        }

    }

    public void getSelectedTile(int col, int row) {
        int tileIndex = ((col-1) * 10 + (row-2));
        if(tileIndex >= 0 && tileIndex <= 39)
            tile2Edit = tileIndex;
    }

    public void editMap(int col, int row) {
        col = col - 6;
        row = row - 2;
        if(col >= 0 && col < gp.maxScreenCol && row >= 0 && row < gp.maxScreenRow)
            mapTileNum[col][row] = tile2Edit;
    }

    public void saveMap() {

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter("res"+gp.mapPath));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {


                while(col < gp.maxScreenCol) {
                    wr.write(String.valueOf(mapTileNum[col][row]));
                    wr.write(" ");
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    wr.write("\n");
                    col = 0;
                    row++;
                }
            }
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
