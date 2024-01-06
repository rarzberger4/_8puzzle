import java.util.*;

public class Main {
    public static void main(String[] args) {
        int totalExpandedNodesManhattan = 0;
        int totalExpandedNodesHamming = 0;

        for (int i = 0; i < 100; i++) {
            totalExpandedNodesManhattan += runAlgorithm(HeuristicType.MANHATTAN);
            //System.out.println(totalExpandedNodesManhattan);
            totalExpandedNodesHamming += runAlgorithm(HeuristicType.HAMMING);
            //System.out.println(totalExpandedNodesHamming);
        }

        System.out.println("Average nodes expanded (Manhattan): " + (totalExpandedNodesManhattan / 100.0));
        System.out.println("Average nodes expanded (Hamming): " + (totalExpandedNodesHamming / 100.0));



    }

    private static int runAlgorithm(HeuristicType heuristicType) {
        PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(new PNodeComparator());
        Map<String, Integer> stateFscoreMap = new HashMap<>();
        Puzzle initialPuzzle = new Puzzle();
        initialPuzzle.fill();
        PuzzleNode initialNode = new PuzzleNode(initialPuzzle, null, 0, initialPuzzle.calculateHeuristic(heuristicType));
        openSet.add(initialNode);
        stateFscoreMap.put(initialPuzzle.toString(), initialNode.getF());

        int nodesExpanded = 0;
        while (!openSet.isEmpty()) {
            PuzzleNode currentNode = openSet.poll();
            nodesExpanded++;
            if (currentNode.getPuzzle().isGoalState()) {
                return nodesExpanded;
            }

            for (Puzzle neighbor : currentNode.getPuzzle().getNeighbors()) {
                int tentativeG = currentNode.getG() + 1;
                PuzzleNode neighborNode = new PuzzleNode(neighbor, currentNode, tentativeG, neighbor.calculateHeuristic(heuristicType));
                String neighborState = neighbor.toString();
                int neighborF = neighborNode.getF();

                if (!stateFscoreMap.containsKey(neighborState) || stateFscoreMap.get(neighborState) > neighborF) {
                    stateFscoreMap.put(neighborState, neighborF);
                    openSet.add(neighborNode);
                }
            }
        }
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

