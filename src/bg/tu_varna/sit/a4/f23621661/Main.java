package bg.tu_varna.sit.a4.f23621661;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor();

        System.out.println("Системата за управление на хотел е стартирана успешно. Въведете 'help' за да се изведата командите, които може да използвате.");
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