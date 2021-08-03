import java.util.Random;

public class Apple {
    public final int DOT_SIZE = 32; // Размер пикселя !КОНСТАНТА!
    Integer x;
    Integer y;

    public void createApple() {
        x = new Random().nextInt(16) * DOT_SIZE;
        y = new Random().nextInt(16) * DOT_SIZE;
    }
}