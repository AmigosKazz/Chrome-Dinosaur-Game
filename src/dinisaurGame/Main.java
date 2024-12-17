package dinisaurGame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int boardWidth = 750;
        int boardHeight = 250;

        JFrame frame = new JFrame("chrome Dinosaur game");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ChromeDinosaur chromeDinosaur = new ChromeDinosaur();
        frame.add(chromeDinosaur);
        frame.pack();
        chromeDinosaur.requestFocus();
        frame.setVisible(true);
    }
}