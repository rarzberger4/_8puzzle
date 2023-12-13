import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<PuzzleNode> pq = new PriorityQueue<PuzzleNode>(5, new PNodeComparator());

        PuzzleNode pn1 = new PuzzleNode(1,2, "Peter");
        PuzzleNode pn2 = new PuzzleNode(1,7, "Lustig");

        pq.add(pn1);
        pq.add(pn2);

        System.out.println("Students served in their priority order");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll().getName());
        }


        Puzzle puzzle = new Puzzle();

        puzzle.fillRnd();
        puzzle.printPuzzle();

    }
}