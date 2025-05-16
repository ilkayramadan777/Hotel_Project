package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class FindUrgentCommand implements Command {
    private Hotel hotel;

    public FindUrgentCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: find! <beds> <from> <to>");
            return;
        }

        int beds = Integer.parseInt(args[1]);
        LocalDate from = DateUtils.parse(args[2]);
        LocalDate to = DateUtils.parse(args[3]);

        hotel.findUrgent(beds, from, to).ifPresentOrElse(
                r -> System.out.println("Urgently found room (with rearrangement): " + r),
                () -> System.out.println("No room could be found, even with rearrangement.")
        );
    }
}

