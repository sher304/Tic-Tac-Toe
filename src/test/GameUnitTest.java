package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameUnitTest {

    static  {
        System.loadLibrary("utpFirstProject");
    }

    private native int[][] getBoard();
    private native void setBoardSize(int size);
    private native void makeMovement(int row, int col, int player);
    private native boolean gameOver();
    private native int getNextPlayer();
    private native int gameKeyReader(int keyCode);

    @Test
    public void checkBoardCorrectInitialization() {
        setBoardSize(5); // Setting default board size

        int[][] board = getBoard();
        assertNotNull(board, "Board is not null, after initialising it");
        assertEquals(5, board.length, "Board size should be equal to setted size");

        for (int i = 0; i < board.length; i++) {
            assertEquals(5, board[i].length, "Every row has 5 columns");
            for (int j = 0; j < board[i].length; j++) {
                assertEquals(0, board[i][j], "All cells are 0");
            }
        }
    }

    @Test
    public void checkValidMove() {
        int initialPlayer = getNextPlayer();
        makeMovement(2, 2, initialPlayer); // Place a move in the center

        int[][] board = getBoard();
        assertEquals(initialPlayer, board[2][2]);

        int nextPlayer = getNextPlayer();
        assertNotEquals(initialPlayer, nextPlayer);
    }

    @Test
    public void checkCorrectMovements() {
        setBoardSize(5);
        int xPLayer = 1;
        int oPLayer = 2;

        makeMovement(1, 1, xPLayer);
        int[][] board = getBoard();
        assertEquals(xPLayer, board[1][1]);

        makeMovement(1, 2, oPLayer);
        board = getBoard();
        assertEquals(oPLayer, board[1][2]);
    }

    @Test
    public void correctGameOverValidation() {
        int player = getNextPlayer();
        makeMovement(0, 1, player);
        makeMovement(0, 2, player);
        makeMovement(0, 3, player);
        makeMovement(0, 4, player);
        assertTrue(gameOver());
    }
}
