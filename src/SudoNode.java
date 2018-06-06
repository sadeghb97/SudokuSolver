
import java.util.ArrayList;
import java.util.Scanner;

public class SudoNode {
    SudoNode parent;
    int[][] cells;
    ArrayList[][] domains;

    public SudoNode() {
        cells = new int[9][];
        for(int i=0; 9>i; i++) cells[i] = new int[9];
        domains = new ArrayList[9][];
        for(int i=0; 9>i; i++) domains[i] = new ArrayList[9];
    }
    
    public void printSudoku(){
        StylishPrinter.println(" -------------------------------------", StylishPrinter.BOLD_YELLOW);
        for(int i=0; 9>i; i++){
            StylishPrinter.print(" | ", StylishPrinter.BOLD_YELLOW);
            for(int j=0; 9>j; j++){
                if(cells[i][j]>0) StylishPrinter.print(cells[i][j] + " ", StylishPrinter.RESET);
                else StylishPrinter.print("  ", StylishPrinter.RESET);
                if(((j+1)%3)==0) StylishPrinter.print("| ", StylishPrinter.BOLD_YELLOW);
                else StylishPrinter.print("| ", StylishPrinter.BOLD_CYAN);
            }
            System.out.println();
            if (((i+1)%3)==0) 
                StylishPrinter.println(" -------------------------------------", StylishPrinter.BOLD_YELLOW);
            else StylishPrinter.println(" -------------------------------------", StylishPrinter.BOLD_CYAN);
        }
    }
    
    private boolean isDuplicateInput(int i, int j, int value){
        for(int k=0; 9>k; k++) if(k!=j && cells[i][k]==value) return true;
        for(int k=0; 9>k; k++) if(k!=i && cells[k][j]==value) return true;
        
        int sr = ((int)(i/3))*3;
        int sc = ((int)(j/3))*3;
        for(int m=0; 3>m; m++){
            for(int n=0; 3>n; n++){
                if((sr+m)!=i && (sc+n)!=j && cells[sr+m][sc+n]==value) return true;
            }
        }
        return false;
    }
    
    public void inputSudoku(){
        StylishPrinter.println("Entering Sudoku", StylishPrinter.BOLD_RED);
        System.out.println("Help: Enter row, column, value or 0 to end!");
        
        while(true){
            System.out.println();
            printSudoku();
            System.out.print("Please Enter: ");
            
            int i,j,value;
            while(true){
                String inpStr = new Scanner(System.in).nextLine().trim();
                if(inpStr.equals("0")) return;
                String[] strs = inpStr.split(" ");
                if(strs.length != 3){
                    System.out.print("Wrong input format! try again: ");
                    continue;
                }
                
                try{i = Integer.valueOf(strs[0]);}
                catch(NumberFormatException ex){
                    System.out.print("Wrong row input! Try Again: ");
                    continue;
                }
                if(i<1 || i>9){
                    System.out.print("Wrong row range! Try Again: ");
                    continue;
                }
                
                try{j = Integer.valueOf(strs[1]);}
                catch(NumberFormatException ex){
                    System.out.print("Wrong col input! Try Again: ");
                    continue;
                }
                if(j<1 || j>9){
                    System.out.print("Wrong col range! Try Again: ");
                    continue;
                }
                
                try{value = Integer.valueOf(strs[2]);}
                catch(NumberFormatException ex){
                    System.out.print("Wrong value input! Try Again: ");
                    continue;
                }
                if(value<0 || value>9){
                    System.out.print("Wrong value range! Try Again: ");
                    continue;
                }
                
                i--;
                j--;
                if(isDuplicateInput(i, j, value)){
                    System.out.print("Duplicate value! Try Again: ");
                    continue;
                }
                
                cells[i][j]=value;
                break;
            }
        }
    }

}
