package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Клас {@code DateUtils} предоставя помощни методи за работа с дати във формат {@code yyyy-MM-dd}.
 * Използва се за парсване, форматиране и проверка за припокриване на времеви интервали.
 */
public class DateUtils {

    /**
     * Формат на датата, използван в цялото приложение: {@code yyyy-MM-dd}.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Преобразува текст в {@code LocalDate} по зададения формат.
     *
     * @param dateStr низ, съдържащ датата
     * @return обект от тип {@code LocalDate}
     * @throws IllegalArgumentException ако низът не отговаря на очаквания формат
     */
    public static LocalDate parse(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Грешен/Невалиден формат на дата. Моля използвайте този формат: yyyy-MM-dd.");
        }
    }

    /**
     * Преобразува {@code LocalDate} в текстов формат {@code yyyy-MM-dd}.
     *
     * @param date обект от тип {@code LocalDate}
     * @return низ с форматирана дата
     */
    public static String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Проверява дали два времеви интервала се припокриват.
     *
     * @param start1 начало на първия интервал
     * @param end1   край на първия интервал
     * @param start2 начало на втория интервал
     * @param end2   край на втория интервал
     * @return {@code true}, ако интервалите се припокриват; {@code false} в противен случай
     */
    public static boolean overlaps(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }
}
