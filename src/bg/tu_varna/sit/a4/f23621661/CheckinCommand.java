package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за настаняване на гости в хотелска стая за определен период.
 * Ако не е подаден брой гости, се използва броят легла в стаята.
 */
public class CheckinCommand implements Command {

    /**
     * Хотелът, в който се извършва настаняването.
     */
    private Hotel hotel;

    /**
     * Създава нова команда за настаняване.
     *
     * @param hotel хотелският обект, към който се прилага командата
     */
    public CheckinCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява настаняване на гости в стая.
     * Валидира номера на стаята и броя на гостите спрямо капацитета ѝ.
     *
     * @param args масив от аргументи:
     *             args[1] – номер на стая,
     *             args[2] – начална дата,
     *             args[3] – крайна дата,
     *             args[4] – бележка,
     *             args[5] – брой гости (по избор)
     */
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

        if (!hotel.roomExists(room)) {
            System.out.println("Грешка: Стая с номер " + room + " не съществува.");
            return;
        }

        int roomBeds = hotel.getRoomBeds(room);
        int guests;

        if (args.length >= 6) {
            guests = Integer.parseInt(args[5]);
            if (guests > roomBeds) {
                System.out.println("Грешка: Стая " + room + " има само " + roomBeds + " легла. Изберете друга стая или намалете броя на гостите.");
                return;
            }
        } else {
            guests = roomBeds;
        }

        hotel.checkin(room, from, to, note, guests);
    }
}
