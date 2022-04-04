package com.cs360.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP)
            code = KeyEvent.VK_W;
        if(code == KeyEvent.VK_LEFT)
            code = KeyEvent.VK_A;
        if(code == KeyEvent.VK_DOWN)
            code = KeyEvent.VK_S;
        if(code == KeyEvent.VK_RIGHT)
            code = KeyEvent.VK_D;

        if (gp.gameState == gp.menuState)
            menuState(code);

        else if (gp.gameState == gp.playState)
            playState(code);

        else if (gp.gameState == gp.pauseState)
            pauseState(code);

        else if (gp.gameState == gp.winState || gp.gameState == gp.loseState)
            finishState(code);

        else if (gp.gameState == gp.editState)
            editState(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP)
            code = KeyEvent.VK_W;
        if(code == KeyEvent.VK_LEFT)
            code = KeyEvent.VK_A;
        if(code == KeyEvent.VK_DOWN)
            code = KeyEvent.VK_S;
        if(code == KeyEvent.VK_RIGHT)
            code = KeyEvent.VK_D;

        if(code == KeyEvent.VK_W)
            upPressed = false;
        if(code == KeyEvent.VK_A)
            leftPressed = false;
        if(code == KeyEvent.VK_S)
            downPressed = false;
        if(code == KeyEvent.VK_D)
            rightPressed = false;

    }

    public void menuState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.cmdNum--;
            if (gp.ui.cmdNum < 0) {
                gp.ui.cmdNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.cmdNum++;
            if (gp.ui.cmdNum > 2) {
                gp.ui.cmdNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.cmdNum == 0) {
                gp.gameState = gp.playState;
                gp.stopMusic();
                gp.playMusic(1);
                gp.playSoundEffect(2);
                gp.playDelayEffect();
            }
            if (gp.ui.cmdNum == 1) {

            }
            if (gp.ui.cmdNum == 2) {
                gp.gameState = gp.editState;
            }
            if (gp.ui.cmdNum == 3) {
                System.exit(0);
            }
        }

    }

    public void playState(int code) {

        if(code == KeyEvent.VK_F)
            gp.player.attack();
        if(code == KeyEvent.VK_W)
            upPressed = true;
        if(code == KeyEvent.VK_A)
            leftPressed = true;
        if(code == KeyEvent.VK_S)
            downPressed = true;
        if(code == KeyEvent.VK_D)
            rightPressed = true;
        if(code == KeyEvent.VK_P)
            gp.gameState = gp.pauseState;
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.menuState;
            gp.stopMusic();
            gp.playMusic(0);
        }

        if(code == KeyEvent.VK_T) {
            if (showDebugText == true) {
                showDebugText = false;
            }
            else if (showDebugText == false) {
                showDebugText = true;
            }
        }

        if (code == KeyEvent.VK_R) {
            gp.tileM.loadMap(gp.mapPath);
        }
    }

    public void pauseState(int code) {
        if(code == KeyEvent.VK_P)
            gp.gameState = gp.playState;
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.menuState;
            gp.stopMusic();
            gp.playMusic(0);
        }
    }

    public void finishState(int code) {
        if(code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void editState(int code) {
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.menuState;
        }
    }
}
