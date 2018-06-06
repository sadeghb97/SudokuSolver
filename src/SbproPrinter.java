

public class SbproPrinter {
    public static String roundDouble(double fNum, int decimals){
        int coef = (int) Math.pow(10, decimals);
        return String.valueOf((double)((int)(fNum*coef))/coef);
    }
    
    public static void printDelimiter(String delStr){
        System.out.print(" ");
        StylishPrinter.print(delStr, StylishPrinter.BOLD_YELLOW, StylishPrinter.BG_CYAN);
        System.out.print(" ");        
    }
    
    public static void printDelimiter(){
        printDelimiter("//");
    }
}
