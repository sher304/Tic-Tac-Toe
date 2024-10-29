import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Game {

    int[][] board = new int[5][5];
    boolean firstPlayer = true;

    JFrame frame;
    boolean gameOver = false;
    JLabel winLabel;

    public void createUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(700, 750));

        winLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setForeground(Color.BLUE);
        frame.add(winLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = j;
                JButton button = new JButton();
                button.setName(i + ":" + j);
//                button.setText(button.getName());
                button.addActionListener(e -> gameLogic(button));
                boardPanel.add(button);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void gameLogic(JButton clickedButton) {
        if(clickedButton.getText().equals("X") || clickedButton.getText().equals("O")) return;
        if(gameOver) return;
        if (firstPlayer)  {
            firstPlayer = false;
            clickedButton.setText("X");
        }
        else {
            clickedButton.setText("O");
            firstPlayer = true;
        }
        boardMovement(clickedButton);
    }

    public void boardMovement(JButton button) {
        int getRow = Integer.parseInt(button.getName().substring(0, 1));
        int getColumn = Integer.parseInt(button.getName().substring(2)) ;
        if (button.getText().equals("X")) board[getRow][getColumn] = -1;
        else board[getRow][getColumn ] = -2;
        winValidator(board[getRow][getColumn]);
    }

    public void boardDisplay() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public void winValidator(int symbolValue) {
        if (checkHorizontally(symbolValue) || checkVertically(symbolValue)
                || checkDiagonalLeftTopRightBottom(symbolValue) || checkDiagonalLeftBottomRightTop(symbolValue)) {
            String winner = (symbolValue == -1) ? "Player X" : "Player O";
            winLabel.setText("Winner: " + winner);
            winLabel.setForeground(Color.RED);
            gameOver = true;
        }
    }

    public boolean checkHorizontally(int symbolValue) {
        for (int i = 0; i < 5; i++) {
            int valueCounter = 0;
            for(int j = 0; j < 5; j++) {
                if(board[i][j] == symbolValue) valueCounter++;
                else valueCounter = 0;
                if(valueCounter == 4)   return true;
            }
        }
        return false;
    }

    public boolean checkVertically(int symbolValue) {
        for (int i = 0; i < 5; i++) {
            int valueCounter = 0;
            for (int j = 0; j < 5; j++) {
                if(board[j][i] == symbolValue) valueCounter++;
                else valueCounter = 0;
                if(valueCounter == 4) return true;
            }
        }
        return false;
    }

    public boolean checkDiagonalLeftTopRightBottom(int symbolValue) {
        for(int i = 0; i <= 0; i++)  {
            int valueCounter = 0;
            for(int j = 0; j <= 0; j++) {
                for(int k = 0; k < 5; k++) {
                    if(board[i+k][j+k] == symbolValue) valueCounter++;
                    else valueCounter = 0;
                }
                if(valueCounter == 4) return true;
            }
        }
        return  false;
    }

    public boolean checkDiagonalLeftBottomRightTop(int symbolValue) {
        for(int i = 0; i <= 0; i++)  {
            int valueCounter = 0;
            for(int j = 4; j >= 4; j--) {
                for(int k = 0; k <= 4; k++) {
                    if(board[i+k][j-k] == symbolValue) valueCounter++;
                    else valueCounter = 0;
                }
                if (valueCounter == 4) return true;
            }
        }
        return false;
    }
}
