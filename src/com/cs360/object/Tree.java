package com.cs360.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tree extends GameObj {

    public Tree() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/treeGreen_large.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
