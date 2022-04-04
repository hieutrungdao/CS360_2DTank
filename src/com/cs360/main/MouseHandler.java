package com.cs360.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    GamePanel gp;
    int tileSize = 50;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void mouseClicked(MouseEvent e) {
        if (gp.gameState == gp.editState) {
            int col = e.getX()/tileSize;
            int row = e.getY()/tileSize;
            if (col < 5)
                gp.tileM.getSelectedTile(col, row);
            else if (col > 5)
                gp.tileM.editMap(col, row);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
