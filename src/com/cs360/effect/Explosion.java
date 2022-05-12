package com.cs360.effect;

import java.awt.image.BufferedImage;

public class Explosion extends Effect{

    public Explosion(int x, int y, boolean smoke) {

        this.x = x;
        this.y = y;

        images = new BufferedImage[5];

        if (smoke) {
            images[0] = setup("/explosion/explosionSmoke1.png");
            images[1] = setup("/explosion/explosionSmoke2.png");
            images[2] = setup("/explosion/explosionSmoke3.png");
            images[3] = setup("/explosion/explosionSmoke4.png");
            images[4] = setup("/explosion/explosionSmoke5.png");
        } else {
            images[0] = setup("/explosion/explosion1.png");
            images[1] = setup("/explosion/explosion2.png");
            images[2] = setup("/explosion/explosion3.png");
            images[3] = setup("/explosion/explosion4.png");
            images[4] = setup("/explosion/explosion5.png");
        }

    }

}
