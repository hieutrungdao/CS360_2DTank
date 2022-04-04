package com.cs360.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SandBag extends GameObj{

    public SandBag() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/sandbagBrown.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
        bulletProof = 1;
        solidArea.height = image.getHeight();
        solidArea.width = image.getWidth();
    }
}
