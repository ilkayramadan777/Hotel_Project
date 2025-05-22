package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Команда за извеждане на справка за използване на стаите
 * в даден период от време.
 */
public class ReportCommand implements Command {

    /**
     * Хотелът, за който се извежда справката.
     */
    private final Hotel hotel;

    /**
     * Създава нова команда за справка.
     *
     * @param hotel хотелският обект
     */
    public ReportCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява справка за заетост на стаите за зададен период.
     *
     * @param args аргументи:
     *             args[1] – начална дата,
     *             args[2] – крайна дата
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
