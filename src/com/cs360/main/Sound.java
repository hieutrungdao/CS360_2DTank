
package com.cs360.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[16];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/menu.wav");
        soundURL[1] = getClass().getResource("/sound/battleTheme.wav");
        soundURL[2] = getClass().getResource("/sound/ready.wav");
        soundURL[3] = getClass().getResource("/sound/go.wav");
        soundURL[4] = getClass().getResource("/sound/mission_completed.wav");
        soundURL[5] = getClass().getResource("/sound/mission_failed.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setVolume(float volume) {

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        gainControl.setValue(20f * (float) Math.log10(volume));

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
