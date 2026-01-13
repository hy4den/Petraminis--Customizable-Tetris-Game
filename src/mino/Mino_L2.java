package mino;

import java.awt.Color;

public class Mino_L2 extends Mino {

    public Mino_L2() {
        create(Color.cyan); // L tipi minoyu cyan renkle oluştur
    }

    @Override
    public void setXY(int x, int y) {
        // Başlangıç şekli (Direction 1):
        // o
        // o   
        // o o o     

        b[0].x = x;         b[0].y = y;
        b[1].x = x;         b[1].y = y - 30;
        b[2].x = x;         b[2].y = y - 60;
        b[3].x = x + 30;    b[3].y = y;
        b[4].x = x + 60;    b[4].y = y;
    }

    public void getDirection1() {
    	tempB[0].x = b[0].x;         tempB[0].y = b[0].y;
    	tempB[1].x = b[0].x;         tempB[1].y = b[0].y - 30;
    	tempB[2].x = b[0].x;         tempB[2].y = b[0].y - 60;
    	tempB[3].x = b[0].x + 30;    tempB[3].y = b[0].y;
    	tempB[4].x = b[0].x + 60;    tempB[4].y = b[0].y;
        updateXY(1);
    }

    public void getDirection2() {
        tempB[0].x = b[0].x;     tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + 30; tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + 60; tempB[2].y = b[0].y;
        tempB[3].x = b[0].x;     tempB[3].y = b[0].y + 30;
        tempB[4].x = b[0].x;     tempB[4].y = b[0].y + 60;
        updateXY(2);
    }

    public void getDirection3() {
        tempB[0].x = b[0].x;     tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;     tempB[1].y = b[0].y + 30;
        tempB[2].x = b[0].x;     tempB[2].y = b[0].y + 60;
        tempB[3].x = b[0].x - 30; tempB[3].y = b[0].y;
        tempB[4].x = b[0].x - 60; tempB[4].y = b[0].y;
        updateXY(3);
    }

    public void getDirection4() {
        tempB[0].x = b[0].x;     tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - 30; tempB[1].y = b[0].y;
        tempB[2].x = b[0].x - 60; tempB[2].y = b[0].y;
        tempB[3].x = b[0].x;     tempB[3].y = b[0].y - 30;
        tempB[4].x = b[0].x;     tempB[4].y = b[0].y - 60;
        updateXY(4);
    }
}
