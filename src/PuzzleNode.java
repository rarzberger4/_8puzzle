import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class PuzzleNode {


    public Puzzle childNode;
    private String name;
    public int g;
    public int h;
    private PuzzleNode next;

    public Puzzle getChildNode() {
        return childNode;
    }

    public void setChildNode(Puzzle childNode) {
        this.childNode = childNode;
    }


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
    public int compare(@org.jetbrains.annotations.NotNull PuzzleNode s1, @NotNull PuzzleNode s2) {
        if ((s1.g + s1.h) > (s2.g + s2.h))
            return 1;
        else if ((s1.g + s1.h) < (s2.g + s2.h))
            return -1;
        return 0;
    }
}
