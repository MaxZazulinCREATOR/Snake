import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}