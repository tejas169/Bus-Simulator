package Main;
import java.util.*;

public class Sort {
    public static ArrayList<Process> sort(String key, ArrayList<Process> pl) {
        
    	int x,j;
        if(key.equals("predictTime")) {
        	x = 0;
            while(x < pl.size()-1) {
                int _flag = 0;
                j=0;
                while(j < pl.size()-1) {
                    if(pl.get(j).predictTime > pl.get(j+1).predictTime) {
                        Process _p = pl.get(j+1);
                        pl.set(j+1, pl.get(j));
                        pl.set(j, _p);
                        _flag = 1;
                    }
                    j++;
                }
                if(_flag == 0) {
                    break;
                }
                x++;
            }
        }
        
        x=0;
        if(key.equals("nextStartTime")) {
            while(x < pl.size()-1) {
                int _flag = 0;
                j=0;
                while(j < pl.size()-1) {
                    if(pl.get(j).nextStartTime > pl.get(j+1).nextStartTime) {
                        Process _p = pl.get(j+1);
                        pl.set(j+1, pl.get(j));
                        pl.set(j, _p);
                        _flag = 1;
                    }
                    j++;
                }
                if(_flag == 0) {
                    break;
                }
                x++;
            }
        }        
        return pl;
    }
    
}
