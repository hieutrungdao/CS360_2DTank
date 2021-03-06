package com.cs360.entity;

import com.cs360.main.GamePanel;
import com.cs360.main.KeyHandler;

import java.awt.Rectangle;


public class Player extends Entity {

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = x;
        solidArea.y = y;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        id = 999;
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
        color = "red";
        healthPoint = 3;

    }

    public void setHardMode(int hardMode) {
        this.healthPoint = 3 - hardMode;
    }

    public void getPlayerImage() {

        up = loadImage("/tank/tank_red_up.png");
        down = loadImage("/tank/tank_red_down.png");
        left = loadImage("/tank/tank_red_left.png");
        right = loadImage("/tank/tank_red_right.png");

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

        if (keyH.upPressed) {
            direction = "up";
        } else if (keyH.downPressed) {
            direction = "down";
        } else if (keyH.leftPressed) {
            direction = "left";
        } else if (keyH.rightPressed) {
            direction = "right";
        }

        collisionOn = false;
        gp.cChecker.checkMapBorder(this);
        gp.cChecker.checkObj(this);
        gp.cChecker.checkEntity(this, gp.bot);

        if (!collisionOn) {
            move();
        }

    }

}
