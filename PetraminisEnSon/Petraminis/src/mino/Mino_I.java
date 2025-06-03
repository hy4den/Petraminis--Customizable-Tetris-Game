package mino;

import java.awt.Color;

public class Mino_I extends Mino {
    public Mino_I () {
        create(Color.blue);
    }
    public void setXY(int x, int y) {
        // o
        // o
        // o
        // o
        // o
    	
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE*2;
        b[2].x = b[0].x;
        b[2].y = b[0].y - Block.SIZE;
        b[3].x = b[0].x;
        b[3].y = b[0].y + Block.SIZE;
        b[4].x = b[0].x;
        b[4].y = b[0].y + Block.SIZE*2; 
    }

    public void getDirection1 () {
        // o o o o o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE*2;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y;
        tempB[4].x = b[0].x - Block.SIZE*2;
        tempB[4].y = b[0].y;

        updateXY(1);
    }

    public void getDirection2 () {
        // o
        // o
        // o
        // o
        // o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + Block.SIZE*2;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - Block.SIZE;
        tempB[4].x = b[0].x;
        tempB[4].y = b[0].y - Block.SIZE*2;

        updateXY(2);
    }

    public void getDirection3 () {
        // o o o o o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE*2;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y;
        tempB[4].x = b[0].x - Block.SIZE*2;
        tempB[4].y = b[0].y;

        updateXY(3);
    }

    public void getDirection4 () {
        // o
        // o
        // o
        // o
        // o
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE*2;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE;
        tempB[4].x = b[0].x;
        tempB[4].y = b[0].y + Block.SIZE*2;

        updateXY(4);
    }
}