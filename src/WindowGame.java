import javax.swing.*;

/* Данный класс является окном игры */
public class WindowGame extends JFrame {  // extends - наследование
    // JFrame - это класс от, которого идет наследование любого класса,
    // который хочет быть окном
    public WindowGame() {                 // Конструктор окна

        setTitle("Упоротая змейка");     // Название окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Нажатие крестика в окне будет завершать работу программы
        setSize(530, 608); // размеры окна
        setLocation(400, 0);
        add(new Mechanics());
        setVisible(true);
    }

    public static void main(String[] args) { WindowGame mw = new WindowGame();
    }
}
