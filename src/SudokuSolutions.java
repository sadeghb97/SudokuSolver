
import java.util.ArrayList;
import java.util.Random;

public class SudokuSolutions {
    public static SudoNode forwardChecking(SudoNode node){
        if(!node.isValid()) throw new RuntimeException();
        return fc(node);
    }
    
    private static SudoNode fc(SudoNode node){
        if(node.isSolved()) return node;
        
        int icell=-1,jcell=-1;
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                if(node.getCells()[i][j] == 0){
                    icell = i;
                    jcell = j;
                    break;
                }
            }
        }
        
        if(icell==-1 || jcell==-1) throw new RuntimeException();
        
        for(int k=0; node.getDomains()[icell][jcell].size()>k; k++){
            SudoNode child = node.getChild(icell, jcell, (int) node.getDomains()[icell][jcell].get(k));
            SudoNode childRes = fc(child);
            if(childRes!=null) return childRes;
        }
        
        return null;
    }
    
    public static SudoNode forwardCheckingWithMVR(SudoNode node){
        if(!node.isValid()) throw new RuntimeException();
        return fc(node);
    }
    
    private static SudoNode fcmvr(SudoNode node){
        if(node.isSolved()) return node;
        
        int icell=-1,jcell=-1;
        int minDomain=-1;
        
        for(int i=0; 9>i; i++){
            for(int j=0; 9>j; j++){
                if(node.getCells()[i][j] == 0 &&
                        (i==-1 || node.getDomains()[i][j].size()<minDomain)){
                    icell=i;
                    jcell=j;
                    minDomain = node.getDomains()[i][j].size();
                }
            }
        }
        
        if(icell==-1 || jcell==-1) throw new RuntimeException();
        
        for(int k=0; node.getDomains()[icell][jcell].size()>k; k++){
            SudoNode child = node.getChild(icell, jcell, (int) node.getDomains()[icell][jcell].get(k));
            SudoNode childRes = fcmvr(child);
            if(childRes!=null) return childRes;
        }
        
        return null;
    }
    
    public static SudoNode minimumConflicts(SudoNode node){
        boolean[][] fixed = new boolean[9][];
        ArrayList vars = new ArrayList();
        Random random = new Random();
        
        for(int i=0; 9>i; i++) fixed[i] = new boolean[9];
        for(int i=0; 9>i; i++){ 
            for(int j=0; 9>j; j++){
                if(node.getCells()[i][j] != 0) fixed[i][j] = true;
                else{
                    int randIndex = random.nextInt(node.getDomains()[i][j].size());
                    node.getCells()[i][j] = (int) node.getDomains()[i][j].get(randIndex);
                    vars.add(i*9+j);
                }
            }
        }
        
        int iterations = 0;
        while(true){
            if(node.isSolved()) return node;
            int sumc = node.getNumConflicts();
            
            boolean improved = false;
            ArrayList varsClone = (ArrayList) vars.clone();
            
            while(!improved && varsClone.size()>0){
                int randIndex = random.nextInt(varsClone.size());
                int mixedIJ = (int) varsClone.get(randIndex);
                varsClone.remove(randIndex);
                int icell = mixedIJ/9;
                int jcell = mixedIJ%9;

                if(fixed[icell][jcell]) continue;
                ArrayList domain = node.getDomains()[icell][jcell];

                for(int k=0; domain.size()>k; k++){
                    int newValue = (int) domain.get(k);
                    int ifsumc = SudoNode.getNumConflicts(node, icell, jcell, newValue);
                    if(ifsumc < sumc){
                        node.getCells()[icell][jcell] = newValue;
                        sumc = ifsumc;
                        improved = true;
                    }
                }
            }
            
            if(!improved){
                for(int k=0; 5>k; k++){
                    int icell = random.nextInt(9);
                    int jcell = random.nextInt(9);
                    while(fixed[icell][jcell]){
                        icell = random.nextInt(9);
                        jcell = random.nextInt(9);                    
                    }

                    node.getCells()[icell][jcell] = 1 + random.nextInt(9);
                }
            }
            
            iterations++;
        }
    }
}
