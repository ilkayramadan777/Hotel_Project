package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за търсене на свободна хотелска стая
 * с поне определен брой легла за даден период.
 */
public class FindCommand implements Command {

    private Hotel hotel;

    public FindCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява търсене на стая по зададени критерии.
     * Ако няма подходяща, извежда съобщение.
     *
     * @param args аргументи:
     *             args[1] – брой легла,
     *             args[2] – начален период,
     *             args[3] – краен период
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Въведете: find <брой легла> <от дата> <до дата>");
            return;
        }

        try {
            int beds = Integer.parseInt(args[1]);
            if (beds <= 0) {
                System.out.println("Грешка: Броят на леглата трябва да е положително число.");
                return;
            }

            LocalDate from = DateUtils.parse(args[2]);
            LocalDate to = DateUtils.parse(args[3]);

            hotel.find(beds, from, to).ifPresentOrElse(
                    room -> System.out.println("Намерена свободна стая: " + room),
                    () -> System.out.println("Няма свободни стаи с поне " + beds + " легла за избрания период.")
            );
        } catch (Exception e) {
            System.out.println("Грешни входни данни. Уверете се, че датите са във формат yyyy-MM-dd и броят легла е цяло число.");
        }
    }
}
