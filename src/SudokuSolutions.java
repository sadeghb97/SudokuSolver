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
}
