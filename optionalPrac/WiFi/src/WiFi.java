import java.util.Arrays;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {
        Arrays.sort(houses);
        double low = 0.0;
        double high = houses[houses.length - 1] - houses[0];
        
        while (high - low > 1e-6) {
            double mid = low + (high - low) / 2.0;
            
            if (coverable(houses, numOfAccessPoints, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    /**
     * Implement your solution here
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
       
        int i = 0;

        while (i < houses.length && numOfAccessPoints > 0) {
            double range = houses[i] + 2 * distance;
           
            while (i < houses.length && houses[i] <= range) {
                i++;
            }
            
            numOfAccessPoints--; 
        }
         
        return i == houses.length;
    }
}
