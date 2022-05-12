package com.cs360.main;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    int cmdNum = 0;
    double playTime;

    Font kenvector_future;
    BufferedImage heartIcon;
    BufferedImage tankIcon;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/kenvector_future.ttf");
            kenvector_future = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is));
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
        }

        heartIcon = setup("/icon/suit_hearts.png");
        tankIcon = setup("/tank/tank_bigRed.png");

    }

    public BufferedImage setup(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(kenvector_future);
        g2.setColor(Color.white);

        if (gp.getGameState() == gp.menuState)
            drawMenuScreen();
        if(gp.getGameState() == gp.editState)
            drawEditMapScreen();
        if (gp.getGameState() == gp.playState)
            drawPlayScreen();
        if (gp.getGameState() == gp.pauseState)
            drawPauseScreen();
        if (gp.getGameState() == gp.winState)
            drawFinishScreen();
        if (gp.getGameState() == gp.loseState)
            drawLoseScreen();
        if (gp.getGameState() == gp.selectMapState)
            drawSelectMapScreen();
        if (gp.getGameState() == gp.selectHardState)
            drawSelectHardScreen();
    }

    public void drawMenuScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Tank";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 2;

        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(text,x ,y);

        y += gp.tileSize;
        g2.drawImage(tankIcon, x + 3*gp.tileSize + gp.tileSize/2, y,
                tankIcon.getWidth(), tankIcon.getHeight(), null);

        g2.setFont(g2.getFont().deriveFont(48F));

        text = "Play Game";
        x = getXforCenteredText(text);
        y += 3 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 0) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Select Map";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 1) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Map Editor";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 2) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 3) {
            g2.drawString(">", x-gp.tileSize,y);
        }

    }

    public void drawSelectHardScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Tank";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 2;

        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(text,x ,y);

        y += gp.tileSize;
        g2.drawImage(tankIcon, x + 3*gp.tileSize + gp.tileSize/2, y,
                tankIcon.getWidth(), tankIcon.getHeight(), null);

        g2.setFont(g2.getFont().deriveFont(48F));

        text = "Easy";
        x = getXforCenteredText(text);
        y += 3 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 0) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Medium";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 1) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Hard";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 2) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 3) {
            g2.drawString(">", x-gp.tileSize,y);
        }

    }

    public void drawSelectMapScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Tank";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 2;

        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(text,x ,y);

        y += gp.tileSize;
        g2.drawImage(tankIcon, x + 3*gp.tileSize + gp.tileSize/2, y,
                tankIcon.getWidth(), tankIcon.getHeight(), null);

        g2.setFont(g2.getFont().deriveFont(48F));

        text = "Map 1";
        x = getXforCenteredText(text);
        y += 3 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 0) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Map 2";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 1) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Map 3";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 2) {
            g2.drawString(">", x-gp.tileSize,y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += 2 * gp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNum == 3) {
            g2.drawString(">", x-gp.tileSize,y);
        }

    }

    public void drawEditMapScreen() {

    }

    public void drawPlayScreen() {

        int x = 100;
        int y = 10;

        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("HP: ", 30,50 );
        for (int i = 0; i < gp.player.healthPoint; i++) {
            g2.drawImage(heartIcon, x, y,
                    heartIcon.getWidth(), heartIcon.getHeight(), null);
            x += heartIcon.getWidth();
        }

        playTime += (double)1/gp.FPS;
        g2.drawString("Time: " + dFormat.format(playTime), 1300,50 );

    }

    public void drawPauseScreen() {

        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(80F));

        int y = gp.screenHigh/2;
        int x = getXforCenteredText(text);

        g2.drawString(text, x, y);
    }

    public void drawFinishScreen() {

        String text;
        int textLength;
        int x;
        int y;

        g2.setFont(g2.getFont().deriveFont(50F));

        text = "Mission Completed!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHigh/2 - (gp.tileSize*3);
        g2.drawString(text, x, y);

        text = "Go!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHigh/2 - (gp.tileSize*2);
        g2.drawString(text, x, y);

    }

    public void drawLoseScreen() {

        String text;
        int textLength;
        int x;
        int y;

        g2.setFont(g2.getFont().deriveFont(80F));

        text = "Mission Failed!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHigh/2 - (gp.tileSize);
        g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String text) {
        return (gp.screenWidth/2 -
                (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2);

    }

}
