package GanttChart;

public class TimeFinder {
    public static double DecimalPlace(double num, int N) {
        return (double) (Math.round(num * Math.pow(10, N)) / Math.pow(10, N));
    }
    public static int doubleToInt(double num) {
        return (new Double(num)).intValue();
    }
    
    
    
    
    
    
    
}

