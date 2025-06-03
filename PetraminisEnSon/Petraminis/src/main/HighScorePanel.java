package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import database.Database;

public class HighScorePanel extends JPanel {
    public HighScorePanel(MainFrame frame) {
        setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Yüksek Skorlar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JTextArea scoreArea = new JTextArea();
        scoreArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scoreArea.setEditable(false);
        scoreArea.setBackground(Color.BLACK);
        scoreArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(scoreArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("← Geri");
        backButton.addActionListener(e -> frame.showMenu());
        add(backButton, BorderLayout.SOUTH);

        // Hata yönetimi geliştirilmiş versiyon
        try {
            Database db = new Database();
            List<String> scores = db.getHighScores();
            
            StringBuilder sb = new StringBuilder();
            if (scores.isEmpty() || scores.get(0).contains("hata")) {
                sb.append("Henüz kayıtlı skor yok veya skorlar yüklenemedi.\n");
                sb.append("Oyun oynayarak skorlar tablosuna eklenebilirsiniz.");
            } else {
                sb.append(" SIRA    İSİM             PUAN   SEVİYE\n");
                sb.append("----------------------------------------\n");
                for (String score : scores) {
                    sb.append(score).append("\n");
                }
            }
            scoreArea.setText(sb.toString());
        } catch (Exception e) {
            scoreArea.setText("Skorlar yüklenirken hata oluştu:\n" + e.getMessage() + 
                              "\n\nOyun oynayarak skorlar tablosuna eklenebilirsiniz.");
            e.printStackTrace();
        }
    }
}