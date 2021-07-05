package life;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameOfLifeTest {

    public static final int SEED = 100;

    @Test
    void initialize10AliveCellsWithSeedOf4() {
        Grid life = new Grid(4, 4);
        life.buildGrid();
        assertEquals(10, life.getLiveCells());
    }

    @Test
    void throwExceptionIfNIsLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grid(-1, SEED);
        });
    }

    @Test
    void throwExceptionIfNIsZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grid(0, SEED);
        });
    }

}
