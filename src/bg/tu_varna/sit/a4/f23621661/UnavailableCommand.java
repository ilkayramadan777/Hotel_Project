package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class UnavailableCommand implements Command {
    private Hotel hotel;

    public UnavailableCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: unavailable <room> <from> <to> <note>");
            return;
        }

        int room = Integer.parseInt(args[1]);
        LocalDate from = DateUtils.parse(args[2]);
        LocalDate to = DateUtils.parse(args[3]);
        String note = args[4];
        hotel.unavailable(room, from, to, note);
    }
}

