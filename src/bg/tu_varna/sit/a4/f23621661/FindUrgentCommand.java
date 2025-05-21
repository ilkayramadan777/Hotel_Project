package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code FindUrgentCommand} представлява команда за спешно намиране на свободна хотелска стая
 * с поне определен брой легла за зададен период чрез евентуално пренареждане на текущите резервации.
 * Част е от реализацията на шаблона Command.
 */
public class FindUrgentCommand implements Command {

    /**
     * Обект от тип {@code Hotel}, в който ще се търсят стаи.
     */
    private Hotel hotel;

    /**
     * Конструктор за създаване на нова команда {@code FindUrgentCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, към който се отнася търсенето
     */
    public FindUrgentCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за спешно търсене на свободна стая.
     * Ако няма подходяща стая, дори и при пренареждане, се извежда съобщение.
     * @param args масив от аргументи, където:
     *             {@code args[1]} е брой легла (цяло положително число),
     *             {@code args[2]} и {@code args[3]} са дати във формат {@code yyyy-MM-dd}
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
                System.out.println("Броят на леглата трябва да е положително число.");
                return;
            }

            LocalDate from = DateUtils.parse(args[2]);
            LocalDate to = DateUtils.parse(args[3]);

            hotel.findUrgent(beds, from, to).ifPresentOrElse(
                    r -> System.out.println("Спешно намерена стая (с пренареждане): " + r),
                    () -> System.out.println("Няма налична стая дори със спешно пренареждане.")
            );
        } catch (Exception e) {
            System.out.println("Грешни входни данни. Уверете се, че датите са във формат yyyy-MM-dd и броят легла е число.");
        }
    }
}
