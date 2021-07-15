package life;
// TODO: evolve first generation for NUM_GENERATIONS times and display each generation
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static life.Grid.N;

public class GameOfLife extends JFrame {

    public static int NUM_GENERATIONS = 10;
    public JLabel generationLabel;
    public JLabel aliveLabel;
    public Grid gameGrid;

    public GameOfLife() {
        gameGrid = new Grid();
        gameGrid.buildGrid();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }

    private void init() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        JPanel textPanel = new JPanel();
        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        textPanel.add(generationLabel);
        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        textPanel.add(aliveLabel);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JPanel gridPanel = new GridCanvas(gameGrid.getCurrent());
        updateCounters(gameGrid.getGeneration(), gameGrid.getLiveCells());
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private class GridCanvas extends JPanel {

        public GridCanvas(Cell[][] cells) {
            setLayout(new GridLayout(N, N));
            GridBagConstraints gbc = new GridBagConstraints();

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;
                    JPanel cell = new JPanel();
                    Border border;
                    if (row < (N - 1)) {
                        if (col < (N - 1)) {
                            border = new MatteBorder(1, 1, 0, 0, Color.gray);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.gray);
                        }
                    } else {
                        if (col < (N - 1)) {
                            border = new MatteBorder(1, 1, 1, 0, Color.gray);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.gray);
                        }
                    }
                    if (cells[row][col].isAlive()) {
                        cell.setBackground(Color.darkGray);
                    }
                    cell.setBorder(border);
                    add(cell, gbc);
                }
            }
        }
    }

    public void updateGrid() {
        /*

         */

    }

    public void updateCounters(int generation, int liveCells) {
        generationLabel.setText("Generation: #" + generation);
        aliveLabel.setText("Alive: " + liveCells);
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        /*
            - create thread for logic (build initial grid,
             get next generations, etc)
            -
         */
    }
}
