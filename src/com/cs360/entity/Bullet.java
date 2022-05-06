package com.cs360.entity;

import com.cs360.main.GamePanel;

import java.awt.*;


public class Bullet extends Entity {

    int bulletIndex;
    Entity entity;
    int target;


    public Bullet(GamePanel gp, int bulletIndex, Entity entity) {

        super(gp);
        this.bulletIndex = bulletIndex;
        this.entity = entity;
        this.color = entity.color;

        direction = entity.direction;
        speed = 10;

        getBulletImage();
        getCoordinateOfBullet();

    }

    public void getBulletImage() {
        if (color.equals("red")){
            up = loadImage("/bullet/bulletRed1_up.png");
            down = loadImage("/bullet/bulletRed1_down.png");
            left = loadImage("/bullet/bulletRed1_left.png");
            right = loadImage("/bullet/bulletRed1_right.png");
        } else if (color.equals("dark")) {
            up = loadImage("/bullet/bulletDark1_up.png");
            down = loadImage("/bullet/bulletDark1_down.png");
            left = loadImage("/bullet/bulletDark1_left.png");
            right = loadImage("/bullet/bulletDark1_right.png");
        }
    }

    public void getCoordinateOfBullet() {

        solidArea = new Rectangle();

        switch (direction) {
            case "up" -> {
                x = entity.x + entity.solidArea.width/2 - up.getWidth()/2;
                y = entity.y;
            }
            case "down" -> {
                x = entity.x + entity.solidArea.width/2 - down.getWidth()/2;
                y = entity.y + entity.solidArea.height;
            }
            case "left" -> {
                x = entity.x;
                y = entity.y + entity.solidArea.height/2 - left.getHeight()/2;
            }
            case "right" -> {
                x = entity.x + entity.solidArea.width;
                y = entity.y + entity.solidArea.height/2 - left.getHeight()/2;
            }
        }

    }


    public void update() {

        collisionOn = false;

        if(entity.id == 999) { // Đạn do người chơi bắn (player.id = 99)
            target = gp.cChecker.checkEntity(this, gp.bot); // check giao nhau giữa đạn và bot
            if(target != 99){ // checkEntity return 99 nghĩa là viên đạn không giao với mục tiêu
                gp.bot[target].healthPoint--;
                if (gp.bot[target].healthPoint == 0) {
                    gp.bot[target] = null;
                    gp.effectM.addExplosion(x+solidArea.width/2, y+solidArea.height/2, true);
                    gp.checkWinCondition(); // Kiểm tra xem có còn bot nào sống không. Nếu không change gameState to winState
                } else {
                    gp.effectM.addExplosion(x+solidArea.width/2, y+solidArea.height/2, false);
                }
            }
        } else {
            if (gp.cChecker.checkPlayer(this)){
                gp.player.healthPoint--;
                if (gp.player.healthPoint == 0) {
                    gp.effectM.addExplosion(x+solidArea.width/2, y+solidArea.height/2, true);
                    gp.setGameState(gp.loseState);
                } else {
                    gp.effectM.addExplosion(x+solidArea.width/2, y+solidArea.height/2, false);
                }
            }
        }
        gp.cChecker.checkMapBorder(this);
        gp.cChecker.checkBulletProof(this);
        if (collisionOn) {
            gp.bullet[bulletIndex] = null;
        }
        else {
            move();
        }

    }


}

