package com.cs360.main;

import com.cs360.entity.Bot;
import com.cs360.object.Crate;
import com.cs360.object.Fence;
import com.cs360.object.SandBag;
import com.cs360.object.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetSetter {

    GamePanel gp;
    int botID = 0;
    int objID = 0;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;

    }

    public void setGame(String filePath) {

        int num, x, y;

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (String line; (line = br.readLine()) != null; ) {
                String[] numbers = line.split(" ");
                num = Integer.parseInt(numbers[0]);
                x = Integer.parseInt(numbers[1]);
                y = Integer.parseInt(numbers[2]);
                switch (num) {
                    case 0 -> {
                        gp.bot[botID] = new Bot(gp, botID);
                        gp.bot[botID].x = x;
                        gp.bot[botID].y = y;
                        botID++;
                    }
                    case 1 -> {
                        gp.obj[objID] = new Fence();
                        gp.obj[objID].x = x;
                        gp.obj[objID].y = y;
                        objID++;
                    }
                    case 2 -> {
                        gp.obj[objID] = new Crate();
                        gp.obj[objID].x = x;
                        gp.obj[objID].y = y;
                        objID++;
                    }
                    case 3 -> {
                        gp.obj[objID] = new SandBag();
                        gp.obj[objID].x = x;
                        gp.obj[objID].y = y;
                        objID++;
                    }
                    case 4 -> {
                        gp.obj[objID] = new Tree();
                        gp.obj[objID].x = x;
                        gp.obj[objID].y = y;
                        objID++;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void resetGame() {
        objID = 0;
        botID = 0;
    }

}
