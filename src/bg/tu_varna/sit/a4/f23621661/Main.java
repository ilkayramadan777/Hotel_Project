package bg.tu_varna.sit.a4.f23621661;

import java.util.Scanner;

/**
 * Главен клас на приложението.
 * Стартира конзолен интерфейс и обработва потребителски команди.
 */
public class Main {

    /**
     * Точка на вход. Изпълнява команди до въвеждане на "exit".
     *
     * @param args аргументи от командния ред (не се използват)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor();

        System.out.println("Системата за управление на хотел е стартирана успешно.");
        System.out.println("Въведете 'help' за списък с достъпни команди.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Излизане от програмата...");
                break;
            }
            if (!input.isEmpty()) {
                executor.execute(input);
            }
        }
    }
}
