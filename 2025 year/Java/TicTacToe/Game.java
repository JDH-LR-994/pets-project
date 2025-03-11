import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int gameMode = 3;
        do {
            System.out.println("Выберите режим:");
            System.out.println("1. Играть с алгоритмом");
            System.out.println("2. Играть с другом");
            System.out.print("Ваш выбор: ");
            try {
                gameMode = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод");
            }
            System.out.println("");
        } while (gameMode < 1 || gameMode > 2);

        switch (gameMode) {
            case 1:
                // Реализация игры с алгоритмом
                break;
            case 2:
                TicTacToe game = new TicTacToe();
                int counter = 0;
                int pos;
                while (game.status) {
                    char[] token = {'O', 'X'};
                    game.showBoard();
                    System.out.println("Введите позицию для своего хода (1-9):");
                    try {
                        pos = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели даже не число!!");
                        continue;
                    }

                    try {
                        game.playerMove(pos, token[counter % 2]);
                        if (game.checkWinner(token[counter % 2])) {
                            System.out.println("Победил игрок: " + token[counter % 2]);
                            game.status = false;
                        } else if (game.isBoardFull()) {
                            System.out.println("Ничья!");
                            game.status = false;
                        }
                        counter++;
                    } catch (TicTacToe.IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
        }
    }
}