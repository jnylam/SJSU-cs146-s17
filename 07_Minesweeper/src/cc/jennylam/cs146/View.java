package cc.jennylam.cs146;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;


public class View extends Application {

    private static final Color WHITE = Color.WHITE;
    private static final Color GRAY = Color.DARKGRAY;
    private static final Color BLACK = new Color(.3, .3, .3, 1);
    private static final Color COVERED_COLOR = Color.DARKSLATEBLUE;
    private static final Color UNCOVERED_COLOR = Color.ANTIQUEWHITE;
    private static final Color MINE_COLOR = Color.DARKMAGENTA;
    private static final Color FLAG_COLOR = Color.DARKRED;

    private final int cellSize;
    private final MineSweeper game;
    private final Set<Integer> flags;
    private final Canvas canvas;

    public View() {
        int width = 20;
        int height = 20;
        cellSize = 30;
        game = new MineSweeper(width, height, 20);
        flags = new HashSet<>();
        canvas = new Canvas(width*cellSize, height*cellSize);
        canvas.setOnMouseClicked (this::update);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, canvas.getWidth(), canvas.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeper");
        primaryStage.show();
        drawScreen();
    }

    private void update(MouseEvent event) {
        if (!game.isRunning())
            return;

        // get cell
        int row = Math.toIntExact(Math.round(Math.floor(event.getY() / cellSize)));
        int col = Math.toIntExact(Math.round(Math.floor(event.getX() / cellSize)));
        int cell = row * game.getBoard().getWidth() + col;

        // update state at given cell
        if (event.getButton() == MouseButton.SECONDARY)
            toggleFlag(cell);
        else
            game.update(cell);

        drawScreen();
    }

    private void toggleFlag(int cell) {
        if (flags.contains(cell))
            flags.remove(cell);
        else
            flags.add(cell);
    }

    private void drawScreen() {
        // draw board cells
        int[][] board = game.isRunning() ? game.getBoard().getCoveredView() : game.getBoard().getClearedView();
        for (int i = 0; i < game.getBoard().getHeight(); i++)
            for (int j = 0; j < game.getBoard().getWidth(); j++)
                drawCell(i, j, board[i][j]);

        // draw flags
        if (game.isRunning()) {
            int w = game.getBoard().getWidth();
            for (int c : flags) {
                int i = c / w;
                int j = c % w;
                if (board[i][j] == Board.COVERED)
                    drawFlag(i, j);
            }
        }

        // draw grid lines
        for (int i = 1; i < game.getBoard().getHeight(); i++)
            drawHorizontalLine(i);
        for (int j = 1; j < game.getBoard().getWidth(); j++)
            drawVerticalLine(j);


        if (!game.isRunning())
            drawMessage(game.isWon());
    }

    private void drawCell(int row, int col, int value) {
        int x = col*cellSize;
        int y = row*cellSize;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (value == Board.COVERED) {
            gc.setFill(COVERED_COLOR);
            gc.fillRect(x, y,cellSize, cellSize);
        } else if (value == Board.MINE) {
            gc.setFill(MINE_COLOR);
            gc.fillRect(x, y,cellSize, cellSize);
        } else if (0 < value && value < 9){
            gc.setFill(UNCOVERED_COLOR);
            gc.fillRect(x, y, cellSize, cellSize);
            gc.setStroke(GRAY);
            gc.strokeText(String.valueOf(value), x+cellSize/3,y+2*cellSize/3);
        } else {
            gc.setFill(UNCOVERED_COLOR);
            gc.fillRect(x, y, cellSize, cellSize);
        }
    }

    private void drawFlag(int row, int col) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double x = col*cellSize + cellSize/2;
        double y = row*cellSize + cellSize/2;
        double w = cellSize/6;
        double[] xPoints = { x-w, x-w, x+w };
        double[] yPoints = { y-w, y+w, y };
        gc.setFill(FLAG_COLOR);
        gc.setStroke(WHITE);
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.strokePolygon(xPoints, yPoints, 3);
    }

    private void drawHorizontalLine(int row) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double x1 = 0;
        double x2 = canvas.getWidth();
        double y = row*cellSize;
        gc.setStroke(GRAY);
        gc.strokeLine(x1, y, x2, y);
    }

    private void drawVerticalLine(int col) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double x = col*cellSize;
        double y1 = 0;
        double y2 = canvas.getHeight();
        gc.setStroke(GRAY);
        gc.strokeLine(x, y1, x, y2);
    }

   private void drawMessage(boolean won) {
        String message = won ? "You win!" : "You lose!";
        double w = 2.5*cellSize;
        double pad = cellSize/3;
        double x = (canvas.getWidth()-w)/2;
        double y = canvas.getHeight()/2;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(WHITE);
        gc.setStroke(BLACK);
        gc.fillRoundRect(x-pad, y-cellSize+pad, w, cellSize, pad, pad);
        gc.strokeRoundRect(x-pad, y-cellSize+pad, w, cellSize, pad, pad);
        gc.strokeText(message, x, y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
