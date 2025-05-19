package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class CheckinCommand implements Command {
    private Hotel hotel;

    public CheckinCommand(Hotel hotel) {

        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Въведете: checkin <стая> <от> <до> <бележка> [<гости>]");
            return;
        }

        int room = Integer.parseInt(args[1]);
        LocalDate from = DateUtils.parse(args[2]);
        LocalDate to = DateUtils.parse(args[3]);
        String note = args[4];
        int guests = args.length >= 6 ? Integer.parseInt(args[5]) : 1;
        hotel.checkin(room, from, to, note, guests);
    }
}

