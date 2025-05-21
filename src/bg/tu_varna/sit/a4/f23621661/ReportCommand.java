package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code ReportCommand} представлява команда за извеждане на справка
 * относно използването на стаите в хотела за зададен период.
 * Част е от реализацията на шаблона Command.
 */
public class ReportCommand implements Command {

    /**
     * Обект от тип {@code Hotel}, за който ще се извежда справката.
     */
    private final Hotel hotel;

    /**
     * Конструктор за създаване на нова команда {@code ReportCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, към който се прилага командата
     */
    public ReportCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за извеждане на справка за заетост на стаите
     * в даден времеви интервал.
     * При невалиден формат на датите се извежда съобщение за грешка.
     * @param args масив от аргументи, където {@code args[1]} и {@code args[2]} са дати във формат yyyy-MM-dd
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Въведете: report <от дата> <до дата>");
            return;
        }

        try {
            LocalDate from = DateUtils.parse(args[1]);
            LocalDate to = DateUtils.parse(args[2]);
            hotel.report(from, to);
        } catch (Exception e) {
            System.out.println("Невалиден формат на дата. Използвайте yyyy-MM-dd.");
        }
    }
}
