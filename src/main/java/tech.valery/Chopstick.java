package tech.valery;

public class Chopstick {
    private int idNumber;

    public Chopstick(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "idNumber=" + idNumber +
                '}';
    }
}
