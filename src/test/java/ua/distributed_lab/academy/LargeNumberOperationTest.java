package ua.distributed_lab.academy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargeNumberOperationTest {
    LargeNumberOperation largeNumberOperation = new LargeNumberOperation();

    @Test
    void XOR() {
        LargeNumber numberOne = new LargeNumber("51bf608414ad5726a3c1bec098f77b1b54ffb2787f8d528a74c1d7fde6470ea4");
        LargeNumber numberTwo = new LargeNumber("403db8ad88a3932a0b7e8189aed9eeffb8121dfac05c3512fdb396dd73f6331c");

        String result = "1182d8299c0ec40ca8bf3f49362e95e4ecedaf82bfd167988972412095b13db8";

        assertEquals(result, largeNumberOperation.XOR(numberOne, numberTwo).getHex());
    }

    @Test
    void ADD() {
        LargeNumber numberOne = new LargeNumber("36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80");
        LargeNumber numberTwo = new LargeNumber("70983d692f648185febe6d6fa607630ae68649f7e6fc45b94680096c06e4fadb");

        String result = "a78865c13b14ae4e25e90771b54963ee2d68c0a64d4a8ba7c6f45ee0e9daa65b";

        assertEquals(result, largeNumberOperation.ADD(numberOne, numberTwo).getHex());
    }

    @Test
    void SUB() {
        LargeNumber numberOne = new LargeNumber("33ced2c76b26cae94e162c4c0d2c0ff7c13094b0185a3c122e732d5ba77efebc");
        LargeNumber numberTwo = new LargeNumber("22e962951cb6cd2ce279ab0e2095825c141d48ef3ca9dabf253e38760b57fe03");

        String result = "10e570324e6ffdbc6b9c813dec968d9bad134bc0dbb061530934f4e59c2700b9";

        assertEquals(result, largeNumberOperation.SUB(numberOne, numberTwo).getHex());
    }

    @Test
    void MOD() {
        LargeNumber numberOne = new LargeNumber("7d7deab2affa38154326e96d350deee1");
        LargeNumber numberTwo = new LargeNumber("97f92a75b3faf8939e8e98b96476fd22");

        String result = "34bbb5d5515a4c386f9ea6d9bae953e2";

        assertEquals(result, largeNumberOperation.MOD(numberOne, numberTwo).getHex());
    }
}
