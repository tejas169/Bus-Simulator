package GanttChart;

import java.util.*;


public class SJF {
    public static double startingTime = 0d;
    public static double currentTime = startingTime;
    public static ArrayList<Process> processQ = new ArrayList<Process>();
    public static int turn = 0;
    public static int numOfProcess = 5;
    public static double[][] record = new double[numOfProcess*2+1][5];   
    
    public static void main(String[] args) {
        
        processQ.add(new Process(1, 3, 13d));
        processQ.add(new Process(2, 1, 6d));
        processQ.add(new Process(3, 3, 5d));
        processQ.add(new Process(4, 4, 8d));
        processQ.add(new Process(5, 2, 7d));
        processQ.add(new Process(6, 2, 5d));
        processQ.add(new Process(7, 2, 1d));
        
        while(!processQ.isEmpty()) {
            processQ = Sort.sort("burstTime", processQ);
            Process processes = processQ.get(0);
            processQ.remove(0);
        
            double _startTime = currentTime;
            processes.waitingTime = processes.waitingTime + _startTime - processes.lastStop;
            currentTime = currentTime + processes.burstTime;
            processes.turnaroundTime = currentTime - processes.arrivalTime;
            
            record[turn][0] = processes.id;
            record[turn][1] = _startTime;
            record[turn][2] = currentTime;
            record[turn][3] = processes.turnaroundTime;
            record[turn][4] = processes.waitingTime;
            turn++;
        }
        
        double WaitingTime = 0d;
        for(int i = 0; record[i][0] != 0d; i++) {
            WaitingTime += TimeFinder.DecimalPlace(record[i][4], 1);
        }
        System.out.println("Average waiting time: "+TimeFinder.DecimalPlace(WaitingTime/5, 1));

        System.out.println("");
        
        String s1 = "SJF:  |";
        String s2 = "      0";
        for(int i = 0; record[i][0] != 0d; i++) {
            s1 = s1 + "  P" + TimeFinder.doubleToInt(record[i][0]) + "  |";
            s2 = s2 + "     " + String.format("%2s", TimeFinder.doubleToInt(record[i][2]));
        }
        System.out.println(s1);
        System.out.println(s2);
    }

}

