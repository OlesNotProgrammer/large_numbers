package ua.distributed_lab.academy;

public class LargeNumber {
    public final static int BITS_IN_ONE_BYTE = 8;
    public final static int BYTE_MAX_VALUE = 255;

    private final static int ONE_BYTE_HEX_LENGTH = 2;

    private int[] byteArray;

    public LargeNumber(int[] byteArray) throws NumberFormatException {
        if (byteArray == null) {
            this.byteArray = new int[1];
            return;
        }

        this.byteArray = validateByteArray(byteArray.clone());
    }

    public LargeNumber(String hex) throws NumberFormatException {
        if (hex == null) {
            byteArray = new int[1];
            return;
        }

        byteArray = createByteArray(hex);
    }

    private String prepearHex(String hex) {
        if (hex.length() % ONE_BYTE_HEX_LENGTH != 0) {
            return "0" + hex;
        }

        return hex;
    }

    private int[] validateByteArray(int[] byteArray) throws NumberFormatException {
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] > BYTE_MAX_VALUE) || (byteArray[i] < 0)) {
                throw new NumberFormatException("Value out of range. Value" + byteArray[i] + ". Range: 0-" + BYTE_MAX_VALUE + ".");
            }
        }

        return byteArray;
    }

    private int[] createByteArray(String hex) throws NumberFormatException {
        String preparedHex = prepearHex(hex);
        int[] byteArray = new int[preparedHex.length() / ONE_BYTE_HEX_LENGTH];

        int charId;

        for (int i = 0; i < byteArray.length; i++) {
            charId = (byteArray.length - (i + 1)) * ONE_BYTE_HEX_LENGTH;

            byteArray[i] = Integer.parseInt(preparedHex.substring(charId, charId + ONE_BYTE_HEX_LENGTH), 16);
        }

        return validateByteArray(byteArray);
    }

    public int numberOfBytes() {
        return byteArray.length;
    }

    public int getByte(int index) {
        if ((index >= byteArray.length) || (index < 0)) {
            return 0;
        }

        return byteArray[index];
    }

    public String getHex() {
        StringBuilder hex = new StringBuilder("");

        String hexToken;

        for (int i = byteArray.length - 1; i > -1; i--) {
            hexToken = Integer.toHexString(byteArray[i]);

            if (hexToken.length() < ONE_BYTE_HEX_LENGTH) {
                hex.append("0");
            }

            hex.append(hexToken);
        }

        return hex.toString();
    }
}
