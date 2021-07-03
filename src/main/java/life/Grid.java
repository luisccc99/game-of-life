package life;

import java.util.Random;

public class Grid {

    private final Cell[][] prev;
    private final Cell[][] current;
    private final Random random;
    private final int N;
    private int numberOfCellsAlive;

    public Grid(int n, long seed) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0");
        }
        N = n;
        current = new Cell[n][n];
        prev = new Cell[n][n];
        random = new Random(seed);
    }

    public void buildGrid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean alive = random.nextBoolean();
                current[i][j] = new Cell(alive);
                if (alive) {
                    numberOfCellsAlive++;
                }
            }
        }
    }

    public void nextGeneration() {
        System.arraycopy(current, 0, prev, 0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checkNeighbors(i, j);
			    System.out.println();	
                if (prev[i][j].getNeighbors() > 3 || prev[i][j].getNeighbors() < 2) {
                    current[i][j].setAlive(false);
                } else if (prev[i][j].getNeighbors() == 3 && !prev[i][j].isAlive()) {
                    System.out.println("back to life bebe [" + i + "," + j +"]");
                    current[i][j].setAlive(true);
                }
            }
        }
    }

    private void checkNeighbors(int row, int col) {
        Cell cell = prev[row][col];
        if (isTopLeftCorner(row, col)) {
            addNeighborIfIsAlive(cell, N - 1, col); // W
            addNeighborIfIsAlive(cell, N - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, row, N - 1); // N
            addNeighborIfIsAlive(cell, row + 1, N - 1); // NE
            addNeighborIfIsAlive(cell, (row + 1), col); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, N - 1, col); // SW
        } else if (isTopRightCorner(row, col)) {
            addNeighborIfIsAlive(cell, row - 1, col); // W
            addNeighborIfIsAlive(cell, row - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, row, N - 1); // N
            addNeighborIfIsAlive(cell, 0, N - 1); // NE
            addNeighborIfIsAlive(cell,0, 0); // E
            addNeighborIfIsAlive(cell, 0, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, row - 1, col); // SW
        } else if (isBottomLeftCorner(row, col)) {
            addNeighborIfIsAlive(cell, N - 1, col); // W
            addNeighborIfIsAlive(cell, N - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, row + 1, col - 1); // NE
            addNeighborIfIsAlive(cell, row + 1, col); // E
            addNeighborIfIsAlive(cell, row + 1, 0); // SE
            addNeighborIfIsAlive(cell, row, 0); // S
            addNeighborIfIsAlive(cell, N - 1, 0); // SW
        } else if (isBottomRightCorner(row, col)) {
            addNeighborIfIsAlive(cell, row - 1, N - 1); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, 0, col - 1); // NE
            addNeighborIfIsAlive(cell, 0, col); // E
            addNeighborIfIsAlive(cell, 0, 0); // SE
            addNeighborIfIsAlive(cell, row, 0); // S
            addNeighborIfIsAlive(cell, row - 1, 0); // SW
        } else if (isLeftEdge(row, col)) {
            addNeighborIfIsAlive(cell, N - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, N - 1, col); // W
            addNeighborIfIsAlive(cell, row + 1, col - 1); // NE
            addNeighborIfIsAlive(cell, row + 1, col); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, N - 1, col + 1); // SW
        } else if (isRightEdge(row, col)) {
            addNeighborIfIsAlive(cell, row - 1, col); // W
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, 0, col - 1); // NE
            addNeighborIfIsAlive(cell, 0, col); // E
            addNeighborIfIsAlive(cell, 0, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, row - 1, col + 1); // SW
        } else if (isTopEdge(row, col)) {
            addNeighborIfIsAlive(cell, row - 1, col); // W
            addNeighborIfIsAlive(cell, row, N - 1); // N
            addNeighborIfIsAlive(cell, row - 1, N - 1); // NW
            addNeighborIfIsAlive(cell, row + 1, N - 1); // NE
            addNeighborIfIsAlive(cell, row + 1, col); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, row - 1, col + 1); // SW
        } else if (isBottomEdge(row, col)) {
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // W
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, row + 1, col - 1); // NE
            addNeighborIfIsAlive(cell, row + 1, col); // E
            addNeighborIfIsAlive(cell, row + 1,0); // SE
            addNeighborIfIsAlive(cell, row, 0); // S
            addNeighborIfIsAlive(cell, row - 1, 0); // SW
        } else {
            addNeighborIfIsAlive(cell, row - 1, col - 1); // NW
            addNeighborIfIsAlive(cell, row - 1, col); // W
            addNeighborIfIsAlive(cell, row, col - 1); // N
            addNeighborIfIsAlive(cell, row + 1, col - 1); // NE
            addNeighborIfIsAlive(cell, row + 1, col); // E
            addNeighborIfIsAlive(cell, row + 1, col + 1); // SE
            addNeighborIfIsAlive(cell, row, col + 1); // S
            addNeighborIfIsAlive(cell, row - 1, col + 1); // SW
        }
    }

    private void addNeighborIfIsAlive(Cell cell, int row, int col) {
        Cell neighbor = prev[row][col];
        cell.addNeighbor(neighbor);
    }

    private boolean isTopLeftCorner(int row, int col) {
        return col == 0 && row == 0;
    }

    private boolean isTopRightCorner(int row, int col) {
        return col == 0 && (row == N - 1);
    }

    private boolean isBottomLeftCorner(int row, int col) {
        return (col == N - 1) && row == 0;
    }

    private boolean isBottomRightCorner(int row, int col) {
        return (col == N - 1) && (row == N - 1);
    }

    private boolean isLeftEdge(int row, int col) {
        return row == 0 && (col > 0 && col < N - 1);
    }

    private boolean isRightEdge(int row, int col) {
        return row == N - 1 && col > 0 && col < N - 1;
    }

    private boolean isTopEdge(int row, int col) {
        return (row > 0 && row < N - 1) && col == 0;
    }

    private boolean isBottomEdge(int row, int col) {
        return row > 0 && row < N - 1 && col == N - 1;
    }

    public void displayGrid() {
        for (Cell[] row : current) {
            for (Cell cell : row) {
                System.out.print(cell.isAlive() ? "(o)" : "[ ]");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getNumberOfCellsAlive() {
        return numberOfCellsAlive;
    }
}
