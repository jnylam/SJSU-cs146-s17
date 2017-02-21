package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NQueens {
    List<List<Integer>> solutions;
    int boardSize;

    public NQueens(int boardSize) {
        this.solutions = new ArrayList<>();
        this.boardSize = boardSize;
        solve(new Stack<>());
    }

    private void solve(Stack<Integer> partialSolution) {

    }

    public List<List<Integer>> solutions() {
        return solutions;
    }

    public static String toString(List<Integer> board) {
        String result = "";
        for (int i = 0; i < board.size(); i++)
            result += rowToString(board.get(i), board.size()) + "\n";
        return result;
    }

    public static String rowToString(int queenPosition, int boardSize) {
        String result = "|";
        for (int j = 0; j < queenPosition; j++)
            result += " |";
        result += "Q|";
        for (int j = queenPosition+1; j < boardSize; j++)
            result += " |";
        return result;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens(4);
        for (List<Integer> sol : nQueens.solutions())
            System.out.println(toString(sol));
    }
}
