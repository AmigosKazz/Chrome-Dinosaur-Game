package dinisaurGame;

import java.awt.*;

public class Block {
    int x;
    int y;
    int width;
    int height;
    Image image;

    Block(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
}
