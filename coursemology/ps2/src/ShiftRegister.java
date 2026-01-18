///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    private final int size;
    private final int tap;
    private int[] register;

    ShiftRegister(int size, int tap) {
        this.size = size;
        this.tap = tap;
        this.register = new int[size];
    }

    @Override
    public void setSeed(int[] seed) {
        if (seed.length != size) {
            throw new IllegalArgumentException("Seed length must match register size");
        }
        for (int i = 0; i < size; i++) {
            register[i] = seed[i];
        }
    }

    @Override
    public int shift() {
        // msb is at index size - 1
        int msb = register[size - 1];
        int tapBit = register[tap];

        // xor for feedback
        int feedback = msb ^ tapBit;

        // shift left (towards the msb)
        for (int i = size - 1; i > 0; i--) {
            register[i] = register[i - 1];
        }

        // set lsb to feedback
        register[0] = feedback;

        return feedback;
    }

    @Override
    public int generate(int k) {
        int result = 0;

        for (int i = 0; i < k; i++) {
            result = (result << 1) | shift();
        }

        return result;
    }
}
