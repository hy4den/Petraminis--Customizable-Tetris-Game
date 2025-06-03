package main;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import javax.swing.*;

import database.Database;

import mino.Block;
import mino.color;
import mino.Mino;
import mino.Mino_D;
import mino.Mino_I;
import mino.Mino_L;
import mino.Mino_L2;
import mino.Mino_P;
import mino.Mino_Plus;
import mino.Mino_R;
import mino.Mino_S;
import mino.Mino_T;
import mino.Mino_U;
import mino.Mino_W;
import mino.Mino_Z;

public class PlayManager {
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;
    
    Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();
    
    public static int dropInterval = 60; 
    boolean gameOver;
    
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();
    
    private int level = 1;
    private int score = 0;
    private int lines = 0;
    
    // Getter metodları
    public int getLevel() { return level; }
    public int getScore() { return score; }
    public int getLines() { return lines; }

    public PlayManager() {
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;
        
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;
        
        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;
        
        currentMino = new Mino_P();
        currentMino.setXY(MINO_START_X, MINO_START_Y);
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
    }

    private Mino pickMino() {
        int i = new Random().nextInt(12);
        switch(i) {
            case 0: return new Mino_T();
            case 1: return new Mino_U();
            case 2: return new Mino_S();
            case 3: return new Mino_I();
            case 4: return new Mino_L();
            case 5: return new Mino_Z();
            case 6: return new Mino_P();
            case 7: return new Mino_R();
            case 8: return new Mino_D();
            case 9: return new Mino_L2();
            case 10: return new Mino_Plus();
            case 11: return new Mino_W();
            default: return new Mino_T();
        }
    }

    public void update() {
        if (!currentMino.active) {
            for (int i = 0; i < currentMino.b.length; i++) {
                staticBlocks.add(currentMino.b[i]);
            }
              if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y) {
                gameOver = true;
                GamePanel.music.stop();
                GamePanel.sfx.play(2, false);
                Database.saveScore(GamePanel.playerName, getScore(), getLevel());
                return;
            }

            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
            
            checkDelete();
        } else {
            currentMino.update();
        }
    }

    private void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

        while (y < bottom_y) {
            blockCount = 0;
            x = left_x;

            while (x < right_x) {
                for (Block block : staticBlocks) {
                    if (block.x == x && block.y == y) {
                        blockCount++;
                        break;
                    }
                }
                x += Block.SIZE;
            }

            if (blockCount == 12) {
                effectCounterOn = true;
                effectY.add(y);
                
                for (int i = staticBlocks.size() - 1; i >= 0; i--) {
                    if (staticBlocks.get(i).y == y) {
                        staticBlocks.remove(i);
                    }
                }
                
                lineCount++;
                lines++;
                
                if (lines % 10 == 0 && dropInterval > 1) {
                    level++;
                    dropInterval = Math.max(1, dropInterval - 10);
                }

                for (Block block : staticBlocks) {
                    if (block.y < y) {
                        block.y += Block.SIZE;
                    }
                }
                continue;
            }
            y += Block.SIZE;
        }        if (lineCount > 0) {
            GamePanel.sfx.play(1, false);
            
            // Skor algoritması - Tetris standart puanlaması (Nintendo)
            int lineBonus = 0;
            switch(lineCount) {
                case 1: lineBonus = 40; break;    // Tek satır: 40 * level
                case 2: lineBonus = 100; break;   // İki satır: 100 * level
                case 3: lineBonus = 300; break;   // Üç satır: 300 * level
                case 4: lineBonus = 1200; break;  // Dört satır (Tetris!): 1200 * level
                default: lineBonus = lineCount * 400; break;  // 4'ten fazla (özel durum)
            }
            
            // Skoru önemli miktarda artırıyoruz ki fark edilebilir olsun
            int newScore = lineBonus * level;
            score += newScore;
            
            // Konsola debug bilgisi yazalım
            System.out.println("Yeni skor: " + score + " (Eklendi: " + newScore + ")");
        }
    }

    public void draw(Graphics2D g2) {
        // Draw play area
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(left_x, top_y, WIDTH, HEIGHT);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);
        
        // Draw next mino frame
        int x = right_x + 70;
        int y = bottom_y - 250;
        g2.drawRect(x, y, 250, 250);
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 90, y + 60);        // Draw score and level frame with enhanced visibility
        g2.drawRect(x, top_y, 250, 300);
        x += 40;
        y = top_y + 90;
        
        // Level bilgisi
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(new Color(255, 215, 0)); // Altın sarısı
        g2.drawString("LEVEL: " + getLevel(), x, y); 
        y += 70;
        
        // Skor bilgisi - daha büyük ve dikkat çekici
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        g2.setColor(new Color(255, 100, 100)); // Parlak kırmızı
        g2.drawString("SCORE: " + getScore(), x, y); 
        y += 70;
        
        // Satır bilgisi
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setColor(Color.WHITE);
        g2.drawString("LINES: " + getLines(), x, y);
        
        // Draw current and next mino
        if (currentMino != null) currentMino.draw(g2);
        nextMino.draw(g2);
        
        // Draw static blocks
        for (Block block : staticBlocks) {
            block.draw(g2);
        }
        
        // Draw line clear effect
        if (effectCounterOn) {
            effectCounter++;
            g2.setColor(Color.red);
            for (Integer effect : effectY) {
                g2.fillRect(left_x, effect, WIDTH, Block.SIZE);
            }
            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }
        
        // Draw game over
        if (gameOver) {
            String text = "GAME OVER";
            x = left_x + 20;
            y = top_y + 320;
            
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            Font font = g2.getFont().deriveFont(50f);
            g2.setFont(font);

            FontRenderContext frc = g2.getFontRenderContext();
            TextLayout textLayout = new TextLayout(text, font, frc);
            Shape outline = textLayout.getOutline(AffineTransform.getTranslateInstance(x, y));

            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(5f));
            g2.draw(outline);
            g2.setColor(Color.white);
            g2.fill(outline);
        }
    }
}