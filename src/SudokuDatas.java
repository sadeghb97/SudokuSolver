
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

public class SudokuDatas {
    public static int[][] getSudoku(int index){
        index--;
        try{
            ClassLoader classLoader = SudokuSolver.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("sudos.json");
            String jsonString = readFromInputStream(inputStream);
            JSONArray fullJsonArray = new JSONArray(jsonString);
            
            JSONArray sudoJsonArray = (JSONArray) fullJsonArray.get(index);
            int[][] cells = new int[9][];
            for(int i=0; 9>i; i++) cells[i] = new int[9];
            
            for(int k=0; sudoJsonArray.length()>k; k++){
                int i = k/9;
                int j = k%9;
                cells[i][j] = sudoJsonArray.getInt(k);
            }
            return cells;
        }
        catch(Exception ex){
            System.err.println(SudokuDatas.class.getName() + " Error!");
            Logger.getLogger(SudokuDatas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private static String readFromInputStream(InputStream inputStream)
      throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
          = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
      return resultStringBuilder.toString();
    }
}
