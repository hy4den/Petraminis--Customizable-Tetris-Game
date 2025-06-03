package mino;

import java.awt.Color;

public class Mino_D extends Mino {

    public Mino_D() {
        create(Color.darkGray); 
    }

    @Override
    public void setXY(int x, int y) {
        // Başlangıç şekli (Direction 1):
        //
        //  o
        //  o o
        //  o o

        b[0].x = x;                  b[0].y = y;
        b[1].x = x + Block.SIZE;     b[1].y = y;
        b[2].x = x + Block.SIZE;     b[2].y = y - Block.SIZE;
        b[3].x = x;                  b[3].y = y - Block.SIZE;
        b[4].x = x;                  b[4].y = y - Block.SIZE*2;
    }

    public void getDirection1() {
        // Aynı şekli tekrar oluşturuyor (Direction 1)

        tempB[0].x = b[0].x;                    tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE;       tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;       tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x;                    tempB[3].y = b[0].y - Block.SIZE;
        tempB[4].x = b[0].x;                    tempB[4].y = b[0].y - Block.SIZE*2;

        updateXY(1);
    }

    public void getDirection2() {
        // Dönüş 2:
        //
        //  o o o
        //  o o

        tempB[0].x = b[0].x;                    tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;                    tempB[1].y = b[0].y + Block.SIZE;
        tempB[2].x = b[0].x + Block.SIZE;       tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x + Block.SIZE;       tempB[3].y = b[0].y;
        tempB[4].x = b[0].x + Block.SIZE*2;     tempB[4].y = b[0].y;

        updateXY(2);
    }

    public void getDirection3() {
        // Dönüş 3:
        //
        //   o o
        //   o o
        //     o

        tempB[0].x = b[0].x;                    tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;       tempB[1].y = b[0].y;
        tempB[2].x = b[0].x - Block.SIZE;       tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;                    tempB[3].y = b[0].y + Block.SIZE;
        tempB[4].x = b[0].x;                    tempB[4].y = b[0].y + Block.SIZE*2;

        updateXY(3);
    }

    public void getDirection4() {
        // Dönüş 3:
        //
        //   o o
        // o o o

        tempB[0].x = b[0].x;                    tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;       tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;       tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE;       tempB[3].y = b[0].y - Block.SIZE;
        tempB[4].x = b[0].x;                    tempB[4].y = b[0].y - Block.SIZE;

        updateXY(4);
    }
}