package mino;

import java.awt.Color;

public class Mino_Plus extends Mino {

    public Mino_Plus() {
        create(Color.red); 
    }

    @Override
    public void setXY(int x, int y) {
        // Başlangıç şekli (Direction 1):
        //    o
        //  o o o
        //    o 

        b[0].x = x;         b[0].y = y;
        b[1].x = x;         b[1].y = y + 30;
        b[2].x = x + 30;    b[2].y = y;
        b[3].x = x;         b[3].y = y - 30;
        b[4].x = x - 30;    b[4].y = y;
    }

    public void getDirection1() {
        // Dönüş 1:
        //    o
        //  o o o
        //    o 
        tempB[0].x = b[0].x;         tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;         tempB[1].y = b[0].y + 30;
        tempB[2].x = b[0].x + 30;    tempB[2].y = b[0].y;
        tempB[3].x = b[0].x;         tempB[3].y = b[0].y - 30;
        tempB[4].x = b[0].x - 30;    tempB[4].y = b[0].y;

        updateXY(1);
    }

    public void getDirection2() {
        getDirection1(); // Simetrik olduğu için dönüş değişmez
    }

    public void getDirection3() {
        getDirection1();
    }

    public void getDirection4() {
        getDirection1();
    }
}