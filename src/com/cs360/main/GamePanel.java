package com.cs360.main;

import com.cs360.entity.Bot;
import com.cs360.entity.Bullet;
import com.cs360.entity.Player;
import com.cs360.object.GameObj;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    public final int tileSize = 64; // 64x64
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 1536
    public final int screenHigh = tileSize * maxScreenRow; // 896

    public final int FPS = 60;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int winState = 3;
    public final int loseState = 4;
    public final int editState = 5;
    public final int selectMapState = 6;
    public final int selectHardState = 7;

    public String mapPath = "/map/map00.txt"; // Đường dẫn mặc định để tải map
    public String objPath = "/map/obj00.txt"; // Đường dẫn mặc định để tải object

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);

    MouseHandler mouseH = new MouseHandler(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound effect = new Sound();

    public UI ui = new UI(this);
    public EffectManager effectM = new EffectManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this); // Kiểm tra các vật thể giao nhau
    public AssetSetter aSetter = new AssetSetter(this); // Khởi tạo bot và object

    public Player player = new Player(this, keyH);
    public GameObj[] obj = new GameObj[64];
    public Bot[] bot = new Bot[16];
    public Bullet[] bullet = new Bullet[128];

    // Thay đổi game state sẽ thay đổi ui, draw, keyH, ..
    private int gameState;
    // Tốc độ bắn và tốc độ chạy cho bot tùy theo độ khó
    private int hardMode = 1;
    // Delay cho sound
    private boolean delayOn = false;
    private int delayCounter = 0;
    private boolean endSound = true;
    // macOS dùng file map txt khác với Windows
    public boolean systemIsMacOS = false;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHigh));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);

    }

    public void setupGame() {

        if (Objects.equals(System.getProperty("os.name"), "Mac OS X")) systemIsMacOS = true;
        tileM.loadMap(mapPath);
        playMusic(0);
        gameState = menuState;

    }

    public void resetGame() {

        stopMusic();
        playMusic(0);
        setGameState(menuState);
        aSetter.resetGame();

        player = new Player(this, keyH);
        obj = new GameObj[64];
        bot = new Bot[16];
        bullet = new Bullet[128];

        endSound = true;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Update và vẽ lại màn hình mỗi 1/60 s
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        double startTimer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
//                System.out.println("FPS: " + drawCount);
            }

        }
    }

    public void update() {

        if (gameState == playState) {

            // Gọi method update cho tất cả các obj của game
            player.update();

            for (Bot item : bot) {
                if (item != null) {
                    item.update();
                }
            }

            for (Bullet item : bullet) {
                if (item != null) {
                    item.update();
                }
            }

            // Chạy sound effect đang được delay
            if (delayOn) {
                delayCounter++;
                if (delayCounter > FPS) {
                    playSoundEffect(3);
                    delayCounter = 0;
                    delayOn = false;
                }
            }

        }

        if (endSound) {
            if (gameState == winState) {
                playSoundEffect(4);
                endSound = false;
            }
            if (gameState == loseState) {
                playSoundEffect(5);
                endSound = false;
            }
        }
    }

    public void paintComponent(Graphics g) {

        // Vẽ lại màn hình sau khi update

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if (keyH.showDebugText) {
            drawStart = System.nanoTime();
        }

        if (gameState == menuState
                || gameState == selectMapState
                || gameState == selectHardState) {

            ui.draw(g2);

        } else if (gameState == editState) {

            tileM.draw2Edit(g2);

        } else {

            tileM.draw(g2);

            player.draw(g2);

            for (Bot value : bot) {
                if (value != null) {
                    value.draw(g2);
                }
            }

            for (Bullet value : bullet) {
                if (value != null) {
                    value.draw(g2);
                }
            }

            for (GameObj gameObj : obj) {
                if (gameObj != null) {
                    gameObj.draw(g2, this);
                }
            }

            effectM.draw(g2);

            ui.draw(g2);

        }

        if (keyH.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(g2.getFont().deriveFont(20F));
            g2.setColor(Color.white);

            int x = 50;
            int y = 700;
            int lineHeight = 25;

            g2.drawString("X: " + player.x, x, y);
            y += lineHeight;
            g2.drawString("Y: " + player.y, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.x / tileSize), x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.y / tileSize), x, y);
            y += lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);
        }

        g2.dispose();

    }

    public void playMusic(int i) {

        music.setFile(i);
        music.setVolume((float) 0.1);
        music.play();
        music.loop();

    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {

        effect.setFile(i);
        effect.play();

    }

    public void playDelayEffect() {
        delayOn = true;
    }

    public void checkWinCondition() {
        boolean empty = true;
        for (Bot value : bot) {
            if (value != null) {
                empty = false;
                break;
            }
        }
        if (empty) {
            setGameState(winState);
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getHardMode() {
        return hardMode;
    }

    public void setHardMode(int hardMode) {
        this.hardMode = hardMode;
        player.setHardMode(hardMode);
    }
}
