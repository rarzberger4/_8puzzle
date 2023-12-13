import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    int[][] puzzle = new int[3][3];


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


}