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

        else if (gp.gameState == gp.selectMapState)
            selectMapState(code);
        else if (gp.gameState == gp.selectHardState)
            selectHardState(code);
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
                gp.gameState = gp.selectHardState;
            }
            if (gp.ui.cmdNum == 1) {
                gp.gameState = gp.selectMapState;
                gp.ui.cmdNum = 0;
            }
            if (gp.ui.cmdNum == 2) {
                gp.gameState = gp.editState;
            }
            if (gp.ui.cmdNum == 3) {
                System.exit(0);
            }
        }

    }

    public void startGame() {
        gp.aSetter.setGame(gp.objPath);
        gp.gameState = gp.playState;
        gp.stopMusic();
        gp.playMusic(1);
        gp.playSoundEffect(2);
        gp.playDelayEffect();
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
                gp.gameState = gp.menuState;
                gp.ui.cmdNum = 0;
            } else if (gp.ui.cmdNum == 0) {
                startGame();
            } else if (gp.ui.cmdNum == 1) {
                gp.botSpeed = 3;
                gp.botFireRate = 60;
                gp.player.healthPoint = 2;
                startGame();
            } else if (gp.ui.cmdNum == 2) {
                gp.botSpeed = 4;
                gp.botFireRate = 80;
                gp.player.healthPoint = 1;
                startGame();
            }
        }
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.menuState;
            gp.ui.cmdNum = 0;
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
//        if(code == KeyEvent.VK_ESCAPE) {
//            gp.gameState = gp.menuState;
//            gp.stopMusic();
//            gp.playMusic(0);
//        }

        if(code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
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
            if (gp.ui.cmdNum == 3) {
                gp.gameState = gp.menuState;
                gp.ui.cmdNum = 1;
            }
            else {
                gp.mapPath = "/map/map0"+gp.ui.cmdNum+".txt";
                gp.tileM.loadMap(gp.mapPath);
                gp.gameState = gp.menuState;
                gp.ui.cmdNum = 1;
            }
        }
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.menuState;
            gp.ui.cmdNum = 1;
        }

    }

    public void editState(int code) {
        if(code == KeyEvent.VK_ESCAPE) {
            gp.tileM.saveMap();
            gp.gameState = gp.menuState;
        }
    }
}
