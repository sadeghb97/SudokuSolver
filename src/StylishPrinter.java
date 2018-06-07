import java.util.Stack;
import org.fusesource.jansi.AnsiConsole;

public class StylishPrinter {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public static final String BOLD_RESET = "\u001B[0;1m";
    public static final String BOLD_BLACK = "\u001B[30;1m";
    public static final String BOLD_RED = "\u001B[31;1m";
    public static final String BOLD_GREEN = "\u001B[32;1m";
    public static final String BOLD_YELLOW = "\u001B[33;1m";
    public static final String BOLD_BLUE = "\u001B[34;1m";
    public static final String BOLD_PURPLE = "\u001B[35;1m";
    public static final String BOLD_CYAN = "\u001B[36;1m";
    public static final String BOLD_WHITE = "\u001B[37;1m";
    
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";
    
    public static void print(String str, String fgcolor, String bgcolor){
        if(fgcolor == null) fgcolor = "";
        if(bgcolor == null) bgcolor = "";
        if(OSDetecter.isWindows())
            AnsiConsole.out.print(bgcolor + fgcolor + str + RESET);
        else
            System.out.print(bgcolor + fgcolor + str + RESET);
    }
    
    public static void print(String str, String fgcolor){
        print(str, fgcolor, "");
    }
    
    public static void println(String str, String fgcolor, String bgcolor){
        print(str, fgcolor, bgcolor);
        System.out.println("");
    }
    
    public static void println(String str, String fgcolor){
        print(str, fgcolor);
        System.out.println("");
    }
    
    public static void printSpace(int num){
        for(int i=0; num>i; i++) System.out.print(" ");
    }
    
    public static String getFormattedNumber(long number, String splitter){
        long n = number;
        Stack stack = new Stack();
        
        while(n>0){
            String piece = String.valueOf(n%1000);
            if(n>=1000){
                if(piece.length()==1) piece = "00" + piece;
                else if(piece.length()==2) piece = "0" + piece;
            }
            
            stack.add(piece);
            n = n/1000;
        }
        
        StringBuilder strBuilder = new StringBuilder();
        boolean first = true;
        while(!stack.empty()){
            if(first) first=false;
            else strBuilder.append(splitter);
            strBuilder.append(stack.pop());
        }
        
        return strBuilder.toString();
    }
}
