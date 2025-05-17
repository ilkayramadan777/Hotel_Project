package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

public class ReportCommand implements Command {
    private Hotel hotel;

    public ReportCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: report <from> <to>");
            return;
        }

        LocalDate from = DateUtils.parse(args[1]);
        LocalDate to = DateUtils.parse(args[2]);
        hotel.report(from, to);
    }
}
