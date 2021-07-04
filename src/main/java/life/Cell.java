package life;

public class Cell {
    private byte neighbors;
    private boolean alive;

    public Cell() {
        this.alive = false;
        this.neighbors = 0;
    }

    public Cell(boolean alive) {
        this.alive = alive;
        this.neighbors = 0;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public byte getNeighbors() {
        return neighbors;
    }

    public void addNeighbor() {
        neighbors++;
    }

}
