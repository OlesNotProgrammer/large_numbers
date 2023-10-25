package ua.distributed_lab.academy.interfaces;

public interface NumberOperation <T> {
    T INV(T number);

    T XOR(T numberOne, T numberTwo);

    T OR(T numberOne, T numberTwo);

    T AND(T numberOne, T numberTwo);

    T shiftR(T number, T to);

    T shiftL(T number, T to);

    T ADD(T numberOne, T numberTwo);

    T SUB(T numberOne, T numberTwo);

    T MOD(T numberOne, T numberTwo);
}
