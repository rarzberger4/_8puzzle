import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    int[][] puzzle = new int[3][3];
    private final int[][] goalState = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};


    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    // Method to set up a specific puzzle state
    public void setPuzzleState(int[][] newState) {
        this.puzzle = new int[newState.length][newState[0].length];
        for (int i = 0; i < newState.length; i++) {
            System.arraycopy(newState[i], 0, this.puzzle[i], 0, newState[i].length);
        }
    }


    private int randomIntFromInterval(int min, int max) { // min and max included
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    public void fill(){
        List<Integer> drawn = new ArrayList<>(8);
        int rndNum;

        do{
            drawn.clear();
            //System.out.println("generate Puzzle");
            for (int i = 0; i < this.puzzle.length; i++) {
                for (int j = 0; j < this.puzzle[i].length; j++) {
                    do{
                        rndNum = randomIntFromInterval(0,8);
                    }while(drawn.contains(rndNum));
                    drawn.add(rndNum);
                    this.puzzle[i][j] = rndNum;
                }
            }
        }while(!isSolvable(this.puzzle));
    }

    public void printPuzzle(){
        for (int row = 0; row < this.puzzle.length; row++)//Cycles through rows
        {
            for (int col = 0; col < this.puzzle[row].length; col++)//Cycles through columns
            {
                System.out.printf("%3d", this.puzzle[row][col]); //change the %5d to however much space you want
            }
            System.out.println(); //Makes a new row
        }
    }

    public int calculateManhattanDistance() {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    int targetX = value / 3;
                    int targetY = value % 3;
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return distance;
    }


    public int calculateHammingDistance() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] != 0 && puzzle[i][j] != goalState[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.puzzle);
    }
    public List<Puzzle> getNeighbors() {
        List<Puzzle> neighbors = new ArrayList<>();
        int blankRow = -1, blankCol = -1;

        // Find the blank (0) tile
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
            if (blankRow != -1) break; // Break if blank tile is found
        }

        // Directions: Up, Down, Left, Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = blankRow + dir[0];
            int newCol = blankCol + dir[1];

            // Check if the new position is within the bounds of the puzzle
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                Puzzle newPuzzle = new Puzzle();
                newPuzzle.setPuzzle(clonePuzzle(puzzle));
                newPuzzle.swap(blankRow, blankCol, newRow, newCol); // Swap the blank tile with the adjacent tile
                neighbors.add(newPuzzle);
            }
        }

        return neighbors;
    }

    private int[][] clonePuzzle(int[][] puzzle) {
        int[][] newPuzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(puzzle[i], 0, newPuzzle[i], 0, 3);
        }
        return newPuzzle;
    }

    private void swap(int row1, int col1, int row2, int col2) {
        int temp = puzzle[row1][col1];
        puzzle[row1][col1] = puzzle[row2][col2];
        puzzle[row2][col2] = temp;
    }

    public boolean isGoalState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] != goalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int calculateHeuristic(HeuristicType heuristicType) {
        switch (heuristicType) {
            case MANHATTAN:
                return calculateManhattanDistance();
            case HAMMING:
                return calculateHammingDistance();
            default:
                throw new IllegalArgumentException("Unknown heuristic type");
        }
    }

    // A utility function to count
// inversions in given array 'arr[]'
    static int getInvCount(int[] arr)
    {
        int inv_count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)

                // Value 0 is used for empty space
                if (arr[i] > 0 &&
                        arr[j] > 0 && arr[i] > arr[j])
                    inv_count++;
        return inv_count;
    }

    // This function returns true
// if given 8 puzzle is solvable.
    static boolean isSolvable(int[][] puzzle)
    {
        int[] linearPuzzle;
        linearPuzzle = new int[9];
        int k = 0;

        // Converting 2-D puzzle to linear form
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                linearPuzzle[k++] = puzzle[i][j];

        // Count inversions in given 8 puzzle
        int invCount = getInvCount(linearPuzzle);

        // return true if inversion count is even.
        return (invCount % 2 == 0);
    }
}


