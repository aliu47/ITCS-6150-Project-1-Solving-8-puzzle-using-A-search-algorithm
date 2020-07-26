import java.util.Arrays;

public class Puzzleboard implements Comparable<Puzzleboard>{
    int g,f,h;
    int[][] puzzle = new int[3][3];
    Puzzleboard parent;
    public Puzzleboard(int g, int f, int h, int[][] puzzle, Puzzleboard parent) {
        this.g = g;
        this.f = f;
        this.h = h;
        this.puzzle = puzzle;
    }

    public Puzzleboard() {
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    private void setF(int g ,int h) {
        this.f = g+h;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
        setF(getG(),getH());
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public String toString() {
        return "Puzzleboard{" +
                "g=" + g +
                ", f=" + f +
                ", h=" + h +
                ", puzzle=" + Arrays.toString(puzzle) +
                '}';
    }

    @Override
    public int compareTo(Puzzleboard p) {
        int compare = f - p.f;
        if(compare == 0) {
            compare = h - p.h;
        }

        return compare;
    }
}
