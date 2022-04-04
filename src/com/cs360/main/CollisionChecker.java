package com.cs360.main;

import com.cs360.entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    int checkDistance = 6;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkMapBorder(Entity entity) {

        entity.solidArea.x = entity.x;
        entity.solidArea.y = entity.y;

        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= checkDistance;
                if (entity.solidArea.y < 0) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entity.solidArea.y += checkDistance;
                if ((entity.solidArea.y + entity.solidArea.height) > gp.screenHigh) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entity.solidArea.x -= checkDistance;
                if (entity.solidArea.x < 0) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entity.solidArea.x += checkDistance;
                if ((entity.solidArea.x + entity.solidArea.width) > gp.screenWidth) {
                    entity.collisionOn = true;
                }
            }
        }
    }


    public void checkObj(Entity entity) {

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null && gp.obj[i].collision) {

                entity.solidArea.x = entity.x;
                entity.solidArea.y = entity.y;

                gp.obj[i].solidArea.x = gp.obj[i].x;
                gp.obj[i].solidArea.y = gp.obj[i].y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= checkDistance;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += checkDistance;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= checkDistance;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += checkDistance;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            entity.collisionOn = true;
                        }
                    }
                }
            }
        }
    }

    public void checkBulletProof(Entity entity) {

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null && gp.obj[i].collision) {

                entity.solidArea.x = entity.x;
                entity.solidArea.y = entity.y;

                gp.obj[i].solidArea.x = gp.obj[i].x;
                gp.obj[i].solidArea.y = gp.obj[i].y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= checkDistance;
                        checkBulletIntersects(entity, i);
                    }
                    case "down" -> {
                        entity.solidArea.y += checkDistance;
                        checkBulletIntersects(entity, i);
                    }
                    case "left" -> {
                        entity.solidArea.x -= checkDistance;
                        checkBulletIntersects(entity, i);
                    }
                    case "right" -> {
                        entity.solidArea.x += checkDistance;
                        checkBulletIntersects(entity, i);
                    }
                }
            }
        }
    }

    public void checkBulletIntersects(Entity entity, int i) {

        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
            if (gp.obj[i].bulletProof > 0) {
                entity.collisionOn = true;
            }
            if (gp.obj[i].bulletProof == 2) {
                gp.obj[i].endurance--;
                if(gp.obj[i].endurance == 0){
                    gp.effectM.addExplosion(gp.obj[i].x, gp.obj[i].y, true);
                    gp.obj[i] = null;
                }
            }
        }

    }

    public int checkEntity(Entity entity, Entity[] target) {

        int index = 99;

        for (int i = 0; i < target.length; i++) {

            if((target[i] != null) && (i != entity.id)) {

                entity.solidArea.x = entity.x;
                entity.solidArea.y = entity.y;

                target[i].solidArea.x = target[i].x;
                target[i].solidArea.y = target[i].y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= checkDistance;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += checkDistance;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= checkDistance;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += checkDistance;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                }
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean state = false;

        entity.solidArea.x = entity.x;
        entity.solidArea.y = entity.y;

        gp.player.solidArea.x = gp.player.x;
        gp.player.solidArea.y = gp.player.y;

        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= checkDistance;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    state = true;
                }
            }
            case "down" -> {
                entity.solidArea.y += checkDistance;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    state = true;
                }
            }
            case "left" -> {
                entity.solidArea.x -= checkDistance;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    state = true;
                }
            }
            case "right" -> {
                entity.solidArea.x += checkDistance;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    state = true;
                }
            }
        }

        return state;
    }

}
