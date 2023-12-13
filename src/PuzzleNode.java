public class PuzzleNode {

    public Puzzle data;
    private PuzzleNode next;

    public PuzzleNode(Puzzle data) {
        this.data = data;
        this.next = null;
    }

    public void setNextPuzzleNode(PuzzleNode node) {
        this.next = node;
    }

    public PuzzleNode getNextPuzzleNode() {
        return this.next;
    }


}
