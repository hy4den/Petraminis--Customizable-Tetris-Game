package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Database {
    // SQLite kullanarak bağlantı oluşturuyoruz
    private static final String DB_FILE = "scores.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;
    
    private Connection connection;
    
    public Database() {
        // Yapıcı metod - veritabanı dosyasının varlığını kontrol edip tabloyu oluşturur
        try {
            File dbFile = new File(DB_FILE);
            boolean newDb = !dbFile.exists();
            
            connect();
            
            if (newDb) {
                createTable();
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı başlatılamadı: " + e.getMessage());
        }
    }
    
    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "player_name TEXT NOT NULL, " +
                     "score INTEGER NOT NULL, " +
                     "level INTEGER NOT NULL, " +
                     "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                     
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Skor tablosu oluşturuldu!");
        }
    }
    
    public void connect() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Veritabanına Bağlanıldı!");
        } catch (SQLException e) {
            throw new SQLException("SQLite bağlantısı kurulamadı", e);
        }
    }
    
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Veritabanı Bağlantısı Kapatıldı!");
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı kapatılırken hata: " + e.getMessage());
        }
    }
      public void insertScore(String playerName, int score, int level) throws SQLException {
        String sql = "INSERT INTO scores (player_name, score, level) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playerName);
            stmt.setInt(2, score);
            stmt.setInt(3, level);
            stmt.executeUpdate();
            System.out.println("Skor Kaydedildi!");
        }
    }
    
    public static void saveScore(String playerName, int score, int level) {
        Database db = new Database();
        try {
            // Bağlantı yapıcı metodda zaten kuruluyor, tekrar bağlanmaya gerek yok
            db.insertScore(playerName, score, level);
        } catch (SQLException e) {
            System.err.println("Skor kaydedilirken hata: " + e.getMessage());
            e.printStackTrace();
        } finally {
            db.disconnect();
        }
    }
      public List<String> getHighScores() {
        List<String> scores = new ArrayList<>();
        try {
            // Bağlantı yapıcı metodda zaten kuruluyor, tekrar bağlanmaya gerek yok
            String sql = "SELECT player_name, score, level FROM scores ORDER BY score DESC LIMIT 10";
            try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
                
                int rank = 1;
                while (rs.next()) {
                    String playerName = rs.getString("player_name");
                    int score = rs.getInt("score");
                    int level = rs.getInt("level");
                    scores.add(String.format("%2d. %-15s %5d (Level: %2d)", rank++, playerName, score, level));
                }
                
                if (scores.isEmpty()) {
                    scores.add("Henüz kayıtlı skor bulunmuyor.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Skorlar alınırken hata: " + e.getMessage());
            scores.add("Veritabanı erişim hatası: " + e.getMessage());
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return scores;
    }
}