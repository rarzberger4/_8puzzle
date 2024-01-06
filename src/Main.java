import java.util.*;

public class Main {
    public static void main(String[] args) {
        int totalExpandedNodesManhattan = 0;
        int totalExpandedNodesHamming = 0;
        long totalTimeManhattan = 0; // Time in nanoseconds
        long totalTimeHamming = 0;   // Time in nanoseconds

        for (int i = 0; i < 1; i++) {
            long startTime, endTime;

            System.out.println("Manhattan:");
            // Measure time for Manhattan heuristic
            startTime = System.nanoTime();
            totalExpandedNodesManhattan += runAlgorithm(HeuristicType.MANHATTAN);
            endTime = System.nanoTime();
            totalTimeManhattan += (endTime - startTime);

            // Measure time for Hamming heuristic
            System.out.println("Hamming:");
            startTime = System.nanoTime();
            totalExpandedNodesHamming += runAlgorithm(HeuristicType.HAMMING);
            endTime = System.nanoTime();
            totalTimeHamming += (endTime - startTime);
        }

        // Calculating average time in seconds
        double avgTimeManhattanSeconds = totalTimeManhattan / 100.0 / 1_000_000_000.0;
        double avgTimeHammingSeconds = totalTimeHamming / 100.0 / 1_000_000_000.0;

        // Displaying the results
        System.out.println("Average nodes expanded (Manhattan): " + (totalExpandedNodesManhattan / 100.0));
        System.out.println("Average computation time (Manhattan): " + avgTimeManhattanSeconds + " seconds");

        System.out.println("Average nodes expanded (Hamming): " + (totalExpandedNodesHamming / 100.0));
        System.out.println("Average computation time (Hamming): " + avgTimeHammingSeconds + " seconds");
    }

    private static int runAlgorithm(HeuristicType heuristicType) {
        // Initialize the priority queue and explored states set
        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(new PNodeComparator());
        Set<String> exploredStates = new HashSet<>();

        // Generate a new puzzle and add the initial node to the open set
        Puzzle initialPuzzle = new Puzzle();
        initialPuzzle.fill();
        PuzzleNode initialNode = new PuzzleNode(initialPuzzle, null, 0, initialPuzzle.calculateHeuristic(heuristicType));
        openSet.add(initialNode);

        int nodesExpanded = 0;

        while (!openSet.isEmpty()) {
            PuzzleNode currentNode = openSet.poll();
            nodesExpanded++;
            // Log the current state being expanded
            //System.out.println("Expanding node: f=" + currentNode.getF() + ", state=" + currentNode.getPuzzle());

            // Check if the current node is the goal state
            if (currentNode.getPuzzle().isGoalState()) {
                //System.out.println(nodesExpanded);
                return nodesExpanded;
            }

            // Add the current state to the explored set
            String currentState = currentNode.getPuzzle().toString();
            exploredStates.add(currentState);

            // Process each neighbor of the current state
            for (Puzzle neighbor : currentNode.getPuzzle().getNeighbors()) {
                int tentativeG = currentNode.getG() + 1;
                PuzzleNode neighborNode = new PuzzleNode(neighbor, currentNode, tentativeG, neighbor.calculateHeuristic(heuristicType));

                // Check if the neighbor state has not been explored
                String neighborState = neighbor.toString();
                if (!exploredStates.contains(neighborState)) {
                    openSet.add(neighborNode);
                }
            }
        }
        //System.out.println("EXIT: "+nodesExpanded);
        return nodesExpanded;
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

