import java.util.*;

public class Main {
    public static void main(String[] args) {

        int runs = 100;
        long[] timesManhattan = new long[runs];
        long[] timesHamming = new long[runs];
        long[] nodesExpandedManhattan = new long[runs];
        long[] nodesExpandedHamming = new long[runs];

        for (int i = 0; i < runs; i++) {
            long startTime, endTime;

            // System.out.println("Manhattan:");
            // Measure time for Manhattan heuristic
            startTime = System.nanoTime();
            Puzzle initialPuzzle = new Puzzle();
            initialPuzzle.fill();
            nodesExpandedManhattan[i] += runAlgorithm(HeuristicType.MANHATTAN, initialPuzzle);
            endTime = System.nanoTime();
            timesManhattan[i] += (endTime - startTime);

            // Measure time for Hamming heuristic
            //System.out.println("Hamming:");
            startTime = System.nanoTime();
            Puzzle initialPuzzle2 = new Puzzle();
            initialPuzzle2.fill();
            nodesExpandedHamming[i] += runAlgorithm(HeuristicType.HAMMING, initialPuzzle2);
            endTime = System.nanoTime();
            timesHamming[i] += (endTime - startTime);
        }

        double meanTimeManhattan = calculateMean(timesManhattan);
        double stdDevTimeManhattan = calculateStandardDeviation(timesManhattan, meanTimeManhattan);

        double meanTimeHamming = calculateMean(timesHamming);
        double stdDevTimeHamming = calculateStandardDeviation(timesHamming, meanTimeHamming);

        double meanNodesManhattan = calculateMean(nodesExpandedManhattan);
        double stdDevNodesManhattan = calculateStandardDeviation(nodesExpandedManhattan, meanNodesManhattan);

        double meanNodesHamming = calculateMean(nodesExpandedHamming);
        double stdDevNodesHamming = calculateStandardDeviation(nodesExpandedHamming, meanNodesHamming);

        // Displaying the results
        System.out.println("Manhattan computing time (mean): " + (meanTimeManhattan/1_000_000_000.0));
        System.out.println("Manhattan computing time (stdDev): " + (stdDevTimeManhattan/1_000_000_000.0));
        System.out.println("Manhattan nodes expanded (mean): " + meanNodesManhattan);
        System.out.println("Manhattan nodes expanded (stdDev): " + stdDevNodesManhattan);

        System.out.println("Hamming computing time (mean): " + (meanTimeHamming/1_000_000_000.0));
        System.out.println("Hamming computing time (stdDev): " + (stdDevTimeHamming/1_000_000_000.0));
        System.out.println("Hamming nodes expanded (mean): " + meanNodesHamming);
        System.out.println("Hamming nodes expanded (stdDev): " + stdDevNodesHamming);
    }


    static int runAlgorithm(HeuristicType heuristicType, Puzzle initialPuzzle) { //runs the A* algorithm, calls the specific heuristic
        // Initialize the priority queue and explored states set
        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(new PNodeComparator());
        Set<String> exploredStates = new HashSet<>();


        PuzzleNode initialNode = new PuzzleNode(initialPuzzle, null, 0, initialPuzzle.calculateHeuristic(heuristicType));
        openSet.add(initialNode);


        int nodes = 0; //to count the nodes expanded
        while (!openSet.isEmpty()) {
            nodes++;
            PuzzleNode currentNode = openSet.poll();


            // Check if the current node is the goal state
            if (currentNode.getPuzzle().isGoalState()) {
                //System.out.println(nodesExpanded);
                //return currentNode.getG();
                return nodes;
            }

            // Add the current state to the explored set
            String currentState = currentNode.getPuzzle().toString();
            if (exploredStates.contains(currentState)) {
                continue;
            }
            exploredStates.add(currentState);



            // Process each neighbor of the current state
            for (Puzzle neighbor : currentNode.getPuzzle().getNeighbors()) {
                if (!exploredStates.contains(neighbor.toString())) {
                    int tentativeG = currentNode.getG() + 1;
                    PuzzleNode neighborNode = new PuzzleNode(neighbor, currentNode, tentativeG, neighbor.calculateHeuristic(heuristicType));
                    openSet.add(neighborNode);
                }
            }
        }
        System.out.println("EXIT");
        return -1;
    }

    private static double calculateMean(long[] data) {
        double sum = 0.0;
        for (long datum : data) {
            sum += datum;
        }
        return sum / data.length;
    }

    private static double calculateStandardDeviation(long[] data, double mean) {
        double sum = 0.0;
        for (long datum : data) {
            sum += Math.pow(datum - mean, 2);
        }
        return Math.sqrt(sum / data.length);
    }

//    private static void printSolutionPath(PuzzleNode node) {
//        LinkedList<PuzzleNode> path = new LinkedList<>();
//        while (node != null) {
//            path.addFirst(node);
//            node = node.getParent();
//        }
//
//        for (PuzzleNode n : path) {
//            n.getPuzzle().printPuzzle();
//            System.out.println();
//        }
//    }
}
enum HeuristicType {
    MANHATTAN, HAMMING
}

