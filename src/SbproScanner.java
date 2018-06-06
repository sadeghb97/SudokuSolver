


import java.util.Scanner;

public class SbproScanner {
    public static int inputInt(int min, int max){
        Scanner scanner = new Scanner(System.in);
        int result;
        
        while(true){
            String input = scanner.next();
            try{result = Integer.valueOf(input);}
            catch(NumberFormatException ex){
                System.out.print("Wrong format! Try Again: ");
                continue;
            }
            
            if(result<min || result>max){
                System.out.print("Wrong range! Try Again: ");
                continue;
            }
            
            return result;
        }
    }
    
    public static double inputDouble(double min, double max){
        Scanner scanner = new Scanner(System.in);
        double result;
        
        while(true){
            String input = scanner.next();
            try{result = Double.valueOf(input);}
            catch(NumberFormatException ex){
                System.out.print("Wrong format! Try Again: ");
                continue;
            }
            
            if(result<min || result>max){
                System.out.print("Wrong range! Try Again: ");
                continue;
            }
            
            return result;
        }
    } 
}
