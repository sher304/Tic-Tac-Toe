import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    static {
        System.loadLibrary("utpFirstProject");
    }

    private native int[][] getBoard();
    private native void makeMovement(int row, int col, int player);
    private native boolean gameOver();
    private native int getNextPlayer();
    private native int getDimension(String name, boolean isRow);
    private native String getPlayerValue(int playerValue);
    private native void setBoardSize(int size);
    private native int gameKeyReader(int code);
    private native void placeMark(int player);
    private native int getCursorRow();
    private native int getCursorColumn();
    private native void moveCursor(int direction);

    public void showMenu() {
        JFrame menuFrame = new JFrame("Tic Tac Toe Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 300);
        menuFrame.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titleLabel = new JLabel("Choose Game Size", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuFrame.add(titleLabel);

        JButton button5x5 = new JButton("5x5 Game");
        button5x5.setFont(new Font("Arial", Font.PLAIN, 18));
        button5x5.addActionListener(e -> {
            setBoardSize(5);
            menuFrame.dispose();
            createUI(5);
        });
        menuFrame.add(button5x5);

        JButton button7x7 = new JButton("7x7 Game");
        button7x7.setFont(new Font("Arial", Font.PLAIN, 18));
        button7x7.addActionListener(e -> {
            setBoardSize(7);
            menuFrame.dispose();
            createUI(7);
        });
        menuFrame.add(button7x7);

        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    public void createUI(int size) {
        JFrame frame = new JFrame("Tic Tac Toe - " + size + "x" + size);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(700, 750));

        JLabel winLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setForeground(Color.BLUE);
        frame.add(winLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(size, size, 10, 10));
        frame.add(boardPanel, BorderLayout.CENTER);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setName(i + ":" + j);
                button.setFocusable(false);
                button.setOpaque(true);

                button.addActionListener(e -> handleButtonClick(button, winLabel, frame, size));
                boardPanel.add(button);
            }
        }
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int result = gameKeyReader(e.getKeyCode());
                if (result == 1) {
                    frame.dispose();
                    setBoardSize(size);
                    createUI(size);
                } else if (result == -1) {
                    frame.dispose();
                    showMenu();
                } else {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            moveCursor(0);
                            break;
                        case KeyEvent.VK_DOWN:
                            moveCursor(1);
                            break;
                        case KeyEvent.VK_LEFT:
                            moveCursor(2);
                            break;
                        case KeyEvent.VK_RIGHT:
                            moveCursor(3);
                            break;
                        case KeyEvent.VK_ENTER:
                            placeMark(getNextPlayer());
                            if (gameOver()) {
                                String text = getPlayerValue(getNextPlayer());
                                winLabel.setText("Game Over - Winner: " + text + " | Press R to Restart or Q for Menu");
                            }
                            break;
                    }
                    updateUI(frame, size);
                }
            }
        });

        frame.setFocusable(true);
        frame.requestFocusInWindow();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    };

    private void handleButtonClick(JButton button,
                                   JLabel winLabel,
                                   JFrame frame, int boardSize) {
        if (gameOver()) return;

        int row = getDimension(button.getName(), true);
        int column = getDimension(button.getName(), false);
        int currentPlayer = getNextPlayer();
        makeMovement(row, column, currentPlayer);

        updateUI(frame, boardSize);

        if (gameOver()) {
            String text = getPlayerValue(currentPlayer);
            winLabel.setText("Game Over - Winner: " + text + " | Press R to Restart or Q for Menu");
        }
    }

    public void updateUI(JFrame frame, int size) {
        int[][] boardState = getBoard();
        Component[] components = ((JPanel) frame.getContentPane().getComponent(1)).getComponents();
        int cursorRow = getCursorRow();
        int cursorCol = getCursorColumn();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = (JButton) components[i * size + j];
                String text = getPlayerValue(boardState[i][j]);
                button.setText(text);

                if (i == cursorRow && j == cursorCol) {
                    button.setBackground(Color.YELLOW);
                } else {
                    button.setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.showMenu();
        });
    }
}