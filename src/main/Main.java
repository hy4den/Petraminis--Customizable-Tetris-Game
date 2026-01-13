package main;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set the player name at the start of the game
            String playerName = JOptionPane.showInputDialog(null, "Oyuncu adınızı girin:", "Hoşgeldiniz", JOptionPane.PLAIN_MESSAGE);
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Anonim";
            }
            GamePanel.playerName = playerName;
            
            // Start the game
            new MainFrame();
        });
    }
}