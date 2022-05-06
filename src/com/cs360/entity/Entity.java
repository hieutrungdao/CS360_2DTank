package com.cs360.entity;

import com.cs360.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int id = 99;
    public int x, y;
    public int speed;
    public String color;

    public String direction;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public BufferedImage up, down, left, right;
    public int healthPoint;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void move() {

        switch (direction) {
            case "up" -> {
                solidArea.width = up.getWidth();
                solidArea.height = up.getHeight();
                y -= speed;
            }
            case "down" -> {
                solidArea.width = down.getWidth();
                solidArea.height = down.getHeight();
                y += speed;
            }
            case "left" -> {
                solidArea.width = left.getWidth();
                solidArea.height = left.getHeight();
                x -= speed;
            }
            case "right" -> {
                solidArea.width = right.getWidth();
                solidArea.height = right.getHeight();
                x += speed;
            }
        }

    }

    public void attack() {
        for (int i = 0; i <= gp.bulletIndex; i++) {
            if (i == gp.bulletIndex) {
                gp.bulletIndex++;
            }
            if (gp.bullet[i] == null) {
                gp.bullet[i] = new Bullet(gp, i, this);
                break;
            }
        }

    }

    public BufferedImage loadImage(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = switch (direction) {
            case "up" -> up;
            case "down" -> down;
            case "left" -> left;
            case "right" -> right;
            default -> null;
        };

        g2.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);

    }
}
