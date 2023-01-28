package Main;


import java.util.*;


public class SJF {
    
    // output route
//    public static String route = "./output/project2/a/SJF Output.txt";

    // start time
    public static int startingTime = 0;
    // end time
    public static int endingTime = 0;
    // current time
    public static int currentTime = startingTime;
    // process quantity
    public static int numberOfProcess = 10;
    // array of mean inter I/O interval
    public static int[] meanIntervalArray = {0,30,35,40,45,50,55,60,65,70,75};
    // I/O Time
    public static int IOTime = 60;
    // the time I/O queue is empty
    public static int emptyIOTime = 0;
    
    // process list
    public static ArrayList<Process> processList = new ArrayList<Process>();
    // finished process state
    public static int[][] recordArray = new int[numberOfProcess+1][3];  // record[id]: turnaround time, running time, waiting time
    
    // for SJF: alpha
    public static double alpha = 1d/3d;
    
    
    public static void main(String[] args) {
        
        
        
        //* to test different alpha with same data
        processList.add(new Process(1, 223012, 30));
        processList.add(new Process(2, 195843, 35));
        processList.add(new Process(3, 126123, 40));
        processList.add(new Process(4, 155318, 45));
        processList.add(new Process(5, 138795, 50));
        processList.add(new Process(6, 205369, 55));
        processList.add(new Process(7, 120721, 60));
        processList.add(new Process(8, 173749, 65));
        processList.add(new Process(9, 150633, 70));
        processList.add(new Process(10, 207356, 75));
        //*/
        int i;
        for(; !processList.isEmpty();) {
            
            // import processes in ready queue
            boolean _empty = true;
            i = 0;
            while(i < processList.size()) {
                Process _p = processList.get(i);
                if(_p.nextStartTime <= currentTime) {
                    _p.place = "ready queue";
                    processList.set(i, _p);
                    _empty = false;
                }
                i++;
            }
            // if ready queue is empty, let time pass to the nextStartTime of next process
            if(_empty) {
                processList = Sort.sort("nextStartTime", processList);
                currentTime = processList.get(0).nextStartTime;
                endingTime = currentTime;
                continue;
            }
            
            // now have at least one process in ready queue
            
            // SJF
            processList = Sort.sort("predictTime", processList);
            // get next process in ready queue
            Process prs = null;
            i=0;
            while(i < processList.size()) {
                prs = processList.get(i);
                if(prs.place.equals("ready queue")) {
                    processList.remove(i);
                    break;
                }
                prs = null;
                i++;
            }
            
            // now CPU starts running the process which is stored in prs
            prs.waitingTime = prs.waitingTime + currentTime - prs.nextStartTime;
            int _interIOTime = TimeFinder.exponentiallyIOTime(prs.meanInterIOInterval);
            prs.thisRunningTime = TimeFinder.doubleToInt(Math.min(_interIOTime, prs.leftBurstTime));
            prs.leftBurstTime = prs.leftBurstTime - prs.thisRunningTime;
            prs.lastEndTime = currentTime + prs.thisRunningTime;
            prs.turnaroundTime = prs.lastEndTime - startingTime;
            prs.runningTime = prs.runningTime + prs.thisRunningTime;
            // predict next burst time
            prs.predictTime = TimeFinder.doubleToInt(alpha*(double)prs.thisRunningTime + (1d-alpha)*(double)prs.predictTime);
            
            // prs is finished
            if(prs.leftBurstTime <= 0) {
                recordArray[prs.id][0] = prs.turnaroundTime;
                recordArray[prs.id][1] = prs.runningTime;
                recordArray[prs.id][2] = prs.waitingTime;
            }
            // prs is not finished and brought to I/O queue
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
        int sumTurnaroundTime = 0;
        int sumRunningTime = 0;
        int sumWaitingTime = 0;
        i=1;
        while(i <= numberOfProcess) {
            sumTurnaroundTime += recordArray[i][0];
            sumRunningTime += recordArray[i][1];
            sumWaitingTime += recordArray[i][2];
            i++;
        }
        double CPUUtilization = TimeFinder.DecimalPlace((double)sumRunningTime/((double)endingTime-(double)startingTime)*100d, 2);  // %
        double throughput = TimeFinder.DecimalPlace((double)numberOfProcess/(((double)endingTime-(double)startingTime)/1000d/60d), 2);  // processes per minute
        double averageTurnaroundTime = TimeFinder.DecimalPlace((double)sumTurnaroundTime/1000d/60d /10d, 2);  // minutes per process
        double averageWaitingTime = TimeFinder.DecimalPlace((double)sumWaitingTime/1000d/60d /10d, 2);  // minutes per process

//        System.out.println("CPU utilization: "+CPUUtilization+" %");
//        System.out.println("Throughput: "+throughput+" processes/minute");
//        System.out.println("Average turnaround time: "+averageTurnaroundTime+" minutes/process");
//        System.out.println("Average waiting time: "+averageWaitingTime+" minutes/process");
        
        System.out.println("CPU utilization      Throughput      Average turnaround time      Average waiting time");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("   "+CPUUtilization+"                "+throughput+"            "+averageTurnaroundTime+"                         "+averageWaitingTime+"\n\n");
        System.out.println("\n");
        System.out.println("Starting Time is =" + startingTime+"           "+"Ending Time is =" + endingTime);
        System.out.println("\n");
        
        System.out.println("Total Number processes = "+numberOfProcess+"\n");
        
        System.out.println("CPU utilization SJF ALGO = "+CPUUtilization+"% \n");
        System.out.println("Throughput(the average number of tasks finished in a given amount of time)= "+throughput+" process/min \n");
        System.out.println("Average turnaround time (the length of time it typically took to complete a task)= "+averageTurnaroundTime+" min/process \n");
        System.out.println("waiting time: "+averageWaitingTime+" minutes/process");


    }

}
