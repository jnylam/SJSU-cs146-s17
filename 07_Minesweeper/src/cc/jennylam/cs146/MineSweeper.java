package cc.jennylam.cs146;

class MineSweeper {
    private Board board;
    private boolean running;
    private boolean won;

    MineSweeper(int width, int height, int mines) {
        board = new Board(width, height, mines);
        running = true;
        won = false;
    }

    Board getBoard() {
        return board;
    }

    boolean isRunning() {
        return running;
    }

    boolean isWon() {
        return won;
    }

    void update(int c) {
        if (running)
            updateBoard(c);
    }

    private void updateBoard(int c) {
        if (!board.clear(c)) {
            running = false;
        } else if (board.allClear()) {
            running = false;
            won = true;
        }
    }
}
