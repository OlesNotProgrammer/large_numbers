package ua.distributed_lab.academy;

public class Main {
    public static void main(String[] args) {
        // String testHex1 = "51bf608414ad5726a3c1bec098f77b1b54ffb2787f8d528a74c1d7fde6470ea4";
        // String testHex2 = "403db8ad88a3932a0b7e8189aed9eeffb8121dfac05c3512fdb396dd73f6331c";
        // String testHex3 = "1182d8299c0ec40ca8bf3f49362e95e4ecedaf82bfd167988972412095b13db8";

        LargeNumberOperation o = new LargeNumberOperation();

        // System.out.println(testHex3.equals(o.XOR(new LargeNumber(testHex1), new LargeNumber(testHex2)).getHex()));

        System.out.println(o.MOD(new LargeNumber("ff"), new LargeNumber("f")).getHex());
    }
}
