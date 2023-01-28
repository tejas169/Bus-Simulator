package Main;
import java.util.*;

public class FCFS {
    
    public static int startingTime = 0;  
    public static int endingTime = 0;
    public static int currentTime = startingTime;
    public static int[] meanIntervalArray = {0,30,35,40,45,50,55,60,65,70,75};  
    public static int numberOfProcess = 10;
    public static int IOTime = 60;
    public static int emptyIOTime = 0;
    
    public static int[][] recordArray = new int[1 + numberOfProcess][3];
    
    public static ArrayList<Process> processList = new ArrayList<Process>();
    

    
    
    public static void main(String[] args) {
    	int a;
    	a=1;
        while(a <= numberOfProcess) {
            int _burstTime = TimeFinder.executionTime();
            Process _p = new Process(a, _burstTime, meanIntervalArray[a]);
            processList.add(_p);
            a++;
        }
        
        while(!processList.isEmpty()) {
            boolean _empty = true;
            a=0;
            while(a < processList.size()) {
                Process _p = processList.get(a);
                if(_p.nextStartTime <= currentTime) {
                    _p.place = "ready queue";
                    processList.set(a, _p);
                    _empty = false;
                }
                a++;
            }

            if(_empty) {
                processList = Sort.sort("nextStartTime", processList);
                currentTime = processList.get(0).nextStartTime;
                endingTime = currentTime;
                continue;
            }
            
           
            
            
            processList = Sort.sort("nextStartTime", processList);
            Process prs = null;
            a=0;
            while(a < processList.size()) {
                prs = processList.get(a);
                if(prs.place.equals("ready queue")) {
                    processList.remove(a);
                    break;
                }
                prs = null;
                a++;
            }

            prs.waitingTime = prs.waitingTime + currentTime - prs.nextStartTime;
            int _interIOTime = TimeFinder.exponentiallyIOTime(prs.meanInterIOInterval);
            prs.thisRunningTime = TimeFinder.doubleToInt(Math.min(_interIOTime, prs.leftBurstTime));
            prs.leftBurstTime = prs.leftBurstTime - prs.thisRunningTime;
            prs.lastEndTime = currentTime + prs.thisRunningTime;
            prs.turnaroundTime = prs.lastEndTime - startingTime;
            prs.runningTime = prs.runningTime + prs.thisRunningTime;
            
            if(prs.leftBurstTime <= 0) {
                recordArray[prs.id][0] = prs.turnaroundTime;
                recordArray[prs.id][1] = prs.runningTime;
                recordArray[prs.id][2] = prs.waitingTime;
            }
            else {
                prs.nextStartTime = TimeFinder.doubleToInt(Math.max(prs.lastEndTime, emptyIOTime) + IOTime);
                prs.place = "I/O queue";
                emptyIOTime = prs.nextStartTime;
                processList.add(prs);
            }
            
            currentTime = prs.lastEndTime;
            endingTime = currentTime;
        }
        
        // print out result
        
        int sumWaitingTime = 0;
        int sumTurnaroundTime = 0;
        int sumRunningTime = 0;
        a=1;
        while(a <= numberOfProcess) {
            sumTurnaroundTime += recordArray[a][0];
            sumRunningTime += recordArray[a][1];
            sumWaitingTime += recordArray[a][2];
            a++;
        }
        double CPUUtilization = TimeFinder.DecimalPlace((double)sumRunningTime/((double)endingTime-(double)startingTime)*100d, 2);  // %
        double throughput = TimeFinder.DecimalPlace((double)numberOfProcess/(((double)endingTime-(double)startingTime)/1000d/60d), 2);  // processes per minute
        double averageTurnaroundTime = TimeFinder.DecimalPlace((double)sumTurnaroundTime/1000d/60d /10d, 2);  // minutes per process
        double averageWaitingTime = TimeFinder.DecimalPlace((double)sumWaitingTime/1000d/60d /10d, 2);  // minutes per process


        System.out.println("CPU utilization      Throughput      Average turnaround time      Average waiting time");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("   "+CPUUtilization+"                "+throughput+"            "+averageTurnaroundTime+"                         "+averageWaitingTime+"\n\n");
        System.out.println("\n");
        System.out.println("Starting Time is =" + startingTime+"           "+"Ending Time is =" + endingTime);
        System.out.println("\n");
        
        System.out.println("Total Number processes = "+numberOfProcess+"\n");
        
        System.out.println("CPU utilization FCFS ALGO = "+CPUUtilization+"% \n");
        System.out.println("Throughput(the average number of tasks finished in a given amount of time)= "+throughput+" process/min \n");
        System.out.println("Average turnaround time (the length of time it typically took to complete a task)= "+averageTurnaroundTime+" min/process \n");
        System.out.println("waiting time: "+averageWaitingTime+" minutes/process");    
    }

}
