package com.cs360.effect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Effect {

    public BufferedImage[] images;
    public int x, y;
    public int effectCounter = 0;
    public int effectIndex = 0;


    public BufferedImage setup(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public BufferedImage getIndexImage() {
        return images[effectIndex];
    }

}