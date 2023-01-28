package Main;

public class TimeFinder {
    public static double DecimalPlace(double num, int N) {
        return (double) (Math.round(num * Math.pow(10, N)) / Math.pow(10, N));
    }
    
    public static int executionTime() {
        return TimeFinder.doubleToInt((Math.random()*120000)+120000);
    }
    
  
    
    public static int exponentiallyIOTime(int perLambda) {
        int m = 65536;
        double Z = (-perLambda)*Math.log((Math.random()*m+1)/m);
        return TimeFinder.doubleToInt(Z);
    }
    
    @SuppressWarnings("removal")
  	public static int doubleToInt(double num) {
          return (new Double(num)).intValue();
      }
    
    
}
