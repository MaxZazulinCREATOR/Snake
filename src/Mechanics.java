import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/* Внутреняя часть игры */
public class Mechanics extends JPanel implements ActionListener {
    public final int DOT_SIZE = 32; // Размер пикселя !КОНСТАНТА!
    private final int SIZE = 500; // Размер поля !КОНСТАНТА!
    private final int ALL_DOTS = 400; // Сколько пикселей можно заполнить
    public int appleX; // Позиция яблока по ширине
    public int appleY; // Позиция яблока по высоте
    public int[] x = new int[ALL_DOTS]; // Массивы для сохранения кординат змейки
    public int[] y = new int[ALL_DOTS];
    public int dots; // размер змейки
    public int delay = 200;
    public int level = 1;
    public int backgrondX = 0;
    public int backgrondY = 510;
    Music music = new Music();
    private Image dot; // рисунок пикселя
    private Image Apple; // рисунок яблока
    private Timer timer;
    private boolean left = false;     //Напрвление змейки
    private boolean right = true;
    private boolean back = false;
    private boolean up = false;
    private boolean inGame = true;   //Проверяет нахождение в игре
    private final boolean righteleport = false;
    private boolean pause = false;
    private Image background;


    public Mechanics() { // Конструктор
        Music.play("src/Music.Wav");
        setBackground(Color.black); // Цвет поля, окна
        loadImage();
        newGame();
        addKeyListener(new KeyField()); // метод обрабатывающий команды класса
        setFocusable(true); // фокусирует команды на поле
    }

    public void newGame() { //Механика ОБЬЕКТОВ ИГРЫ
        level = 1;
        dots = 3; // змейка
        for (int i = 0; i < dots; i++) {
            x[i] = 96 - i * DOT_SIZE; // Змейка занимает 3 ячейки, по этой причине,
            y[i] = 96;                 // чтоб комфортно начинать игру х имеет 3 значение,
            // в то время как y только одно
        }
        timer = new Timer(delay, this);
        timer.start();

        createApple();
    }

    public void checkApple() {  // сьедение яблок
        if (x[0] == appleX - 32 && y[0] == appleY && !left && !up && !back) {
            dots++;
            createApple();
        }
        if (x[0] == appleX + 32 && y[0] == appleY && !right && !up && !back) {
            dots++;
            createApple();
        }
        if (x[0] == appleX && y[0] == appleY + 32 && !up && !right && !left) {
            dots++;
            createApple();
        }
        if (x[0] == appleX && y[0] == appleY - 32 && !back && !right && !left) {
            dots++;
            createApple();
        }
    }
    

    public void loadImage() { // Метод загрузки картинок
        ImageIcon iie = new ImageIcon("src/Доп фон.png");
        background = iie.getImage();
        ImageIcon iia = new ImageIcon("src/Apple.png");
        Apple = iia.getImage();
        ImageIcon iid = new ImageIcon("src/dots.png");
        dot = iid.getImage();

    }
    public void createApple() {
        appleX = new Random().nextInt(16) * DOT_SIZE;
        appleY = new Random().nextInt(16) * DOT_SIZE;
    }
    @Override

    public void paintComponent(Graphics g) {

        int factor = 50 * level;
        int score = (dots - 3) * factor;

        if (score > 199 && 600 > score) {            // разбивка уровней
            level = 2;
            timer.stop();
            timer = new Timer(180, this);
            timer.start();
        }
        if (score > 799 && 1800 > score) {
            level = 3;
            timer.stop();
            timer = new Timer(160, this);
            timer.start();
        }
        if (score > 1799 && 3200 > score) {
            level = 4;
            timer.stop();
            timer = new Timer(150, this);
            timer.start();
        }
        if (score > 3199 && 5000 > score) {
            level = 5;
            timer.stop();
            timer = new Timer(140, this);
            timer.start();
        }
        if (score > 4999 && 7500 > score) {
            level = 6;
            timer.stop();
            timer = new Timer(130, this);
            timer.start();
        }
        if (score > 7499 && 9000 > score) {
            level = 7;
            timer.stop();
            timer = new Timer(120, this);
            timer.start();
        }
        if (score > 8999 && 11000 > score) {
            level = 8;
            timer.stop();
            timer = new Timer(110, this);
            timer.start();
        }
        if (score > 10999 && 13000 > score) {
            level = 9;
            timer.stop();
            timer = new Timer(100, this);
            timer.start();
        } else if (score > 13000) {
            level = 10;
            timer.stop();
            timer = new Timer(90, this);
            timer.start();
        }

        super.paintComponent(g);

        if (inGame) {

            g.drawImage(Apple, appleX, appleY, this);

            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
                g.drawImage(background, backgrondX, backgrondY, this);

                Font f = new Font("TimesRoman", Font.BOLD, 16);
                String string = "SCORE " + score;
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(string, 400, 30);

                String str = "Level " + level;
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(str, 30, 30);

                Font a = new Font("TimesRoman", Font.BOLD, 14);
                String str3 = "Для паузы нажмите - Esk, выход из паузы любая другая кнопка";
                g.setColor(Color.white);
                g.setFont(a);
                g.drawString(str3, 30, 530);

                Font m = new Font("TimesRoman", Font.BOLD, 10);
                String str4 = "creator:ZMax";
                g.setColor(Color.white);
                g.setFont(a);
                g.drawString(str4, 410, 555);
            }
        } else {
            String string = "Game Over";
            String str = "Для новой игры,";
            String str2 = "нажмите ENTER";
            Font f = new Font("TimesRoman", Font.BOLD, 64);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(string, 90, SIZE / 3 * 1);
            Font s = new Font("TimesRoman", Font.BOLD, 32);
            g.setColor(Color.white);
            g.setFont(s);
            g.drawString(str, 130, SIZE / 3 * 2);
            g.drawString(str2, 130, SIZE / 3 * 2 + 45);


            String str5 = "Ваш счёт: " + score;
            g.setColor(Color.white);
            g.setFont(s);
            g.drawString(str5, 250, 500);

        }
    }


    public void move() {
        for (int a = dots; a > 0; a--) {             // код отвечающий за направление движения
            x[a] = x[a - 1];
            y[a] = y[a - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;

        }
        if (right) {
            x[0] += DOT_SIZE;

        }
        if (back) {
            y[0] -= DOT_SIZE;

        }

        if (up) {
            y[0] += DOT_SIZE;

        }


    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();// перерисовка поля
    }

    private void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > SIZE) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }

    }


    class KeyField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode(); // опознает какая клавиша была нажата
            if (key == KeyEvent.VK_LEFT && !right) {
                up = false;
                back = false;
                left = true;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                up = false;
                back = false;
                right = true;
            }
            if (key == KeyEvent.VK_DOWN && !back) {
                up = true;
                right = false;
                left = false;
            }
            if (key == KeyEvent.VK_UP && !up) {
                right = false;
                back = true;
                left = false;
            }
            if (key == KeyEvent.VK_ESCAPE)    // пауза
            {

                timer.stop();
                pause = true;

            } else {
                timer.start();
                pause = false;
                System.out.println(pause);

            }

            if (key == KeyEvent.VK_ENTER && inGame == false) {
                timer.stop();
                inGame = true;
                up = false;
                back = false;
                right = true;
                left = false;
                newGame();
            }


        }
    }
}
