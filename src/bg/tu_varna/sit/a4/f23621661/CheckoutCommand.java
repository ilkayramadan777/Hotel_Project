package bg.tu_varna.sit.a4.f23621661;

/**
 * Класът {@code CheckoutCommand} представлява команда за освобождаване на стая в хотела.
 * Част е от реализацията на шаблона Command.
 */
public class CheckoutCommand implements Command {

    /**
     * Хотелът, в който ще се изпълнява командата.
     */
    private Hotel hotel;

    /**
     * Конструктор за създаване на нова инстанция на {@code CheckoutCommand}.
     *
     * @param hotel обект от тип {@code Hotel}, върху който ще се прилага командата
     */
    public CheckoutCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Изпълнява командата за освобождаване на стая.
     * Ако стаята не съществува, извежда съобщение за грешка.
     *
     * @param args масив от аргументи, където {@code args[1]} съдържа номера на стаята
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
