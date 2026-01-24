public class Main {
    static int MysteryFunction(int argA, int argB) {
        int c = 1;
        int d = argA;
        int e = argB;
        while (e > 0) {
            if (2 * (e / 2) != e) {
                c = c * d;
            }
            d = d * d;
            e = e / 2;
        }
        return c;
    }


    public static void main(String args[]) {
//        int output = Main.MysteryFunction(5, 5);
//        System.out.printf("The answer is: " + output + ".");
//
        int[] array = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter = new ShiftRegister(9, 7);
        shifter.setSeed(array);
        for (int i = 0; i < 10; i++) {
            int j = shifter.shift();
            System.out.print(j);
        }

        int[] array2 = new int[] {0, 1, 0, 1, 1, 1, 1, 0, 1};
        ShiftRegister shifter2 = new ShiftRegister(9, 7);
        shifter2.setSeed(array2);
        for (int i = 0; i < 10; i++) {
            int j = shifter2.generate(3);
            System.out.println(j);
        }
    }
}