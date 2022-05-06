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
            up = setup("/bullet/bulletRed1_up.png");
            down = setup("/bullet/bulletRed1_down.png");
            left = setup("/bullet/bulletRed1_left.png");
            right = setup("/bullet/bulletRed1_right.png");
        } else if (color.equals("dark")) {
            up = setup("/bullet/bulletDark1_up.png");
            down = setup("/bullet/bulletDark1_down.png");
            left = setup("/bullet/bulletDark1_left.png");
            right = setup("/bullet/bulletDark1_right.png");
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

        if(entity.id == 999) {
            target = gp.cChecker.checkEntity(this, gp.bot);
            if(target != 99){
                gp.bot[target].healthPoint--;
                if (gp.bot[target].healthPoint == 0) {
                    gp.bot[target] = null;
                    gp.effectM.addExplosion(x+solidArea.width/2, y+solidArea.height/2, true);
                    boolean empty  = true;
                    for (Bot value: gp.bot) {
                        if (value != null) {
                            empty = false;
                            break;
                        }
                    }
                    if (empty) {
                        gp.setGameState(gp.winState);
                    }
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

