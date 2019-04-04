import java.util.Random;
import java.util.Scanner;

public class lesson4 {
    static final int SIZE = 5;
    static final int DOTS_TO_WIN = 4;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '*';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {

            humanTurn();
            printMap();
            if (chekWin(DOT_X)) {
                System.out.println("Игрок победил!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printMap();
            if (chekWin(DOT_O)) {
                System.out.println("Компьютер победил!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
        }


    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }

        }

    }

    public static void printMap() {
        System.out.print(" ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите кординаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;

        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;

    }

    public static void aiTurn() {
        int x = -1, y = -1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(j, i)) {
                    map[i][j] = DOT_X;
                    if (chekWin(DOT_X)) {
                        x = j;
                        y = i;
                    }
                    map[i][j] = DOT_EMPTY;
                }

            }

        }
        if (x == -1 && y == -1) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);

            } while (!isCellValid(x, y));
        }


        map[y][x] = DOT_O;

    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;

    }

    public static boolean chekWin(char symbol) {
//        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
//        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
//        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;
//
//        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
//        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
//        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;
//
//        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
//        if (map[2][0] == symbol && map[1][1] == symbol && map[0][2] == symbol) return true;
//
//        return false;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chekGorizontal(i, j, symbol) || chekVertical(i, j, symbol) || chekDiagonal1(i, j, symbol)
                        || chekDiagonal2(i, j, symbol)) {
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean chekGorizontal(int x, int y, char symbol) {
        if (x < 0 || y < 0 || x + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[i + x][y] == symbol) {
                k++;
            }

        }
        return k == DOTS_TO_WIN;
    }

    public static boolean chekVertical(int x, int y, char symbol) {
        if (x < 0 || y < 0 || y + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x][y + i] == symbol) {
                k++;
            }

        }
        return k == DOTS_TO_WIN;
    }


    public static boolean chekDiagonal1(int x, int y, char symbol) {
        if (x < 0 || y < 0 || x + DOTS_TO_WIN > SIZE || y + DOTS_TO_WIN > SIZE) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x + i][y + i] == symbol) {
                k++;
            }

        }
        return k == DOTS_TO_WIN;
    }

    public static boolean chekDiagonal2(int x, int y, char symbol) {
        if (x < 0 || x + DOTS_TO_WIN > SIZE || y + 1 - DOTS_TO_WIN < 0) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[x + i][y - i] == symbol) {
                k++;
            }

        }
        return k == DOTS_TO_WIN;
    }

    public static boolean isFull() {
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    k++;
                }

            }

        }
        return k == 0;
    }


}
