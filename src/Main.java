import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void createUI() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(700, 750));

        JLabel winLabel = new JLabel("Tic Tac Toe", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winLabel.setForeground(Color.BLUE);
        frame.add(winLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(5, 5, 10, 10));

        int[][] boardState = getBoard();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setName(i + ":" + j);

                String text = getPlayerValue(boardState[i][j]);
                button.setText(text);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(button, winLabel, frame);
                    }
                });

                boardPanel.add(button);
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void handleButtonClick(JButton button, JLabel winLabel, JFrame frame) {
        if (gameOver()) return;

        int row = getDimension(button.getName(), true);
        int column = getDimension(button.getName(), false);
        int currentPlayer = getNextPlayer();
        makeMovement(row, column, currentPlayer);

        updateUI(frame);

        if (gameOver()) {
            winLabel.setText("Game Over - Winner: " + (currentPlayer == 1 ? "X" : "O"));
        }
    }

    public void updateUI(JFrame frame) {
        int[][] boardState = getBoard();
        Component[] components = ((JPanel) frame.getContentPane().getComponent(1)).getComponents();

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                JButton button = (JButton) components[i * 5 + j];
                String text = getPlayerValue(boardState[i][j]);
                button.setText(text);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.createUI();
        });
    }
}