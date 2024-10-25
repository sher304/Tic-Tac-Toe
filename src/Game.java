import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;


public class Game {

    int[][] board = new int[5][5];
    boolean firstPlayer = true;
    Dictionary<String, int[]> xCoordinates = new Hashtable<>();
    Dictionary<String, int[]> oCoordinates = new Hashtable<>();

    public void createUI() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new GridLayout(5, 5, 10, 10));
        frame.setPreferredSize(new Dimension(700, 700));
        int buttonId = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = buttonId;
                JButton button = new JButton();
                button.setName(i+":"+j);
                buttonId++;
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
//        if (clickedButton.getText().equals("")) return;
        if (firstPlayer)  {
            firstPlayer = false;
            clickedButton.setText("X");
        }
        else {
            clickedButton.setText("O");
            firstPlayer = true;
        }
        movementLogic(clickedButton);
    }

    public void movementLogic(JButton clickedButton) {
        boardMovement(clickedButton);
    }

    public void boardMovement(JButton button) {
        int getRow = Integer.parseInt(button.getName().substring(0, 1));
        int getColumn = Integer.parseInt(button.getName().substring(2)) ;;
        System.out.println("Text: " + button.getText());
        if (button.getText().equals("X")) board[getRow][getColumn] = -1;
        else board[getRow][getColumn ] = -2;
    }

    public void boardDisplay() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    // After implementing the logic of passing the values.
    // I need to check the possible winning combinations
    // For example if i pasted in a row, i need to check if there are row consists of 5 elements, and doesn't been passed other value
    // And i need to check by horizontally, vertically
    // After all them, i need to check them by cross
    // I can stop the checking if there is passed other value
}
