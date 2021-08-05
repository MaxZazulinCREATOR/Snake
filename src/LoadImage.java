import javax.swing.*;

public class LoadImage {
    public static void loadImage() { // Метод загрузки картинок
        ImageIcon iie = new ImageIcon("src/Image/Доп фон.png");
        Mechanics.background = iie.getImage();
        ImageIcon iia = new ImageIcon("src/Image/Apple.png");
        Mechanics.AppleI = iia.getImage();
        ImageIcon iid = new ImageIcon("src/Image/dots.png");
        Mechanics.dot = iid.getImage();

    }
}
