package com.cs360.entity;

import com.cs360.main.GamePanel;

import java.awt.*;


public class Bullet extends Entity {

    int bulletIndex;
    Entity shooter;

    public Bullet(GamePanel gp, int bulletIndex, Entity shooter) {

        super(gp);
        this.bulletIndex = bulletIndex;
        this.shooter = shooter;
        this.color = shooter.color;

        direction = shooter.direction;
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
                x = shooter.x + shooter.solidArea.width/2 - up.getWidth()/2;
                y = shooter.y;
            }
            case "down" -> {
                x = shooter.x + shooter.solidArea.width/2 - down.getWidth()/2;
                y = shooter.y + shooter.solidArea.height;
            }
            case "left" -> {
                x = shooter.x;
                y = shooter.y + shooter.solidArea.height/2 - left.getHeight()/2;
            }
            case "right" -> {
                x = shooter.x + shooter.solidArea.width;
                y = shooter.y + shooter.solidArea.height/2 - left.getHeight()/2;
            }
        }

    }


    public void update() {

        int target;
        collisionOn = false;

        if(shooter.id == 999) { // Đạn do người chơi bắn (player.id = 99)
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

