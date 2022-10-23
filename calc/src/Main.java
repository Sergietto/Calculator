import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Калькулятор!");
        System.out.println("------------");
        System.out.println("Особенности:");
        System.out.println("- Только два операнда!");
        System.out.println("- Поддерживаемые операторы: +, -, * и /");
        System.out.println("- Поддержка арабских и римских чисел");
        System.out.println("- На вход от 1 до 10 включительно");
        System.out.println("- Только целые числа!");
        System.out.println("- Операция записываются в одну строчку: \"2 + 2\" или \"II+II\"");
        System.out.print("Введите арифметическое выражение: ");
        Scanner scn = new Scanner(System.in);
        String operation = scn.nextLine();
        System.out.println("Результат: " + calc(operation));

        /*
        System.out.println("Тестики!");
        System.out.println("Сложение от 1 до 10, арабские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = String.valueOf(i) + " + " + String.valueOf(b);
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */
        /*
        System.out.println("Вычитание от 1 до 10, арабские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = String.valueOf(i) + " - " + String.valueOf(b);
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */

        /*
        System.out.println("Умножение от 1 до 10, арабские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = String.valueOf(i) + " * " + String.valueOf(b);
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */
        /*
        System.out.println("Деление от 1 до 10, арабские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = String.valueOf(i) + " / " + String.valueOf(b);
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */
        /*
        System.out.println("Сложение от 1 до 10, римские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = ArabicToRome.convertArabicToRome(String.valueOf(i)) + " + " + ArabicToRome.convertArabicToRome(String.valueOf(b));
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */
        /*
        System.out.println("Вычитание от 1 до 10, римские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                if (i - b > 0) {
                    String operation = ArabicToRome.convertArabicToRome(String.valueOf(i)) + " - " + ArabicToRome.convertArabicToRome(String.valueOf(b));
                    System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
                }
            }
        }
         */
        /*
        System.out.println("Умножение от 1 до 10, римские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                String operation = ArabicToRome.convertArabicToRome(String.valueOf(i)) + " * " + ArabicToRome.convertArabicToRome(String.valueOf(b));
                System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
            }
        }
         */
        /*
        System.out.println("Деление от 1 до 10, римские");
        int numberOfOperation = 1;
        for (int i = 1; i <= 10; i++) {
            for (int b = 1; b <= 10; b++) {
                if (i / b > 0) {
                    String operation = ArabicToRome.convertArabicToRome(String.valueOf(i)) + " / " + ArabicToRome.convertArabicToRome(String.valueOf(b));
                    System.out.println(numberOfOperation++ + ". " + operation + " = " + calc(operation));
                }
            }
        }
         */
    }

    public static String calc(String input) throws Exception{
        // Находим операцию, разбиваем по ней операнды
        String[] operands;
        Operation operation;
        if (input.contains("+")){
            operands = input.split("\\+");
            operation = Operation.ADD;
        } else if (input.contains("-")) {
            operands = input.split("-");
            operation = Operation.SUB;

        } else if (input.contains("*")) {
            operands = input.split("\\*");
            operation = Operation.MUL;

        } else if (input.contains("/")) {
            operands = input.split("/");
            operation = Operation.DIV;

        } else {
            throw new Exception("Поддерживаемая операция не обнаружена!");
        }

        // Чистим незначащие пробелы, приводим в верхний регистр
        for (byte i = 0; i < operands.length; i++)
        {
            operands[i] = operands[i].trim().toUpperCase();
        }

        // Убеждаемся, что оператора лишь два
        if (operands.length != 2) throw new Exception("Неверное количество операторов!");
        // Проверяем, в какой системе введённые числа
        else if (RomeToArabic.isArabicOrRomeNumbers(operands) == RomeToArabic.CalcSystem.ROME && RomeToArabic.isCorrectRepeatingDeciDigits(operands)) {
            // Если числа римские, то сначала преобразуем оба операнда в арабские числа, потом считаем так же, как и арабские, а результат переводим в рисмскую систему
            for (byte i = 0; i < operands.length; i++) {
                operands[i] = RomeToArabic.convertRomeToArabic(operands[i]);
            }
            String result = Calculator.computeSomeExpression(operands, operation);
            return ArabicToRome.convertArabicToRome(result);
        }
        else {
            // С арабскими числами всё куда проще)
            return Calculator.computeSomeExpression(operands, operation);
        }
    }

}