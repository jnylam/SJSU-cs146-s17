package cc.jennylam.cs146;

import java.util.*;

class Board {
    static int MINE = 9;
    static int COVERED = 10;

    private int width;
    private int height;
    // cell c at row i and column j is an integer c = i*width + height
    private Set<Integer> mines;   // cells c containing mines
    private int[] mineField;      // cell c has a mine if mineField[c] == MINE
                                  // otherwise, c has no mines but is adjacent to mineField[c]-many mines
    private boolean[] sweptCells; // initially all entries have a value of false
                                  // true represents a cell without a mine having been cleared or swept
                                  // value of cell d changes on a call to sweep(c) if d is in the same "region"
                                  // as c. Two cells without mines belong to the same region if there is a path
                                  // of cells without mines that connects c to d.

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    Board(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = generateMines(mines, height*width);
        mineField = makeField();
        sweptCells = new boolean[height*width];
    }

    boolean allClear() {
        int unclear = height*width;
        for (boolean swept : sweptCells)
            if (swept)
                unclear--;
        return unclear <= mines.size();
    }

    boolean clear(int row, int col) {
        return clear(row*width + col);
    }

    boolean clear(Integer c) {
        if (mines.contains(c))
            return false;
        sweep(c);
        return true;
    }

    private void sweep(Integer c) {
        sweptCells[c] = true;
        // TODO: implement sweeping by clearing all the cells in the same region as c.
        // Hint: use a BFS traversal: clear all adjacent cells of c that do not contain a mine
        // for each cleared adjacent cell, repeat the sweeping procedure to clear its neighbors, and so on.
    }

    private static Set<Integer> generateMines(int mines, int range) {
        if (mines >= range)
            throw new AssertionError("too many mines for this size");
        Random r = new Random();
        Set<Integer> mineLocations = new HashSet<>();
        for (int k = 0; k < mines; k++) {
            int c = r.nextInt(range);
            while (mineLocations.contains(c))
                c = r.nextInt(range);
            mineLocations.add(c);
        }
        return mineLocations;
    }

    private int[] makeField() {
        int[] mineField = new int[width*height];
        for (int c : mines)
            mineField[c] = MINE;
        for (int c : mines)
            for (int a : adjacentCells(c))
                if (mineField[a] != MINE)
                    mineField[a] += 1;
        return mineField;
    }

    private Set<Integer> adjacentCells(int c) {
        int i = c/width;
        int j = c%width;
        Set<Integer> cells = new HashSet<>();
        for (int ii = Integer.max(0, i - 1); ii < Integer.min(height, i + 2); ii++) {
            for (int jj = Integer.max(0, j - 1); jj < Integer.min(width, j + 2); jj++) {
                cells.add(ii*width+jj);
            }
        }
        cells.remove(i*width + j);
        return cells;
    }

    int[][] getClearedView() {
        return reshape(mineField, width);
    }

    int[][] getCoveredView() {
        int[] coverField = cover(mineField, sweptCells);
        return reshape(coverField, width);
    }

    private static int[] cover(int[] board, boolean[] sweptCells) {
        int[] covered = Arrays.copyOf(board, board.length);
        for (int c = 0; c < board.length; c++)
            if (!sweptCells[c])
                covered[c] = COVERED;
        return covered;
    }

    private static int[][] reshape(int[] v, int n) {
        int[][] m = new int[v.length/n][n];
        for (int i = 0; i < v.length/n; i++)
            System.arraycopy(v, i*n, m[i], 0, n);
        return m;
    }
}
