import java.util.Random;
import java.util.Arrays;

public class SortingTester {

    public static boolean checkSort(ISort sorter, int size) {
        Random r = new Random();

        for (int trial = 0; trial < 20; trial++) {
            KeyValuePair[] arr = new KeyValuePair[size];

            for (int i = 0; i < size; i++) {
                arr[i] = new KeyValuePair(r.nextInt(), r.nextInt());
            }

            sorter.sort(arr);

            for (int i = 1; i < size; i++) {
                if (arr[i - 1].getKey() > arr[i].getKey()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isStable(ISort sorter, int size) {
        KeyValuePair[] arr = new KeyValuePair[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new KeyValuePair(i % 5, i);
        }

        sorter.sort(arr);

        for (int i = 1; i < size; i++) {
            if (arr[i].getKey() == arr[i - 1].getKey() &&
                    arr[i].getValue() < arr[i - 1].getValue()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFastSorted(ISort sorter, int size) {
        KeyValuePair[] sorted = new KeyValuePair[size];
        KeyValuePair[] random = new KeyValuePair[size];
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            sorted[i] = new KeyValuePair(i, i);
            random[i] = new KeyValuePair(r.nextInt(), r.nextInt());
        }

        long sortedCost = sorter.sort(sorted);
        long randomCost = sorter.sort(random);

        return sortedCost * 10 < randomCost;
    }

    public static boolean isConstPasses(ISort sorter, int size) {
        int half = size / 2;
        Random r = new Random();

        KeyValuePair[] small = new KeyValuePair[half];
        KeyValuePair[] big = new KeyValuePair[size];

        for (int i = 0; i < size; i++) {
            KeyValuePair kv = new KeyValuePair(r.nextInt(), r.nextInt());
            big[i] = kv;
            if (i < half) {
                small[i] = new KeyValuePair(kv.getKey(), kv.getValue());
            }
        }

        long smallCost = sorter.sort(small);
        long bigCost = sorter.sort(big);

        return bigCost > 3 * smallCost;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int size = Math.max(5000, r.nextInt(30000) + 1);

        ISort[] sorters = {
                new SorterA(),
                new SorterB(),
                new SorterC(),
                new SorterD(),
                new SorterE()
        };

        char name = 'A';

        for (ISort ignored : sorters) {
            ISort sorter =
                    name == 'A' ? new SorterA() :
                            name == 'B' ? new SorterB() :
                                    name == 'C' ? new SorterC() :
                                            name == 'D' ? new SorterD() :
                                                    new SorterE();

            System.out.print("Sorter" + name + ": ");

            if (!checkSort(sorter, size)) {
                System.out.println("Evil Sort");
            } else if (isStable(sorter, size) && isFastSorted(sorter, size)) {
                System.out.println("Insertion Sort");
            } else if (isConstPasses(sorter, size)) {
                System.out.println("Selection Sort");
            } else if (isStable(sorter, size)) {
                System.out.println("Merge Sort");
            } else {
                System.out.println("Quick Sort");
            }

            name++;
        }
    }
}
