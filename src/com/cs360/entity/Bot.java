package com.cs360.entity;

import com.cs360.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Bot extends Entity {

    int actionCounter = 0;
    int actionNum = 1;

    public Bot(GamePanel gp, int id) {

        super(gp);
        this.id = id;

        solidArea = new Rectangle();
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;

        getBotImage();
        setDefaultValues();

    }

    public void setDefaultValues() {

        speed = 3;
        direction = "down";
        color = "dark";
        healthPoint = 2;

    }

    public void getBotImage() {

        up = setup("/tank/tank_dark_up.png");
        down = setup("/tank/tank_dark_down.png");
        left = setup("/tank/tank_dark_left.png");
        right = setup("/tank/tank_dark_right.png");

    }


    public void update() {

        actionCounter++;

        if (actionCounter == actionNum) {

            Random random = new Random();
            actionNum = random.nextInt(100)+25;

            int d = random.nextInt(4);

            if (d == 0) {
                direction = "up";
            }
            if (d == 1) {
                direction = "down";
            }
            if (d == 2) {
                direction = "left";
            }
            if (d == 3) {
                direction = "right";
            }

            int fire = random.nextInt(100);

            if(fire < gp.botFireRate) attack();

            actionCounter = 0;
        }

        collisionOn = false;
        gp.cChecker.checkEntity(this, gp.bot);
        gp.cChecker.checkMapBorder(this);
        gp.cChecker.checkObj(this);
        gp.cChecker.checkPlayer(this);

        if (!collisionOn){
            move();
        }
        else {
            actionCounter = actionNum - 1;
        }
    }

}
