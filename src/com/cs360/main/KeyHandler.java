package com.cs360.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP)
            code = KeyEvent.VK_W;
        if (code == KeyEvent.VK_LEFT)
            code = KeyEvent.VK_A;
        if (code == KeyEvent.VK_DOWN)
            code = KeyEvent.VK_S;
        if (code == KeyEvent.VK_RIGHT)
            code = KeyEvent.VK_D;

        if (gp.getGameState() == gp.menuState)
            menuState(code);
        else if (gp.getGameState() == gp.playState)
            playState(code);

        else if (gp.getGameState() == gp.pauseState)
            pauseState(code);

        else if (gp.getGameState() == gp.winState || gp.getGameState() == gp.loseState)
            finishState(code);

        else if (gp.getGameState() == gp.editState)
            editState(code);

        else if (gp.getGameState() == gp.selectMapState)
            selectMapState(code);
        else if (gp.getGameState() == gp.selectHardState)
            selectHardState(code);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP)
            code = KeyEvent.VK_W;
        if (code == KeyEvent.VK_LEFT)
            code = KeyEvent.VK_A;
        if (code == KeyEvent.VK_DOWN)
            code = KeyEvent.VK_S;
        if (code == KeyEvent.VK_RIGHT)
            code = KeyEvent.VK_D;

        if (code == KeyEvent.VK_W)
            upPressed = false;
        if (code == KeyEvent.VK_A)
            leftPressed = false;
        if (code == KeyEvent.VK_S)
            downPressed = false;
        if (code == KeyEvent.VK_D)
            rightPressed = false;

    }

    public void menuState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.cmdNum--;
            if (gp.ui.cmdNum < 0) {
                gp.ui.cmdNum = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.cmdNum++;
            if (gp.ui.cmdNum > 3) {
                gp.ui.cmdNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.cmdNum == 0) {
                gp.setGameState(gp.selectHardState);
            }
            if (gp.ui.cmdNum == 1) {
                gp.setGameState(gp.selectMapState);
                gp.ui.cmdNum = 0;
            }
            if (gp.ui.cmdNum == 2) {
                gp.setGameState(gp.editState);
            }
            if (gp.ui.cmdNum == 3) {
                System.exit(0);
            }
        }

    }

    public void startGame(int hardMode) {
        gp.setGameState(gp.playState);
        gp.setHardMode(hardMode);
        gp.aSetter.setGame(gp.objPath);
        gp.stopMusic();
        gp.playMusic(1);
        gp.playSoundEffect(2);
        gp.playDelaySoundEffect();
    }

    public void selectHardState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.cmdNum--;
            if (gp.ui.cmdNum < 0) {
                gp.ui.cmdNum = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.cmdNum++;
            if (gp.ui.cmdNum > 3) {
                gp.ui.cmdNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.cmdNum == 3) {
                gp.setGameState(gp.menuState);
                gp.ui.cmdNum = 0;
            } else {
                startGame(gp.ui.cmdNum);
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.menuState);
            gp.ui.cmdNum = 0;
        }

    }

    public void playState(int code) {

        if (code == KeyEvent.VK_F)
            gp.player.attack();
        if (code == KeyEvent.VK_W)
            upPressed = true;
        if (code == KeyEvent.VK_A)
            leftPressed = true;
        if (code == KeyEvent.VK_S)
            downPressed = true;
        if (code == KeyEvent.VK_D)
            rightPressed = true;
        if (code == KeyEvent.VK_P)
            gp.setGameState(gp.pauseState);
        if (code == KeyEvent.VK_ESCAPE) {
            gp.resetGame();
        }

        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }

        if (code == KeyEvent.VK_R) {
            gp.tileM.loadMap(gp.mapPath);
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P)
            gp.setGameState(gp.playState);
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.playState);
        }
    }

    public void finishState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.resetGame();
        }
    }

    public void selectMapState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.cmdNum--;
            if (gp.ui.cmdNum < 0) {
                gp.ui.cmdNum = 3;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.cmdNum++;
            if (gp.ui.cmdNum > 3) {
                gp.ui.cmdNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.cmdNum != 3) {
                gp.mapPath = "/map/map0" + gp.ui.cmdNum + ".txt";
                gp.tileM.loadMap(gp.mapPath);
            }
            gp.ui.cmdNum = 1;
            gp.setGameState(gp.menuState);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.setGameState(gp.menuState);
            gp.ui.cmdNum = 1;
        }

    }

    public void editState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.tileM.saveMap();
            gp.setGameState(gp.menuState);
        }
    }
}
