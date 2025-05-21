package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code AvailabilityCommand} представлява команда за проверка на наличността в хотела за дадена дата.
 * Реализира интерфейса {@code Command} като част от шаблона Command.
 */
public class AvailabilityCommand implements Command {

    /**
     * Хотелът, към който се отнася командата.
     */
    private Hotel hotel;

    /**
     * Конструктор за създаване на нова команда {@code AvailabilityCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, към който ще се прилага командата
     */
    public AvailabilityCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за проверка на наличностите за дадена дата.
     * Ако датата не е предоставена, използва текущата дата.
     *
     * @param args масив от аргументи, където {@code args[1]} може да съдържа дата във формат yyyy-MM-dd
     */
    @Override
    public void execute(String[] args) {
        LocalDate date;
        try {
            date = args.length >= 2 ? DateUtils.parse(args[1]) : LocalDate.now();
        } catch (Exception e) {
            System.out.println("Грешен формат на дата. Използвайте yyyy-MM-dd.");
            return;
        }
        hotel.availability(date);
    }
}
