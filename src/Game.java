import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class Game {

    int[][] board = new int[5][5];
    boolean firstPlayer = true;

    int xSteps = 0;
    int oSteps = 0;

    int gameCounter = 1;

    public void createUI() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new GridLayout(5, 5, 10, 10));
        frame.setPreferredSize(new Dimension(700, 700));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = j;
                JButton button = new JButton();
                button.setName(i+":"+j);
                button.setText(button.getName());
                button.addActionListener(e -> {
                    gameLogic(button);
                });
                frame.add(button);
            }
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void gameLogic(JButton clickedButton) {
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
        int getColumn = Integer.parseInt(button.getName().substring(2)) ;;
        if (button.getText().equals("X")) board[getRow][getColumn] = -1;
        else board[getRow][getColumn ] = -2;
        winValidator(board[getRow][getColumn]);
    }

    public void boardDisplay() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public void winValidator(int button) {
        checkHorizontally(button);
        checkVertically(button);
    }

    public boolean checkHorizontally(int symbolValue) {
        for (int i = 0; i < 5; i++) {
            int valueCounter = 0;
            for(int j = 0; j < 5; j++) {
                if(board[i][j] == symbolValue) valueCounter++;
                else valueCounter = 0;

                if(valueCounter == 5) return true;
            }
        }
        return false;
    }

    public boolean checkVertically(int button) {
        for (int i = 0; i < 5; i++) {
            int valueCounter = 0;
            for (int j = 0; j < 5; j++) {
                if(board[j][i] == button) valueCounter++;
                else valueCounter = 0;
                if(valueCounter == 5) return true;
            }
        }
    return false;
    };

    public boolean checkDiagonal(int button) {
        for(int i = 0; i <= 5 -5; i++)  {
            int valueCounter = 0;
            for(int j = 0; j <=5 -5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(board[i+k][j+k] == button) valueCounter++;
                    else valueCounter = 0;
                }
                if(valueCounter == 5) return true;
            }
        }
        return  false;
    };
}
