package com.cs360.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Fence extends GameObj{

    public Fence(){

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/fenceRed.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
        solidArea.height = image.getHeight();
        solidArea.width = image.getWidth();
    }


}
