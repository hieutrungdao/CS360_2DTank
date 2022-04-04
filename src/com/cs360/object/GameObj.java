package com.cs360.object;

import com.cs360.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObj {

    public BufferedImage image;
    public boolean collision = false;
    public int bulletProof = 0;
    public int endurance = 5;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0,0,64,64);

    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }

}
