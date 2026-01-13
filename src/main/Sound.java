package main;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineListener;


public class Sound {
	Clip musicClip;
	URL url[]=new URL[10];
	private boolean isMuted = false;		// mute kontrolü için
	
	public Sound() {
		url[0]=getClass().getResource("/Background Music.wav");
		url[1]=getClass().getResource("/delete line.wav");
		url[2]=getClass().getResource("/gameover.wav");
		url[3]=getClass().getResource("/rotation.wav");
		url[4]=getClass().getResource("/touch floor.wav");
		url[5]=getClass().getResource("/Pause.wav");
		url[6]=getClass().getResource("/menu.wav");
	}
	public void play(int i, boolean music) {
        if (isMuted) return; // Sessizlik aktifse çalma

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if (music) {
                musicClip = clip;
            }

            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == Type.STOP) {
                        clip.close();
                    }
                }
            });

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        if (!isMuted && musicClip != null) {
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    // Mute işlemleri
    public void mute() {
        isMuted = true;
        stop();
    }

    public void unmute() {
        isMuted = false;
    }

    public boolean isMuted() {
        return isMuted;
    }
}