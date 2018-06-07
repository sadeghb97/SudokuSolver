
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuSolver {
    private static SudoNode currentSudoku;
    
    public static void main(String[] args) {
        currentSudoku = new SudoNode(SudokuDatas.getSudoku(1 + new Random().nextInt(25)));
        while(menu());
    }
    
    public static void createSudoku(){
        StylishPrinter.println("\nEntering Sudoku:", StylishPrinter.BOLD_RED);
        System.out.println("1: Load a provided sudoku");
        System.out.println("2: Manually input sudoku");
        System.out.println("3: Cancel");
        System.out.print("Your Choice: ");
        int choose = SbproScanner.inputInt(1, 3);
        System.out.println();
        if(choose==1){
            System.out.print("Enter sudoku number between 1 and 25: ");
            int sn = SbproScanner.inputInt(1, 25);
            currentSudoku = new SudoNode(SudokuDatas.getSudoku(sn));
        }
        else currentSudoku.inputSudoku();
    }
    
    public static void forwardCheckingSolve(){
        long startTime = System.nanoTime();
        SudoNode result = SudokuSolutions.forwardChecking(currentSudoku);
        long endTime = System.nanoTime();
        
        currentSudoku.clean();
        StylishPrinter.println("\nResult:", StylishPrinter.BOLD_GREEN);
        result.printSudoku();
        
        System.out.println("Created nodes: " + SudokuSolutions.createdNodes);
        printRuntime(endTime - startTime);
    }
    
    public static void forwardCheckingMRVSolve(){
        long startTime = System.nanoTime();
        SudoNode result = SudokuSolutions.forwardCheckingWithMVR(currentSudoku);
        long endTime = System.nanoTime();
        
        currentSudoku.clean();
        StylishPrinter.println("\nResult:", StylishPrinter.BOLD_GREEN);
        result.printSudoku();
        
        System.out.println("Created nodes: " + SudokuSolutions.createdNodes);
        printRuntime(endTime - startTime);       
    }
    
    public static void minimumConflictsSolve(){
        long startTime = System.nanoTime();
        SudoNode result = SudokuSolutions.minimumConflicts(currentSudoku);
        long endTime = System.nanoTime();
        
        currentSudoku.clean();
        StylishPrinter.println("\nResult:", StylishPrinter.BOLD_GREEN);
        result.printSudoku();
        
        System.out.println("Iterations: " + SudokuSolutions.iterations);
        printRuntime(endTime - startTime);         
    }
    
    public static String getSecRuntimeString(long runtime){
        double secTime = (double)(runtime)/1000000000;
        int decimalsNum;
        if(secTime<0.0001) decimalsNum=5;
        else if(secTime<0.001) decimalsNum=4;
        else decimalsNum=3;
        return SbproPrinter.roundDouble(secTime, decimalsNum);
    }
    
    public static void printRuntime(long runtime, String fgColor, String bgColor){
        StylishPrinter.println("Runtime: " + getSecRuntimeString(runtime), fgColor, bgColor);
    }
    
    public static void printRuntime(long runtime){
        printRuntime(runtime, StylishPrinter.BOLD_YELLOW, StylishPrinter.BG_CYAN);
    }
    
    public static boolean menu(){
        StylishPrinter.println("\nMenu:", StylishPrinter.BOLD_RED);
        System.out.println("1: Forward checking solve");
        System.out.println("2: Forvard checking with MRV heuristic solve");
        System.out.println("3: Minimum conflicts local Search solve");
        System.out.println("4: Show Current Sudoku");
        System.out.println("5: Change Sudoku");
        System.out.println("6: Exit");
        System.out.print("\nEnter Your Choice: ");
        int choice = SbproScanner.inputInt(1, 6);
        
        if(choice==1) forwardCheckingSolve();
        else if(choice==2) forwardCheckingMRVSolve();
        else if(choice==3) minimumConflictsSolve();
        else if(choice==4){
            StylishPrinter.println("\nCurrent Sudoku:", StylishPrinter.BOLD_RED);
            currentSudoku.printSudoku();
        }
        else if(choice==5) createSudoku();
        else if(choice==6) return false;
        
        return true;
    }
}
