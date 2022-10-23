enum RomeNumber {
    C((byte) 100), L((byte) 50), X((byte) 10), V((byte) 5), I((byte) 1);

    final byte value;

    RomeNumber(byte value) {
        this.value = value;
    }

    byte getValueOfRomeNumber() {
        return value;
    }
}
