package bg.tu_varna.sit.a4.f23621661;

public class Room {
    private int number;
    private int beds;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    public int getNumber() {
        return number;
    }

    public int getBeds() {
        return beds;
    }

    @Override
    public String toString() {
        return "Room " + number + " (" + beds + " beds)";
    }
}

