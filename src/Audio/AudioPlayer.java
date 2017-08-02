//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Audio;

import GameState.OptionState;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.FloatControl.Type;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlayer {
    private Clip clip;
    private long timecode;

    public AudioPlayer(String s, float volume) {
        System.out.println(s);

        try {
            //
            // NEW CODE NEW CODE NEW CODE
            //
            // Reading Audio Data From Whatever Source
            InputStream audioSrc = getClass().getResourceAsStream(s);
            // Adding buffer for mark/reset support
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            this.clip = AudioSystem.getClip();
            this.clip.open(dais);
            FloatControl gainControl = (FloatControl)this.clip.getControl(Type.MASTER_GAIN);
            gainControl.setValue(volume);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public boolean isPlaying() {
        return this.clip.isRunning();
    }

    public void pause() {
        this.timecode = this.clip.getMicrosecondPosition();
        this.clip.stop();
    }

    public void resume() {
        this.clip.setMicrosecondPosition(this.timecode);
        this.loop();
    }

    public void play() {
        if(OptionState.soundOn()) {
            if(this.clip != null) {
                this.stop();
                this.clip.setFramePosition(0);
                this.clip.start();
            }
        }
    }

    public void loop() {
        if(OptionState.soundOn()) {
            this.clip.loop(-1);
        }
    }

    public void stop() {
        if(this.clip.isRunning()) {
            this.clip.stop();
        }

    }

    public void close() {
        this.stop();
        this.clip.close();
    }
}
