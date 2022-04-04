package com.cs360.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends GameObj {
    public Heart() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/barrelBlack_side.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
