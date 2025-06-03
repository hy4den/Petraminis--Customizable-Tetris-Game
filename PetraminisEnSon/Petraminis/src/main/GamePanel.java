package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics2D;
import java.awt.Image;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManager pm;
    public static Sound music = new Sound();
    public static Sound sfx = new Sound();    public static boolean isInMenu = false;
    public static JLabel gorselLabel;
    public static JButton resumeButton;
    public static JButton quitButton;
    public static JButton menuButton;
    public static String playerName = "Player";

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addKeyListener(new KeyBinding());
        this.setFocusable(true);
        
        pm = new PlayManager();
        
        // Load and resize image
        ImageIcon icon = new ImageIcon(getClass().getResource("/RA.png"));
        Image scaledImage = icon.getImage().getScaledInstance(603, 810, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        gorselLabel = new JLabel(scaledIcon);
        gorselLabel.setBounds(-40, 90, 600, 600);
        this.add(gorselLabel);

        setupButtons();
    }

    private void setupButtons() {
        resumeButton = new JButton("Devam Et");
        resumeButton.setBounds(100, 150, 200, 50);
        resumeButton.setFocusable(false);
        resumeButton.setVisible(false);
        resumeButton.setBackground(new Color(0, 0, 0));
        resumeButton.setForeground(Color.WHITE);
        resumeButton.setFocusPainted(false);
        resumeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        resumeButton.addActionListener(e -> {
            isInMenu = false;
            resumeButton.setVisible(false);
            quitButton.setVisible(false);
            menuButton.setVisible(false);
            if (gorselLabel != null) {
                gorselLabel.setVisible(true);
            }
            music.play(0, true);
            music.loop();
        });
        add(resumeButton);
        
        menuButton = new JButton("Ana Menüye Dön");
        menuButton.setBounds(100, 200, 200, 50);
        menuButton.setFocusable(false);
        menuButton.setVisible(false);
        menuButton.setBackground(new Color(0, 0, 0));
        menuButton.setForeground(Color.WHITE);
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        menuButton.addActionListener(e -> {
            MainFrame frame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            frame.showMenu();
            music.stop();
        });
        add(menuButton);
        
        quitButton = new JButton("Çıkış");
        quitButton.setBounds(100, 250, 200, 50);
        quitButton.setFocusable(false);
        quitButton.setVisible(false);
        quitButton.setBackground(new Color(0, 0, 0));
        quitButton.setForeground(Color.WHITE);
        quitButton.setFocusPainted(false);
        quitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
        music.play(0, true);
        music.loop();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }    private void restartGame() {
        // StaticBlocks static bir koleksiyon olduğu için önce temizleyelim
        PlayManager.staticBlocks.clear();
        // Sonra yeni PlayManager oluşturalım
        pm = new PlayManager();
        repaint();
        music.stop();
        music.play(0, true);
    }private void update() {
        if (isInMenu) return;

        if (!pm.gameOver && KeyBinding.restartPressed) {
            KeyBinding.restartPressed = false;
        }

        if (pm.gameOver && KeyBinding.restartPressed) {
            restartGame();
            KeyBinding.restartPressed = false;
            return;
        }

        if (!pm.gameOver) {
            pm.update();
            // Her zaman repaint yaparak skorun güncel görünmesini sağlıyoruz
            repaint();
        }
    }
    
    public void drawEscMenu(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 32));
        g.drawString("ESC Menüsü", 100, 100);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
        if (isInMenu) {
            drawEscMenu(g);
        }
    }
}