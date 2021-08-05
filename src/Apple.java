import java.util.Random;

public class Apple {
    int appleX = Mechanics.appleX;
    int appleY = Mechanics.appleY;
    public static void createApple() {
        Mechanics.appleX = new Random().nextInt(16) * Mechanics.DOT_SIZE;
        Mechanics.appleY = new Random().nextInt(16) * Mechanics.DOT_SIZE;
    }
    public static void checkApple() {  // сьедение яблок
        int appleX = Mechanics.appleX;
        int appleY = Mechanics.appleY;
        int[] x = Mechanics.x;
        int[] y = Mechanics.y;
        boolean left = Mechanics.left;
        boolean right = Mechanics.right;
        boolean up = Mechanics.up;
        boolean back = Mechanics.back;


        if (x[0] == appleX - 32 && y[0] == appleY && !left && !up && !back) {
            Mechanics.dots++;
            Apple.createApple();
        }
        if (x[0] == appleX + 32 && y[0] == appleY && !right && !up && !back) {
            Mechanics.dots++;
            Apple.createApple();
        }
        if (x[0] == appleX && y[0] == appleY + 32 && !up && !right && !left) {
            Mechanics.dots++;
            Apple.createApple();
        }
        if (x[0] == appleX && y[0] == appleY - 32 && !back && !right && !left) {
            Mechanics.dots++;
            Apple.createApple();
        }
    }
}
