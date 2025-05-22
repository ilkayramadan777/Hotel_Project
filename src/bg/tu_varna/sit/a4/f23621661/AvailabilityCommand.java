package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за проверка на свободните стаи в хотела за конкретна дата.
 * Ако не е подадена дата, използва текущата.
 */
public class AvailabilityCommand implements Command {

    /**
     * Хотелът, в който се извършва проверката.
     */
    private Hotel hotel;

    /**
     * Създава нова команда за проверка на наличностите.
     *
     * @param hotel хотелският обект, към който се прилага командата
     */
    public AvailabilityCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява проверката. Ако потребителят е подал дата, тя се използва.
     * В противен случай се използва текущата дата.
     *
     * @param args масив с аргументи, където args[1] може да съдържа дата (yyyy-MM-dd)
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
