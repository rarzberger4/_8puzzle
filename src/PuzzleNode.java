import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class PuzzleNode {


    private PuzzleNode parent;
    private Puzzle puzzle;
    public int g;
    public int h;


    public PuzzleNode(Puzzle puzzle, PuzzleNode parent, int g, int h) {
        this.g = g;
        this.h = h;
        this.puzzle = puzzle;
        this.parent = parent;
        // Log the heuristic values for debugging
        System.out.println("Creating node: g=" + g + ", h=" + h + ", state=" + puzzle);
    }



    public PuzzleNode getParent() {
        return parent;
    }

    public void setParent(PuzzleNode parent) {
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
    public int getF() {
        return g + h; // f-score is the sum of g and h
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
