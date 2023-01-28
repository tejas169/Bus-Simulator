package GanttChart;

public class Process {
	public int priority = 0;
	public double arrivalTime = 0d;
    public int id = 0;
    
  
    public double turnaroundTime = 0d;
    public double waitingTime = 0d;
    public double burstTime = 0d;
    public double lastStop = 0d;
  
    public String place  = "ready queue";
    
    public Process(int id, int priority, double burstTime) {
        this.id = id;
        this.priority = priority;
        this.burstTime = burstTime;
    }
}

