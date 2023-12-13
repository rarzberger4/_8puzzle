import java.util.Comparator;

public class PuzzleNode {

    public Puzzle data;

    private String name;
    public int g;
    public int h;
    private PuzzleNode next;

    public PuzzleNode(int g, int h, String name) {
        this.g = g;
        this.h = h;
        this.name = name;
    }

    public void setNextPuzzleNode(PuzzleNode node) {
        this.next = node;
    }

    public PuzzleNode getNextPuzzleNode() {
        return this.next;
    }

    public String getName(){
        return this.name;
    }


}

class PNodeComparator implements Comparator<PuzzleNode> {

    // Overriding compare()method of Comparator
    // for ascending order of PuzzleNodes
    public int compare(PuzzleNode s1, PuzzleNode s2) {
        if ((s1.g + s1.h) > (s2.g + s2.h))
            return 1;
        else if ((s1.g + s1.h) < (s2.g + s2.h))
            return -1;
        return 0;
    }
}
