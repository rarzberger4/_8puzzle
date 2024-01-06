import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class PuzzleNode {


    private Puzzle parent;
    private Puzzle puzzle;
    private int g;
    private int h;


    public PuzzleNode(int g, int h, Puzzle puzzle, Puzzle parent) {
        this.g = g;
        this.h = h;
        this.puzzle = puzzle;
        this.parent = parent;
    }

    public Puzzle getParent() {
        return parent;
    }

    public void setParent(Puzzle parent) {
        this.parent = parent;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}

class PNodeComparator implements Comparator<PuzzleNode> {

    // Overriding compare()method of Comparator
    // for ascending order of PuzzleNodes
    public int compare(@org.jetbrains.annotations.NotNull PuzzleNode s1, @NotNull PuzzleNode s2) {
        if ((s1.g + s1.h) > (s2.g + s2.h))
            return 1;
        else if ((s1.g + s1.h) < (s2.g + s2.h))
            return -1;
        return 0;
    }
}
