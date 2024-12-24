package dinisaurGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

public class ChromeDinosaur extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 750;
    int boardHeight = 250;

    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;

    class Block {
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    int dinosaurWidth = 88;
    int dinosaurHeight = 94;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight;

    Block dinosaur;

    int cactus1Width = 34;
    int cactus2Width = 69;
    int cactus3Width = 102;

    int cactusHeight = 70;
    int cactusX = 700;
    int cactusY = boardHeight - cactusHeight;
    ArrayList<Block> cactusArray;

    int velocityX = -12;
    int velocityY = 0;
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;

    Timer gameLoop;
    Timer placeCactusTimer;

    public ChromeDinosaur() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        loadImages();
        initializeGameObjects();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        placeCactusTimer = new Timer(1500, e -> placeCactus());
        placeCactusTimer.start();
    }

    private void loadImages() {
        dinosaurImg = loadImage("./image/dino-run.gif");
        dinosaurDeadImg = loadImage("./image/dino-dead.png");
        dinosaurJumpImg = loadImage("./image/dino-jump.png");
        cactus1Img = loadImage("./image/cactus1.png");
        cactus2Img = loadImage("./image/cactus2.png");
        cactus3Img = loadImage("./image/cactus3.png");
    }

    private Image loadImage(String path) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(path))).getImage();
    }

    private void initializeGameObjects() {
        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg);
        cactusArray = new ArrayList<>();
    }

    void placeCactus() {
        if (gameOver) {
            return;
        }

        double placeCactusChance = Math.random(); //0 - 0.999999
        if (placeCactusChance > .90) { //10% you get cactus3
            Block cactus = new Block(cactusX, cactusY, cactus3Width, cactusHeight, cactus3Img);
            cactusArray.add(cactus);
        }
        else if (placeCactusChance > .70) { //20% you get cactus2
            Block cactus = new Block(cactusX, cactusY, cactus2Width, cactusHeight, cactus2Img);
            cactusArray.add(cactus);
        }
        else if (placeCactusChance > .50) { //20% you get cactus1
            Block cactus = new Block(cactusX, cactusY, cactus1Width, cactusHeight, cactus1Img);
            cactusArray.add(cactus);
        }

        if (cactusArray.size() > 10) {
            cactusArray.remove(0); //remove the first cactus from ArrayList
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);

        for (Block cactus : cactusArray) {
            g.drawImage(cactus.img, cactus.x, cactus.y, cactus.width, cactus.height, null);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        g.drawString(gameOver ? "Game Over: " + score : String.valueOf(score), 10, 35);
    }

    public void move() {
        velocityY += gravity;
        dinosaur.y += velocityY;

        if (dinosaur.y > dinosaurY) {
            dinosaur.y = dinosaurY;
            velocityY = 0;
            dinosaur.img = dinosaurImg;
        }

        for (Block cactus : cactusArray) {
            cactus.x += velocityX;

            if (collision(dinosaur, cactus)) {
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
            }
        }

        score++;
    }

    boolean collision(Block a, Block b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            placeCactusTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // System.out.println("JUMP!");
            if (dinosaur.y == dinosaurY) {
                velocityY = -17;
                dinosaur.img = dinosaurJumpImg;
            }

            if (gameOver) {
                dinosaur.y = dinosaurY;
                dinosaur.img = dinosaurImg;
                velocityY = 0;
                cactusArray.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placeCactusTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}