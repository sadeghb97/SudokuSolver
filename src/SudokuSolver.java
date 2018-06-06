
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuSolver {
    public static void main(String[] args) {
        SudoNode sn = new SudoNode(SudokuDatas.getSudoku(1));
        sn.printSudoku();
    }
}
