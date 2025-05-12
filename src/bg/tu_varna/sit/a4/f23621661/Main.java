package bg.tu_varna.sit.a4.f23621661;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor();

        System.out.println("Hotel Management System started. Type 'help' for available commands.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }
            if (!input.isEmpty()) {
                executor.execute(input);
            }
        }
    }
}