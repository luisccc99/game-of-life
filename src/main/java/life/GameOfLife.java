package life;

import java.util.Scanner;

public class GameOfLife {
    public static int NUM_GENERATIONS = 9;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long seed = System.currentTimeMillis();
        int m = NUM_GENERATIONS;
        final Grid game = new Grid(n, seed);
        game.buildGrid();
        for (int i = 0; i <= m; i++) {
            System.out.println("Generation: #" + (i + 1));
            System.out.println("Alive: " + game.getLiveCells());
            game.displayGrid();
            game.nextGeneration();
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
