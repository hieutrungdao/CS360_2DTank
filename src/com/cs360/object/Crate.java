package com.cs360.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Crate extends GameObj{
    public Crate() {

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/crateWood.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
        bulletProof = 2;
        solidArea.height = image.getHeight();
        solidArea.width = image.getWidth();

    }
}
