import com.sun.source.tree.NewArrayTree;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static void main(String[] args) throws RuntimeException {
        String result = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String str = scanner.nextLine();

        Pattern patternMatch = Pattern.compile("^(\\\")(\\D+|\\d+)(\\\")([ ]*)([\\+\\-\\/\\*])([ ]*)(\\\"*)(\\D+|[0-9]+)(\\\"*)$");
        Matcher matcherMatch = patternMatch.matcher(str);

        if (matcherMatch.matches()) {  // Введенная строка соответствует шаблону
            Pattern patternMulti = Pattern.compile("([ ]*)\\*([ ]*)");
            Pattern patternDiv = Pattern.compile("([ ]*)\\/([ ]*)");
            Pattern patternPlus = Pattern.compile("([ ]*)\\+([ ]*)");
            Pattern patternMinus = Pattern.compile("([ ]*)\\-([ ]*)");

            Matcher matcherMulti = patternMulti.matcher(str);
            Matcher matcherDiv = patternDiv.matcher(str);
            Matcher matcherPlus = patternPlus.matcher(str);
            Matcher matcherMinus = patternMinus.matcher(str);


            if (matcherPlus.find()) { // Сложение строк
                result = performStringAddition(str);

            } else if (matcherMinus.find()) {  // Вычитание строк
                result = performStringSubtraction(str);

            } else if (matcherMulti.find()) {  // Умножение строки на целое число
                result = performStringMultiplication(str);

            } else if (matcherDiv.find()) {  // Деление строки на целое число
                result = performStringDivision(str);

            }
        } else {    // Введенная строка не соответствует шаблону
            throw new RuntimeException("Неверный ввод");
        }
        System.out.println("\"" + result + "\"");
    }

    private static String performStringAddition(String str) {
        String[] splitEntries = str.split("\\+");

        splitEntries[0] = splitEntries[0].trim();
        splitEntries[1] = splitEntries[1].trim();


        if (!splitEntries[1].contains("\"")) {
            throw new RuntimeException("Складывать можно только строки");
        }


        for (int i = 0; i < splitEntries.length; i++) {
            splitEntries[i] = splitEntries[i].replace("\"", "");
        }

        if (splitEntries[0].length() > 10 || splitEntries[1].length() > 10) {
            throw new RuntimeException("Длина строки должна быть не более 10 символов");
        }

        return (splitEntries[0]) + (splitEntries[1]);
    }

    private static String performStringSubtraction(String str) {
        String result;
        String[] splitEntries = str.split(" - ");

        if (!splitEntries[1].contains("\"")) {
            throw new RuntimeException("Вычитать можно только строки");
        }

        for (int i = 0; i < splitEntries.length; i++) {
            splitEntries[i] = splitEntries[i].replace("\"", "");
        }

        int index = splitEntries[0].indexOf(splitEntries[1]);

        if (index == -1) {  // Если нет вхождения
            result = splitEntries[0];
        } else {
            result = splitEntries[0].substring(0, index);
            result += splitEntries[0].substring(index + splitEntries[1].length());

            if (splitEntries[0].length() > 10 || splitEntries[1].length() > 10) {
            throw new RuntimeException("Длина строки должна быть не более 10 символов");
        }
        }
        return result;
    }

    private static String performStringMultiplication(String str) {
        String[] splitEntries = str.split("\\*");
        String result;

        splitEntries[1] = splitEntries[1].trim();

        if (splitEntries[1].contains("\"")) {
            throw new RuntimeException("Умножать можно только на число");
        }

        int multiplier = Integer.parseInt(splitEntries[1]);

        if (multiplier > 10 || multiplier == 0) {
            throw new RuntimeException("Только числа от 1 до 10");
        }

        splitEntries[0] = splitEntries[0].replace("\"", "");

        splitEntries[0] = splitEntries[0].trim();

        if (splitEntries[0].length() > 10) {
            throw new RuntimeException("Длина строки должна быть не более 10 символов");
        }

        result = splitEntries[0];

        for (int i = 1; i < multiplier; i++) {
            result = result + splitEntries[0];
        }

        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }
        return result;
    }
    private static String performStringDivision(String str) {
        String[] splitEntries = str.split("/");
        String result;

        splitEntries[0] = splitEntries[0].trim();
        splitEntries[1] = splitEntries[1].trim();

        if (splitEntries[0].length() > 10) {
            throw new RuntimeException("Длина строки должна быть не более 10 символов");
        }

        for (int i = 0; i < splitEntries.length; i++) {
            splitEntries[i] = splitEntries[i].replace("\"", "");
        }

        int division = Integer.parseInt(splitEntries[1]);

        if (division > 10 || division == 0) {
            throw new RuntimeException("Только числа от 1 до 10");
        }

        int substringLength = splitEntries[0].length() / division;
        result = splitEntries[0].substring(0, substringLength);

        return result;
    }
}























