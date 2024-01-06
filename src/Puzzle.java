import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Puzzle {
    int[][] puzzle = new int[3][3];
    private final int[][] goalState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};


    public int[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }


    private int randomIntFromInterval(int min, int max) { // min and max included
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
    public void fillRnd(){
        List<Integer> drawn = new ArrayList<Integer>(8);
        int rndNum;


        for (int i = 0; i < this.puzzle.length; i++) {
            for (int j = 0; j < this.puzzle[i].length; j++) {
                do{
                    rndNum = randomIntFromInterval(0,8);
                }while(drawn.contains(rndNum));
                drawn.add(rndNum);
                this.puzzle[i][j] = rndNum;
            }
        }
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
                if (value != 0) { // Skip the blank tile
                    int targetX = (value - 1) / 3; // Expected x-coordinate (row)
                    int targetY = (value - 1) % 3; // Expected y-coordinate (col)
                    distance += Math.abs(i - targetX) + Math.abs(j - targetY);      //calculate distance in regard to the solved state
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
            if (blankRow != -1) break;
        }

        // Generate neighbors by sliding tiles into the blank space
        // Up, Down, Left, Right moves if valid
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = blankRow + dir[0];
            int newCol = blankCol + dir[1];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                Puzzle newPuzzle = new Puzzle();
                newPuzzle.setPuzzle(clonePuzzle(puzzle));
                newPuzzle.swap(blankRow, blankCol, newRow, newCol);
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
}


