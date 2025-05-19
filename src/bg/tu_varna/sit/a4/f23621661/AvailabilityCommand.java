package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class AvailabilityCommand implements Command {
    private Hotel hotel;

    public AvailabilityCommand(Hotel hotel) {

        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        LocalDate date = args.length >= 2 ? DateUtils.parse(args[1]) : LocalDate.now();
        hotel.availability(date);
    }
}
