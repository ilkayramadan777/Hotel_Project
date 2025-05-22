package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за временно отбелязване на стая като недостъпна.
 * Използва се например при ремонт или блокиране на резервации.
 */
public class UnavailableCommand implements Command {

    private final Hotel hotel;

    /**
     * Създава нова команда за недостъпност на стая.
     *
     * @param hotel хотелският обект
     */
    public UnavailableCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява отбелязване на стая като временно недостъпна.
     *
     * @param args аргументи:
     *             args[1] – номер на стаята,
     *             args[2] – от дата,
     *             args[3] – до дата,
     *             args[4] – бележка
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Въведете: unavailable <стая> <от> <до> <бележка>");
            return;
        }

        try {
            int room = Integer.parseInt(args[1]);
            if (!hotel.roomExists(room)) {
                System.out.println("Стая с номер " + room + " не съществува.");
                return;
            }

            LocalDate from = DateUtils.parse(args[2]);
            LocalDate to = DateUtils.parse(args[3]);
            String note = args[4];

            hotel.unavailable(room, from, to, note);
        } catch (Exception e) {
            System.out.println("Грешка при въвеждане на данните. Проверете формата и стойностите.");
        }
    }
}
