package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    Sound soundformenu = new Sound();
    
    public MenuPanel(MainFrame frame) {
        soundformenu.play(6, true);
        
        setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);

        // Background image
        ImageIcon icon = new ImageIcon(getClass().getResource("/RA2.png"));
        Image scaledImage = icon.getImage().getScaledInstance(603, 810, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel gorselLabel = new JLabel(scaledIcon);
        gorselLabel.setBounds(315, 10, 700, 700);
        add(gorselLabel);

        setupButtons(frame);
    }

    private void setupButtons(MainFrame frame) {
        JButton startButton = createMenuButton("▶ Oyuna Başla", 500, 380);
        startButton.addActionListener(e -> {
            soundformenu.stop();
            frame.showGame();
        });
        add(startButton);
        
        JButton soundButton = createMenuButton("🎵 Ses: Açık", 500, 440);
        soundButton.addActionListener(e -> { 
            if (soundformenu.isMuted()) {
                soundformenu.unmute();
                soundformenu.play(6, true);
                soundButton.setText("🎵 Ses: Açık");
            } else {
                soundformenu.mute();
                soundButton.setText("🔇 Ses: Kapalı");
            }
        });
        add(soundButton);
        
        JButton hButton = createMenuButton("Skor Tablosu", 500, 500);
        hButton.addActionListener(e -> {
            frame.showHighScores();
            soundformenu.stop();
        });
        add(hButton);
        
        JButton exitButton = createMenuButton("⏹ Çıkış", 500, 560);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    private JButton createMenuButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 280, 60);
        button.setFont(new Font("SansSerif", Font.BOLD, 22));
        button.setBackground(new Color(169, 169, 169));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }
}