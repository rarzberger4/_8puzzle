import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<PuzzleNode> pq = new PriorityQueue<PuzzleNode>(5, new PNodeComparator());


        Puzzle puzzle = new Puzzle();

        puzzle.fillRnd();
        puzzle.printPuzzle();

    }
}