package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Помощен клас за работа с дати във формат yyyy-MM-dd.
 * Предоставя методи за парсване, форматиране и проверка за припокриване на интервали.
 */
public class DateUtils {

    /**
     * Форматът на датата, използван в приложението.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Парсира текстова дата към LocalDate.
     *
     * @param dateStr входен низ с дата
     * @return съответният обект LocalDate
     * @throws IllegalArgumentException ако форматът е невалиден
     */
    public static LocalDate parse(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Грешен формат на дата. Използвайте yyyy-MM-dd.");
        }
    }

    /**
     * Преобразува LocalDate към текстов формат yyyy-MM-dd.
     *
     * @param date дата за форматиране
     * @return форматирана дата като текст
     */
    public static String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Проверява дали два периода се припокриват.
     *
     * @param start1 начало на първия интервал
     * @param end1   край на първия интервал
     * @param start2 начало на втория интервал
     * @param end2   край на втория интервал
     * @return true, ако има припокриване
     */
    public static boolean overlaps(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }
}
