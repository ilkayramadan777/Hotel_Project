package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за спешно намиране на свободна стая
 * чрез евентуално пренареждане на резервации.
 */
public class FindUrgentCommand implements Command {

    private Hotel hotel;

    public FindUrgentCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Извършва търсене на стая с поне зададен брой легла за периода.
     * При нужда пренарежда до 2 резервации. Ако не е възможно – извежда съобщение.
     *
     * @param args аргументи:
     *             args[1] – брой легла,
     *             args[2] – начална дата,
     *             args[3] – крайна дата
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Въведете: find! <брой легла> <от дата> <до дата>");
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

            hotel.findUrgent(beds, from, to).ifPresentOrElse(
                    room -> System.out.println("Спешно намерена свободна стая чрез пренареждане: " + room),
                    () -> System.out.println("Няма подходяща стая, дори със спешно пренареждане.")
            );
        } catch (Exception e) {
            System.out.println("Грешни входни данни. Уверете се, че датите са във формат yyyy-MM-dd и броят легла е цяло число.");
        }
    }
}
