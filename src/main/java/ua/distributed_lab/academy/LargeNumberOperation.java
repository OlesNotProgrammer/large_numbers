package ua.distributed_lab.academy;

import java.util.function.BiFunction;

import ua.distributed_lab.academy.interfaces.NumberOperation;

public class LargeNumberOperation implements NumberOperation<LargeNumber> {
    private LargeNumber invertNumber(LargeNumber number) {
        int[] byteArray = new int[number.numberOfBytes()];

        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (LargeNumber.BYTE_MAX_VALUE - number.getByte(i));
        }

        return new LargeNumber(byteArray);
    }

    @Override
    public LargeNumber INV(LargeNumber number) {
        return invertNumber(number);
    }

    private int longestNumberOfBytes(LargeNumber numberOne, LargeNumber numberTwo) {
        if (numberOne.numberOfBytes() < numberTwo.numberOfBytes()) {
            return numberTwo.numberOfBytes();
        }

        return numberOne.numberOfBytes();
    }

    private int[] getBitArray(int byteValue) {
        int[] bitArray = new int[LargeNumber.BITS_IN_ONE_BYTE];

        int powerOfTwo = (LargeNumber.BYTE_MAX_VALUE + 1) / 2;

        for (int i = bitArray.length - 1; i > -1; i--) {
            bitArray[i] = 0;

            if (byteValue >= powerOfTwo) {
                bitArray[i] = 1;
                byteValue -= powerOfTwo;
            }

            powerOfTwo /= 2;
        }

        return bitArray;
    }

    private LargeNumber executeLogicalOperation(LargeNumber numberOne,
            LargeNumber numberTwo,
            BiFunction<Integer, Integer, Boolean> function) {
        int[] byteArray = new int[longestNumberOfBytes(numberOne, numberTwo)];

        int powerOfTwo;

        int[] bitArrayOne;
        int[] bitArrayTwo;

        for (int i = 0; i < byteArray.length; i++) {
            powerOfTwo = 1;

            bitArrayOne = getBitArray(numberOne.getByte(i));
            bitArrayTwo = getBitArray(numberTwo.getByte(i));

            for (int j = 0; j < LargeNumber.BITS_IN_ONE_BYTE; j++) {
                if (function.apply(bitArrayOne[j], bitArrayTwo[j])) {
                    byteArray[i] += powerOfTwo;
                }

                powerOfTwo *= 2;
            }
        }

        return new LargeNumber(byteArray);
    }

    @Override
    public LargeNumber XOR(LargeNumber numberOne, LargeNumber numberTwo) {
        return executeLogicalOperation(numberOne,
            numberTwo,
            (bitOne, bitTwo) -> bitOne != bitTwo);
    }

    @Override
    public LargeNumber OR(LargeNumber numberOne, LargeNumber numberTwo) {
        return executeLogicalOperation(numberOne,
            numberTwo,
            (bitOne, bitTwo) -> (bitOne == 1) || (bitTwo == 1));
    }

    @Override
    public LargeNumber AND(LargeNumber numberOne, LargeNumber numberTwo) {
        return executeLogicalOperation(numberOne,
            numberTwo,
            (bitOne, bitTwo) -> (bitOne == 1) && (bitTwo == 1));
    }

    @Override
    public LargeNumber shiftR(LargeNumber number, LargeNumber to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented me thod 'shiftR'");
    }

    @Override
    public LargeNumber shiftL(LargeNumber number, LargeNumber to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented me thod 'shiftL'");
    }

    private LargeNumber executeArithmeticOperation(LargeNumber numberOne,
        LargeNumber numberTwo,
        BiFunction<Integer, Integer, Integer> function) {
        int[] byteArray = new int[longestNumberOfBytes(numberOne, numberTwo)];

        int collisionPoint = LargeNumber.BYTE_MAX_VALUE + 1;

        int carryBit = 0;
        int byteRemainder;

        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = function.apply(numberOne.getByte(i), numberTwo.getByte(i)) + carryBit;
            carryBit = 0;

            if (byteArray[i] >= collisionPoint) {
                byteRemainder = byteArray[i] % collisionPoint;

                carryBit = (byteArray[i] - byteRemainder) / collisionPoint;
                byteArray[i] = byteRemainder;
            }
        }

        return new LargeNumber(byteArray);
    }

    @Override
    public LargeNumber ADD(LargeNumber numberOne, LargeNumber numberTwo) {
        return executeArithmeticOperation(numberOne,
            numberTwo,
            (byteOne, byteTwo) -> byteOne + byteTwo);
    }

    private LargeNumber changeNumberSign(LargeNumber number) {
        return ADD(invertNumber(number), new LargeNumber(new int[]{1}));
    }

    @Override
    public LargeNumber SUB(LargeNumber numberOne, LargeNumber numberTwo) {
        return ADD(numberOne, changeNumberSign(numberTwo));
    }

    @Override
    public LargeNumber MOD(LargeNumber numberOne, LargeNumber numberTwo) {
        return executeArithmeticOperation(numberOne,
            numberTwo,
            (byteOne, byteTwo) -> byteOne * byteTwo);
    }
}
