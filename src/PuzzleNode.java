import java.util.Comparator;

public class PuzzleNode {   //node saves a puzzle and its corresponding values of g, h and the parent node


    private PuzzleNode parent;
    private Puzzle puzzle;
    public int g;
    public int h;


    public PuzzleNode(Puzzle puzzle, PuzzleNode parent, int g, int h) {
        this.g = g;
        this.h = h;
        this.puzzle = puzzle;
        this.parent = parent;
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

    // Overriding compare()method of Comparator for ascending order of PuzzleNodes depending on f

    @Override
    public int compare(PuzzleNode o1, PuzzleNode o2) {
        if(o1.getF() > o2.getF()){
            return -1;
        }else{
            return 0;
        }
    }
}
