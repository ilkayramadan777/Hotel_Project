package bg.tu_varna.sit.a4.f23621661;

/**
 * Команда за освобождаване на хотелска стая.
 * Ако стаята не съществува, се извежда съобщение за грешка.
 */
public class CheckoutCommand implements Command {

    /**
     * Хотелът, в който се изпълнява командата.
     */
    private Hotel hotel;

    /**
     * Създава нова команда за освобождаване на стая.
     *
     * @param hotel хотелският обект, върху който се прилага командата
     */
    public CheckoutCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява освобождаване на стая по подадения номер.
     *
     * @param args масив от аргументи:
     *             args[1] – номер на стаята
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Въведете: checkout <стая>");
            return;
        }

        int room = Integer.parseInt(args[1]);

        if (!hotel.roomExists(room)) {
            System.out.println("Стая с номер " + room + " не съществува.");
            return;
        }

        hotel.checkout(room);
    }
}
