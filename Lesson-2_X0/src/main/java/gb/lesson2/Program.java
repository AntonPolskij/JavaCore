package gb.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;


    private static int winCount;
    private static int fieldSizeX;
    private static int fieldSizeY;

    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!")) break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!")) break;
            }
            System.out.print("Желаете сыграть ещё раз (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y")) break;
        }
    }

    static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        winCount = 4;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Отрисовка поля
     */
    private static void printField() {
        System.out.print('+');
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print((y + 1) + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Введите координаты хода X и Y (от 1 до " + fieldSizeY + " )\n через пробел:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;

    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    /**
     * Проверка состояния игры
     */
    static boolean checkGameState(char dot, String s) {
        if (checkWin(dot)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;// Игра продолжается
    }

    /**
     * Проверка на пустую ячейку
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игрового поля
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверка на ничью
     */
    static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы (old) 3*3 Only!
     */
//    static boolean checkWin(char dot) {
//        // Проверка по трем горизонталям
//        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
//        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
//        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
//        // Проверка по трем вертикалям
//        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
//        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
//        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
//        // Проверка по диагоналям
//        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
//        if (field[2][0] == dot && field[1][1] == dot && field[0][2] == dot) return true;
//
//        return false;
//    }

    /**
     * Проверка победы(new)
     */
    static boolean checkWin(char dot) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (check1(x, y, dot)) return true;
                if (check2(x, y, dot)) return true;
                if (check3(x, y, dot)) return true;
                if (check4(x, y, dot)) return true;
            }
        }
        return false;
    }

    /**
     * Горизонтальная проверка
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean check1(int x, int y, char dot) {
        for (int i = 0; i < winCount; i++) {
            if (x + i >= fieldSizeX) return false;
            if (field[y][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Вертикальная проверка
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean check2(int x, int y, char dot) {
        for (int i = 0; i < winCount; i++) {
            if (y + i >= fieldSizeY) return false;
            if (field[y + i][x] != dot) return false;
        }
        return true;
    }

    /**
     * Горизонтальная проверка вверх
     *
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean check3(int x, int y, char dot) {
        for (int i = 0; i < winCount; i++) {
            if ((y - i) < 0 || (x + i) >= fieldSizeX) return false;
            if (field[y - i][x + i] != dot) return false;
        }
        return true;
    }

    /**
     * Горизонтальная проверка вниз
     * @param x
     * @param y
     * @param dot
     * @return
     */
    static boolean check4(int x, int y, char dot) {
        for (int i = 0; i < winCount; i++) {
            if ((y + i) >= fieldSizeY || (x + i) >= fieldSizeX) return false;
            if (field[y + i][x + i] != dot) return false;
        }
        return true;
    }
}
