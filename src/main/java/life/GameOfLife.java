package life;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static life.Grid.N;

public class GameOfLife extends JFrame {

    public static int NUM_GENERATIONS = 100;
    public JLabel generationLabel;
    public JLabel aliveLabel;
    private GridCanvas gridPanel;
    public Grid gameGrid;

    public GameOfLife() {
        super("Game of Life");
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
        gridPanel = new GridCanvas();
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    public void setGameGrid(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    private class GridCanvas extends JPanel {

        public GridCanvas() {
            setLayout(new GridLayout(N, N));
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            removeAll();
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    JPanel cell = new JPanel();
                    Border border;
                    if (row < (N - 1)) {
                        if (col < (N - 1)) {
                            border = new MatteBorder(1, 1, 0, 0, Color.darkGray);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.darkGray);
                        }
                    } else {
                        if (col < (N - 1)) {
                            border = new MatteBorder(1, 1, 1, 0, Color.darkGray);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.darkGray);
                        }
                    }
                    if (gameGrid.getCurrent()[row][col].isAlive()) {
                        cell.setBackground(Color.darkGray);
                    }
                    cell.setBorder(border);
                    add(cell);
                }
            }
        }
    }

    public void updateGrid() {
        gridPanel.repaint();
    }

    public void updateCounters(int generation, int liveCells) {
        generationLabel.setText("Generation: #" + generation);
        aliveLabel.setText("Alive: " + liveCells);
    }

    public static void main(String[] args) {
        Thread game = new Thread(() -> {
            Grid grid = new Grid();
            grid.buildGrid();
            GameOfLife life = new GameOfLife();
            life.updateGrid();
            life.setGameGrid(grid);
            for (int i = 0; i < NUM_GENERATIONS; i++) {
                life.updateGrid();
                life.updateCounters(i + 1, grid.getLiveCells());
                grid.nextGeneration();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        game.start();
        try {
            game.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
