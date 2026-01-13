package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinding implements KeyListener {
    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed, restartPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) upPressed = true;
        if(code == KeyEvent.VK_S) downPressed = true;
        if(code == KeyEvent.VK_A) leftPressed = true;
        if(code == KeyEvent.VK_D) rightPressed = true;
        
        if (code == KeyEvent.VK_ESCAPE) {
            GamePanel.isInMenu = !GamePanel.isInMenu;
            if (GamePanel.isInMenu) {
                GamePanel.music.stop();
                GamePanel.gorselLabel.setVisible(false);
                GamePanel.resumeButton.setVisible(true);
                GamePanel.quitButton.setVisible(true);
                GamePanel.menuButton.setVisible(true);
            } else {
                GamePanel.music.play(0, true);
                GamePanel.music.loop();
                GamePanel.gorselLabel.setVisible(true);
                GamePanel.resumeButton.setVisible(false);
                GamePanel.quitButton.setVisible(false);
                GamePanel.menuButton.setVisible(false);
            }
        }
        
        if(code == KeyEvent.VK_ENTER) {
            restartPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}