package mypackage;

public class Player {
    private int reflexTime;
    private final int serial;
    private int rest;
    public boolean flagg=false;

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getRest() {
        return rest;
    }

    public Player(int reflexTime, int serial) {
        this.reflexTime = reflexTime;
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "player " + serial + ',';
    }

    public void setReflexTime(int reflexTime) {
        this.reflexTime = reflexTime;
    }

    public int getReflexTime() {
        return reflexTime;
    }

    public int getSerial() {
        return serial;
    }
}
