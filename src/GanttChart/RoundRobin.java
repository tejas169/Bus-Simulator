package GanttChart;


import java.util.*;


public class RoundRobin {
	public static double quantum = 5d;
	public static ArrayList<double[]> recordQ = new ArrayList<double[]>();  
	public static double[][] record = new double[14][2]; 
    public static double startingTime = 0d;
    public static double currentTime = startingTime;
    public static ArrayList<Process> processQ = new ArrayList<Process>();
 
    
    public static void main(String[] args) {
        
        processQ.add(new Process(1, 3, 2d));
        processQ.add(new Process(2, 1, 7d));
        processQ.add(new Process(3, 3, 6d));
        processQ.add(new Process(4, 4, 10d));
        processQ.add(new Process(5, 2, 14d));
        processQ.add(new Process(6, 2, 18d));
        processQ.add(new Process(7, 2, 22d));
        
        while(!processQ.isEmpty()) {
            Process processes = processQ.get(0);
            processQ.remove(0);

            double _startTime = currentTime;
            processes.waitingTime = processes.waitingTime + _startTime - processes.lastStop;
            currentTime = currentTime + Math.min(processes.burstTime, quantum);
            processes.burstTime = processes.burstTime - quantum;
            processes.lastStop = currentTime;
            processes.turnaroundTime = currentTime - processes.arrivalTime;

            double[] _r = new double[3];
            _r[0] = processes.id;
            _r[1] = _startTime;
            _r[2] = currentTime;
            recordQ.add(_r);
            
            if(processes.burstTime <= 0d) {
                record[processes.id][0] = processes.turnaroundTime;
                record[processes.id][1] = processes.waitingTime;
            }
            else {
                processQ.add(processes);
            }
        }
        
        double WaitingTime = 0d;
        int i = 1;
        while(i <= 5) {
            WaitingTime += TimeFinder.DecimalPlace(record[i][1], 1);
            i++;
        }
        System.out.println("Average waiting time: "+TimeFinder.DecimalPlace(WaitingTime/5, 1));

        System.out.println("");
        
        String s1 = "RR:  |";
        String s2 = "     0";
        i=0;
        while(i < recordQ.size()) {
            double[] _re = recordQ.get(i);
            s1 = s1 + " P" + TimeFinder.doubleToInt(_re[0]) + " |";
            s2 = s2 + "   " + String.format("%2s", TimeFinder.doubleToInt(_re[2]));
            i++;
        }
        System.out.println(s1);
        System.out.println(s2);
    }

}

