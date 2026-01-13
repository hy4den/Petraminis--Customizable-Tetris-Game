package main;

import javax.swing.*;

public class MainFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;

    public MainFrame() {
        setTitle("Petraminis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showGame() {
        gamePanel = new GamePanel();
        setContentPane(gamePanel);
        revalidate();
        gamePanel.launchGame();
        gamePanel.requestFocusInWindow();
    }

    public void showMenu() {
        menuPanel = new MenuPanel(this);
        setContentPane(menuPanel);
        revalidate();
        menuPanel.requestFocusInWindow();
    }
    
    public void showHighScores() {
        setContentPane(new HighScorePanel(this));
        revalidate();
    }
}