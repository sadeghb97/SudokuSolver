
import java.util.ArrayList;
import java.util.Scanner;

public class SudoNode {
    private SudoNode parent;
    private int[][] cells;
    private ArrayList[][] domains;

    public SudoNode getParent() { return parent;}
    public int[][] getCells() { return cells;}
    public ArrayList[][] getDomains() { return domains;}
    
    public SudoNode(SudoNode parent, int[][] cells, ArrayList[][] domains){
        this.parent = parent;
        this.cells = cells;
        this.domains = domains;
    }
    
    public SudoNode(int[][] cells){
        this.cells = cells;
        clean();
    }

    public SudoNode() {
        parent=null;
        cells = new int[9][];
        for(int i=0; 9>i; i++) cells[i] = new int[9];
        domains = new ArrayList[9][];
        for(int i=0; 9>i; i++){
            domains[i] = new ArrayList[9];
            for(int j=0; 9>j; j++){
                domains[i][j] = new ArrayList();
                for(int k=0; 9>k; k++) domains[i][j].add(k+1);
            }
        }
    }
    
    public void clean(){
        parent=null;
        domains = new ArrayList[9][];
        for(int i=0; 9>i; i++){
            domains[i] = new ArrayList[9];
            for(int j=0; 9>j; j++){
                domains[i][j] = new ArrayList();
                for(int k=0; 9>k; k++) domains[i][j].add(k+1);
            }
        }
        
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                chainUpdateDomains(i, j);
            }
        }        
    }
    
    private void chainUpdateDomains(int i, int j){
        if(cells[i][j] == 0) return;
        domains[i][j] = null;
        
        for(int k=0; 9>k; k++) if(k!=j && domains[i][k]!=null) domains[i][k].remove((Object) cells[i][j]);
        for(int k=0; 9>k; k++) if(k!=i && domains[k][j]!=null) domains[k][j].remove((Object) cells[i][j]);

        int sr = ((int)(i/3))*3;
        int sc = ((int)(j/3))*3;
        for(int m=0; 3>m; m++){
            for(int n=0; 3>n; n++){
                if((sr+m)!=i && (sc+n)!=j && domains[sr+m][sc+n]!=null)
                    domains[sr+m][sc+n].remove((Object) cells[i][j]);
            }
        }        
    }
    
    public boolean isValid(){
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                if(isDuplicateCell(i, j, cells[i][j])) return false;
            }
        }
        return true;        
    }
    
    public boolean isSolved(){
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                if(cells[i][j] == 0) return false;
            }
        }
        if(!isValid()) return false;
        return true;
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
    
    private boolean isDuplicateCell(int i, int j, int value){
        if(value==0) return false;
        for(int k=0; 9>k; k++) if(k!=j && cells[i][k]==value) return true;
        for(int k=0; 9>k; k++) if(k!=i && cells[k][j]==value) return true;
        
        int sr = i/3*3;
        int sc = j/3*3;
        for(int m=0; 3>m; m++){
            for(int n=0; 3>n; n++){
                if((sr+m)!=i && (sc+n)!=j && cells[sr+m][sc+n]==value) return true;
            }
        }
        return false;
    }
    
    public void inputSudoku(){
        StylishPrinter.println("Entering Sudoku", StylishPrinter.BOLD_RED);
        StylishPrinter.println("Help", StylishPrinter.BOLD_YELLOW);
        System.out.println("Enter <row,column,value> or <10> to reset sudoku or <0> to end!");
        System.out.println("Enter 0 value for clear a cell");
        
        while(true){
            System.out.println();
            printSudoku();
            System.out.print("Please Enter: ");
            
            int i,j,value;
            while(true){
                String inpStr = new Scanner(System.in).nextLine().trim();
                if(inpStr.equals("0")) return;
                if(inpStr.equals("10")) break;
                
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
                if(isDuplicateCell(i, j, value)){
                    System.out.print("Duplicate value! Try Again: ");
                    continue;
                }
                
                cells[i][j]=value;
                break;
            }
        }
    }
    
    public SudoNode getChild(int icell, int jcell, int value){
        int[][] childCells = new int[9][];
        for(int i=0; 9>i; i++) childCells[i] = new int[9];
        for(int i=0; 9>i; i++) for(int j=0; 9>j; j++) childCells[i][j] = cells[i][j];
        childCells[icell][jcell] = value;
        
        ArrayList[][] childDomains = new ArrayList[9][];
        for(int i=0; 9>i; i++){
            childDomains[i] = new ArrayList[9];
            for(int j=0; 9>j; j++){
                if(domains[i][j] == null) childDomains[i][j]=null;
                else childDomains[i][j] = (ArrayList) domains[i][j].clone();
            }
        }
        
        SudoNode childNode = new SudoNode(this, childCells, childDomains);
        childNode.chainUpdateDomains(icell, jcell);
        return childNode;
    }
    
    public int getNumConflicts(){
        int sumc=0;
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                if(cells[i][j]==0) continue;
                
                for(int k=j+1; 9>k; k++) if(cells[i][k] == cells[i][j]) sumc++;
                for(int k=i+1; 9>k; k++) if(cells[k][j] == cells[i][j]) sumc++;
                
                int maxr = i/3*3 + 3;
                int maxc = j/3*3 + 3;
                for(int m=i; maxr>m; m++){
                    int n;
                    if(m==i) n=j;
                    else n=j/3*3;
                    for(; maxc>n; n++){
                        if(m==i || n==j) continue;
                        if(cells[m][n] == cells[i][j]) sumc++;
                    }
                }
            }
        }
        return sumc;
    }
    
    public static int getNumConflicts(SudoNode node, int i, int j, int value){
        int oldValue = node.getCells()[i][j];
        node.getCells()[i][j] = value;
        int sumc = node.getNumConflicts();
        node.getCells()[i][j] = oldValue;
        return sumc;
    }

}
