import static org.junit.jupiter.api.Assertions.*;

class Testing {

    @org.junit.jupiter.api.Test
    void testPuzzleGeneration() {
        Puzzle puzzle = new Puzzle();
        puzzle.fill();
        assertNotNull(puzzle.getPuzzle());
        assertTrue(Puzzle.isSolvable(puzzle.getPuzzle()));
    }

    @org.junit.jupiter.api.Test
    void testManhattanDistance() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
        assertEquals(0, puzzle.calculateManhattanDistance());
    }

    @org.junit.jupiter.api.Test
    void testHammingDistance() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
        assertEquals(0, puzzle.calculateHammingDistance());
    }

    @org.junit.jupiter.api.Test
    void testAStarWithManhattan() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}); // Easy puzzle
        int nodesExpanded = Main.runAlgorithm(HeuristicType.MANHATTAN, puzzle);
        assertTrue(nodesExpanded > 0);
    }

    @org.junit.jupiter.api.Test
    void testAStarWithHamming() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}); // Easy puzzle
        int nodesExpanded = Main.runAlgorithm(HeuristicType.HAMMING, puzzle);
        assertTrue(nodesExpanded > 0);
    }


    @org.junit.jupiter.api.Test
    void testManhattanConsistency() {
        Puzzle puzzle1 = new Puzzle();
        puzzle1.setPuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
        int distance1 = puzzle1.calculateManhattanDistance();

        Puzzle puzzle2 = new Puzzle();
        puzzle2.setPuzzleState(new int[][]{{1, 0, 2}, {3, 4, 5}, {6, 7, 8}});
        int distance2 = puzzle2.calculateManhattanDistance();

        assertTrue(distance2 > distance1);
    }

    @org.junit.jupiter.api.Test
    void testHammingConsistency() {
        Puzzle puzzle1 = new Puzzle();
        puzzle1.setPuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
        int distance1 = puzzle1.calculateHammingDistance();

        Puzzle puzzle2 = new Puzzle();
        puzzle2.setPuzzleState(new int[][]{{1, 0, 2}, {3, 4, 5}, {6, 7, 8}});
        int distance2 = puzzle2.calculateHammingDistance();

        assertTrue(distance2 > distance1);
    }

    @org.junit.jupiter.api.Test
    void testGoalStateReached() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}});
        assertTrue(puzzle.isGoalState());
    }

    @org.junit.jupiter.api.Test
    void testNonGoalState() {
        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleState(new int[][]{{1, 0, 2}, {3, 4, 5}, {6, 7, 8}});
        assertFalse(puzzle.isGoalState());
    }

}