package dinisaurGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChromeDinosaur extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 750;
    int boardHeight = 250;

    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image cacts1Img;
    Image cacts2Img;
    Image cacts3Img;

    //dinosaur
    int dinosaurWidth = 88;
    int dinosaurHeight = 94;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight;

    Block dinosaur;

    Timer gameLoop;

     public ChromeDinosaur() {
         setPreferredSize(new Dimension(boardWidth, boardHeight));
         setBackground(Color.lightGray);
         setFocusable(true);
         addKeyListener(this);

         dinosaurImg = new ImageIcon(getClass().getResource("./image/dino-run.gif")).getImage();
         dinosaurDeadImg = new ImageIcon(getClass().getResource("./image/dino-dead.png")).getImage();
         dinosaurJumpImg = new ImageIcon(getClass().getResource("./image/dino-jump.png")).getImage();
         cacts1Img = new ImageIcon(getClass().getResource("./image/cactus1.png")).getImage();
         cacts2Img = new ImageIcon(getClass().getResource("./image/cactus2.png")).getImage();
         cacts3Img = new ImageIcon(getClass().getResource("./image/cactus3.png")).getImage();

         dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg);

         gameLoop = new Timer(1000/60, this);
         gameLoop.start();
    }

    public void paintComponent(Graphics g) {
         super.paintComponent(g);
         draw(g);
    }

    public void draw(Graphics g) {
         g.drawImage(dinosaur.image, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("JUMPPPP!!");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
