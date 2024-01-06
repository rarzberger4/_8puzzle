import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // Your code for initializing the puzzle

        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(new PNodeComparator());

        Puzzle initialPuzzle = new Puzzle();
        initialPuzzle.fillRnd();
        initialPuzzle.printPuzzle();

        PuzzleNode initialNode = new PuzzleNode(initialPuzzle, null, 0, initialPuzzle.calculateHeuristic(HeuristicType.MANHATTAN));
        openSet.add(initialNode);

        while (!openSet.isEmpty()) {
            PuzzleNode currentNode = openSet.poll();
            if (currentNode.getPuzzle().isGoalState()) {
                printSolutionPath(currentNode);
                return;
            }

            for (Puzzle neighbor : currentNode.getPuzzle().getNeighbors()) {
                int tentativeG = currentNode.getG() + 1; // Assuming each move costs 1
                PuzzleNode neighborNode = new PuzzleNode(neighbor, currentNode, tentativeG, neighbor.calculateHeuristic(HeuristicType.MANHATTAN));

                // Add neighborNode to the open set if it's not there already with a lower f-score
                if (!containsLowerFScore(openSet, neighborNode)) {
                    openSet.add(neighborNode);
                }
            }
        }
    }

    private static boolean containsLowerFScore(PriorityQueue<PuzzleNode> openSet, PuzzleNode node) {
        return openSet.stream().anyMatch(n -> n.getPuzzle().equals(node.getPuzzle()) && n.getF() <= node.getF());
    }

    private static void printSolutionPath(PuzzleNode node) {
        LinkedList<PuzzleNode> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node);
            node = node.getParent();
        }

        for (PuzzleNode n : path) {
            n.getPuzzle().printPuzzle();
            System.out.println();
        }
    }
}

enum HeuristicType {
    MANHATTAN, HAMMING
}