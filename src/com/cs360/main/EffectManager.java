package com.cs360.main;

import com.cs360.effect.Effect;
import com.cs360.effect.Explosion;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EffectManager {
    
    GamePanel gp;
    Effect[] effects;
    int index = 0;

    public EffectManager(GamePanel gp) {

        this.gp = gp;
        effects = new Effect[64];

    }

    public void addExplosion(int x, int y, boolean smoke){
        if(smoke){
            effects[index] = new Explosion(true);
        } else {
            effects[index] = new Explosion(false);
        }
        effects[index].x = x;
        effects[index].y = y;
        index++;

    }


    public void draw(Graphics2D g2) {

        for (int i=0; i < effects.length; i++) {

            if (effects[i] != null) {
                BufferedImage image = effects[i].getIndexImage();
                g2.drawImage(image, effects[i].x, effects[i].y, image.getWidth(), image.getHeight(), null);

                effects[i].effectCounter++;

                if (effects[i].effectIndex == effects[i].images.length-1) {
                    effects[i] = null;
                } else if (effects[i].effectCounter > gp.FPS/6) {
                    effects[i].effectCounter = 0;
                    effects[i].effectIndex++;
                }
            }

        }

    }

}
