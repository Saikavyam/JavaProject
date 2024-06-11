import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeApp extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true; // Player X starts

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeApp app = new TicTacToeApp();
            app.setVisible(true);
        });
    }

    public TicTacToeApp() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = createBoard();
        add(panel);
    }

    private JPanel createBoard() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 60));
                button.setFocusPainted(false);
                button.addActionListener(new ButtonClickListener(row, col));
                buttons[row][col] = button;
                panel.add(button);
            }
        }

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!buttons[row][col].getText().equals("")) {
                return; // Ignore clicks on already filled buttons
            }

            buttons[row][col].setText(playerX ? "X" : "O");
            playerX = !playerX;

            checkWinner();
        }
    }

    private void checkWinner() {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && 
                buttons[i][1].getText().equals(buttons[i][2].getText()) && 
                !buttons[i][0].getText().isEmpty()) {
                showWinner(buttons[i][0].getText());
                return;
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && 
                buttons[1][i].getText().equals(buttons[2][i].getText()) && 
                !buttons[0][i].getText().isEmpty()) {
                showWinner(buttons[0][i].getText());
                return;
            }
        }

        // Diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && 
            buttons[1][1].getText().equals(buttons[2][2].getText()) && 
            !buttons[0][0].getText().isEmpty()) {
            showWinner(buttons[0][0].getText());
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && 
            buttons[1][1].getText().equals(buttons[2][0].getText()) && 
            !buttons[0][2].getText().isEmpty()) {
            showWinner(buttons[0][2].getText());
            return;
        }

        // Check for a draw
        boolean draw = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            showWinner("Draw");
        }
    }

    private void showWinner(String winner) {
        String message;
        if (winner.equals("Draw")) {
            message = "The game is a draw!";
        } else {
            message = "The winner is " + winner + "!";
        }

        JOptionPane.showMessageDialog(this, message);
        resetBoard();
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        playerX = true; // X starts the new game
    }
}
