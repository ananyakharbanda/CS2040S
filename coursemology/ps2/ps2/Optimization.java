
public class Optimization {

    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * @param dataArray an array of integers which changes direction at most once
     * @return the maximum item in dataArray, or 0 if input is invalid
     */
    public static int searchMax(int[] dataArray) {

        // Invalid input handling as per specification
        if (dataArray == null || dataArray.length == 0) {
            return 0;
        }

        if (dataArray.length == 1) {
            return dataArray[0];
        }

        int n = dataArray.length;

        if (dataArray[0] < dataArray[1] && dataArray[n - 2] > dataArray[n - 1]) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (dataArray[mid] < dataArray[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return dataArray[left];
        }
        return Math.max(dataArray[0], dataArray[n - 1]);
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
