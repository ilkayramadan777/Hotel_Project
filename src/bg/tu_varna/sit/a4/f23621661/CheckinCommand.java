package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code CheckinCommand} представлява команда за настаняване на гости в хотел.
 * Част е от реализацията на шаблона Command.
 */
public class CheckinCommand implements Command {

    /**
     * Обект от тип {@code Hotel}, към който ще се изпълни командата.
     */
    private Hotel hotel;

    /**
     * Конструктор за създаване на нова инстанция на {@code CheckinCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, към който се отнася командата
     */
    public CheckinCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за настаняване в хотел.
     * Ако не е подаден брой гости, се използва стойност по подразбиране 1.
     *
     * @param args масив от аргументи, подадени от потребителя
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
        int guests = args.length >= 6 ? Integer.parseInt(args[5]) : 1;

        hotel.checkin(room, from, to, note, guests);
    }
}
