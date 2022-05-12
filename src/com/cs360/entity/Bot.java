package com.cs360.entity;

import com.cs360.main.GamePanel;

import java.awt.Rectangle;
import java.util.Random;

public class Bot extends Entity {

    int actionCounter = 0;
    int actionNum = 1;
    int fireRate;

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

        direction = "down";
        color = "dark";
        if (gp.getHardMode() == 0){
            healthPoint = 2;
            speed = 2;
            fireRate = 40;
        } else if (gp.getHardMode() == 1) {
            healthPoint = 3;
            speed = 3;
            fireRate = 60;
        } else if (gp.getHardMode() == 2) {
            healthPoint = 4;
            speed = 4;
            fireRate = 80;
        }
    }

    public void getBotImage() {

        up = loadImage("/tank/tank_dark_up.png");
        down = loadImage("/tank/tank_dark_down.png");
        left = loadImage("/tank/tank_dark_left.png");
        right = loadImage("/tank/tank_dark_right.png");

    }

    public void attack() {
        for (int i = 0; i < gp.bullet.length; i++) {
            if (gp.bullet[i] == null) {
                gp.bullet[i] = new Bullet(gp, i, this);
                break;
            }
        }
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

            if(fire < fireRate) attack();

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
