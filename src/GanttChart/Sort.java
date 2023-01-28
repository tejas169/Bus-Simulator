package GanttChart;

import java.util.*;

public class Sort {
    
    public static ArrayList<Process> sort(String key, ArrayList<Process> PROQ) {
        if(key.equals("burstTime")) {
        	int i=0;
            while(i < PROQ.size()-1) {
                int _flag = 0;
                int j= 0;
                while(j < PROQ.size()-1) {
                    if(PROQ.get(j).burstTime > PROQ.get(j+1).burstTime) {
                        Process _p = PROQ.get(j+1);
                        PROQ.set(j+1, PROQ.get(j));
                        PROQ.set(j, _p);
                        _flag = 1;
                    }
                    j++;
                }
                if(_flag == 0) {
                    break;
                }
                i++;
            }
        }
        
        if(key.equals("arrivalTime")) {
        	int i =0;
            while(i < PROQ.size()-1) {
                int _flag = 0;
                int j = 0;
                while(j < PROQ.size()-1) {
                    if(PROQ.get(j).arrivalTime > PROQ.get(j+1).arrivalTime) {
                        Process _p = PROQ.get(j+1);
                        PROQ.set(j+1, PROQ.get(j));
                        PROQ.set(j, _p);
                        _flag = 1;
                    }
                    j++;
                }
                if(_flag == 0) {
                    break;
                }
                i++;
            }
        }
        
        if(key.equals("priority")) {
        	int i = 0;
            while(i < PROQ.size()-1) {
                int _flag = 0;
                int j = 0;
                while(j < PROQ.size()-1) {
                    if(PROQ.get(j).priority > PROQ.get(j+1).priority) {
                        Process _p = PROQ.get(j+1);
                        PROQ.set(j+1, PROQ.get(j));
                        PROQ.set(j, _p);
                        _flag = 1;
                    }
                    j++;
                }
                if(_flag == 0) {
                    break;
                }
                i++;
            }
        }        
        return PROQ;
    }
    
}

