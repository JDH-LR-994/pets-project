public class TicTacToe {
    char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    boolean status = true;

    static class IllegalArgumentException extends Exception {
        public IllegalArgumentException(String e) {
            super(e);
        }
    }

    void showBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    System.out.print("| \u001B[31m" + board[i][j] + "\u001B[0m ");
                } else if (board[i][j] == 'O') {
                    System.out.print("| \u001B[34m" + board[i][j] + "\u001B[0m ");
                } else {
                    System.out.print("| " + board[i][j] + " ");
                }
            }
            System.out.println("|");
        }
    }

    boolean checkWinner(char token) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == token && board[i][1] == token && board[i][2] == token) {
                status = false;
                return true;
            }
            if (board[0][i] == token && board[1][i] == token && board[2][i] == token) {
                status = false;
                return true;
            }
        }
        if (board[0][0] == token && board[1][1] == token && board[2][2] == token) {
            status = false;
            return true;
        }
        if (board[0][2] == token && board[1][1] == token && board[2][0] == token) {
            status = false;
            return true;
        }
        return false;
    }

    void playerMove(int pos, char token) throws IllegalArgumentException {
        if (pos < 1 || pos > 9) {
            throw new IllegalArgumentException("Выбрана несуществующая ячейка на доске");
        }
        if (board[(pos - 1) / 3][(pos - 1) % 3] != Character.forDigit(pos, 10)) {
            throw new IllegalArgumentException("Эта ячейка уже занята!");
        }
        board[(pos - 1) / 3][(pos - 1) % 3] = token;
    }

    boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}

