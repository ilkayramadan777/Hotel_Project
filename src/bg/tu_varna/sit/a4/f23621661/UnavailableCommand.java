package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code UnavailableCommand} представлява команда за временно обявяване на стая
 * като недостъпна за резервации (например при ремонт или друга причина).
 * Част е от реализацията на шаблона Command.
 */
public class UnavailableCommand implements Command {

    /**
     * Хотелският обект, в който се изпълнява операцията.
     */
    private final Hotel hotel;

    /**
     * Конструктор за създаване на команда {@code UnavailableCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, към който се прилага командата
     */
    public UnavailableCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за отбелязване на дадена стая като временно недостъпна.
     * При липсващи или невалидни данни се извежда съобщение за грешка.
     * @param args масив от аргументи – {@code args[1]} е номер на стая,
     *             {@code args[2]} и {@code args[3]} са дати, {@code args[4]} е бележка
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
