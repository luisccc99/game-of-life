package life;

import java.util.Arrays;
import java.util.Random;

public class Grid {

    private Cell[][] prev;
    private Cell[][] current;
    private final Random random;
    public static final int N = 20;
    private int liveCells = 0;
    private int generation = 1;

    public Grid() {
        random = new Random(System.currentTimeMillis());
        current = new Cell[N][N];
        prev = new Cell[N][N];
    }

    public void buildGrid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean alive = random.nextBoolean();
                current[i][j] = new Cell(alive);
            }
        }
    }

    public void nextGeneration() {
        prev = Arrays.copyOf(current, N);
        current = new Cell[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checkNeighbors(i, j);
                int numberOfNeighbors = prev[i][j].getNeighbors();
                boolean alive = false;
                if (numberOfNeighbors == 3) {
                    alive = true;
                } else if (prev[i][j].isAlive() && numberOfNeighbors == 2) {
                    alive = true;
                }
                current[i][j] = new Cell(alive);
            }
        }
    }

    private void checkNeighbors(int row, int col) {
        Cell cell = prev[row][col];
        if (isTopLeftCorner(row, col)) {
            addNeighborIfIsAlive(cell, row, N - 1); // W
            addNeighborIfIsAlive(cell, N - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, N - 1, col); // N
            addNeighborIfIsAlive(cell, N - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, N - 1); // SW
        } else if (isTopRightCorner(row, col)) {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, N - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, N - 1, col); // N
            addNeighborIfIsAlive(cell, N - 1, 0); // NE
            addNeighborIfIsAlive(cell, row, 0); // E
            addNeighborIfIsAlive(cell, row + 1, 0); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, col - 1); // SW
        } else if (isBottomLeftCorner(row, col)) {
            addNeighborIfIsAlive(cell, row, N - 1); // W
            addNeighborIfIsAlive(cell, row - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, 0, col + 1); // SE
            addNeighborIfIsAlive(cell, 0, col); // S
            addNeighborIfIsAlive(cell, 0, N - 1); // SW
        } else if (isBottomRightCorner(row, col)) {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, 0); // NE
            addNeighborIfIsAlive(cell, row, 0); // E
            addNeighborIfIsAlive(cell, 0, 0); // SE
            addNeighborIfIsAlive(cell, 0, col); // S
            addNeighborIfIsAlive(cell, 0, col - 1); // SW
        } else if (isLeftEdge(row, col)) {
            addNeighborIfIsAlive(cell, row, N - 1); // W
            addNeighborIfIsAlive(cell, row - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, N - 1); // SW
        } else if (isRightEdge(row, col)) {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, 0); // NE
            addNeighborIfIsAlive(cell, row, 0); // E
            addNeighborIfIsAlive(cell, row + 1, 0); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, col - 1); // SW
        } else if (isTopEdge(row, col)) {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, N - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, N - 1, col); // N
            addNeighborIfIsAlive(cell, N - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, col - 1); // SW
        } else if (isBottomEdge(row, col)) {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, 0, col + 1); // SE
            addNeighborIfIsAlive(cell, 0, col); // S
            addNeighborIfIsAlive(cell, 0, col - 1); // SW
        } else {
            addNeighborIfIsAlive(cell, row, col - 1); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // N
            addNeighborIfIsAlive(cell, row - 1, col + 1); // NE
            addNeighborIfIsAlive(cell, row, col + 1); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row + 1, col); // S
            addNeighborIfIsAlive(cell, row + 1, col - 1); // SW
        }
    }

    private void addNeighborIfIsAlive(Cell cell, int row, int col) {
        Cell neighbor = prev[row][col];
        if (neighbor.isAlive()) {
            cell.addNeighbor();
        }
    }

    private boolean isTopLeftCorner(int row, int col) {
        return col == 0 && row == 0;
    }

    private boolean isTopRightCorner(int row, int col) {
        return row == 0 && (col == N - 1);
    }

    private boolean isBottomLeftCorner(int row, int col) {
        return (row == N - 1) && col == 0;
    }

    private boolean isBottomRightCorner(int row, int col) {
        return (col == N - 1) && (row == N - 1);
    }

    private boolean isLeftEdge(int row, int col) {
        return col == 0 && (row > 0 && row < N - 1);
    }

    private boolean isRightEdge(int row, int col) {
        return col == N - 1 && (row > 0 && row < N - 1);
    }

    private boolean isTopEdge(int row, int col) {
        return (col > 0 && col < N - 1) && row == 0;
    }

    private boolean isBottomEdge(int row, int col) {
        return (col > 0 && col < N - 1) && row == N - 1;
    }

    public void displayGrid() {
        for (Cell[] row : current) {
            for (Cell cell : row) {
                System.out.print(cell.isAlive() ? "O" : " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getLiveCells() {
        liveCells = 0;
        for (Cell[] row : current) {
            for (Cell cell : row) {
                liveCells += cell.isAlive() ? 1 : 0;
            }
        }
        return liveCells;
    }

    public int getGeneration() {
        return generation;
    }

    public Cell[][] getCurrent() {
        return current;
    }
}
