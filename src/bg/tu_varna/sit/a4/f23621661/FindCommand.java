package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class FindCommand implements Command {
    private Hotel hotel;

    public FindCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        int beds = Integer.parseInt(args[1]);
        LocalDate from = DateUtils.parse(args[2]);
        LocalDate to = DateUtils.parse(args[3]);

        hotel.find(beds, from, to).ifPresentOrElse(
                r -> System.out.println("Available room: " + r),
                () -> System.out.println("No available room found.")
        );
    }
}
