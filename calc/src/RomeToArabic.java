class RomeToArabic {
    // Преобразует строку символов, содержащую римские цифры в арабское число
    // Принимает строку, возвращает целочисленное восьмибитное число
    static String convertRomeToArabic(String input) throws Exception {
        /*
        Доступные в рамках задачи римские цифры на ввод:
        I - 1
        V - 5
        X - 10

        Доступны только на возврат:
        L - 50
        C - 100
         */

        // Преобразование римских цифр в арабские, без учёта особенностей
        byte[] arabicNumberArr = new byte[input.length()];
        for (byte i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'I' -> arabicNumberArr[i] = 1;
                case 'V' -> arabicNumberArr[i] = 5;
                case 'X' -> arabicNumberArr[i] = 10;
            }
        }

        // Защита от "VX" и "IIIX"
        for (byte i = 0; i < arabicNumberArr.length; i++) {
            // Если следующее число вдвое больше, значит была допущена ошибка, ибо VX = V, потому так писать нельзя
            if ((i + 1 < arabicNumberArr.length) && (arabicNumberArr[i+1] / arabicNumberArr[i] == 2)) throw new Exception("Нельзя ставить половинную цифру перед кратной десяти!");
            // Если цифра через цифру больше текущей цифры - это гарантированное нарушение правил написания римского числа
            else if ((i + 2 < arabicNumberArr.length) && (arabicNumberArr[i] < arabicNumberArr[i + 2])) throw new Exception("Нарушения порядка цифр в римском числе!");
        }

        // Суммирование всех цифр римского числа с учётом особенностей правил подсчёта
        byte finalNumber = 0;
        for (byte i = 0; i < arabicNumberArr.length; i++) {
            if (i + 1 < arabicNumberArr.length) {
                // Если текущая цифра больше или равна следующей, то всё идёт по порядку и просто суммируется
                if (arabicNumberArr[i] >= arabicNumberArr[i + 1]) {
                    finalNumber += arabicNumberArr[i];
                }
                // Если текущая цифра меньше, чем следующая, то по правилам будет вычитание
                else {
                    finalNumber += arabicNumberArr[i + 1] - arabicNumberArr[i];
                    i++;    // Чтобы не обработать следующее число дважды
                }
            }
            // Если это последний символ - просто прибавить его
            else {
                finalNumber += arabicNumberArr[i];
            }
        }

        if (finalNumber > 10) throw new Exception("Введёное число выходит за рамки допустимого диапазона!");

        return String.valueOf(finalNumber);
    }

    // Базовые проверки правил написания римских чисел, без фанатизма
    // На вход: строка с римским числом, на выходе true/false
    static boolean isRomeNumber(String input) throws Exception {
        // Проверка на то, что число содержит только допустимые в рамках задачи римские цифры, коих лишь 3
        if (!input.matches("[IVX]+")) throw new Exception("Число содержит недопустимые символы!");
        // Повторение цифр I и X более трёх раз подряд недопустимо, требуется пресекать это, дабы нельзя было ввести ерунду
        else if (input.matches(".*X{4,}.*") || input.matches(".*I{4,}.*")) throw new Exception("Нельзя использовать символы \"X\" или \"I\" более трёх раз подряд!");
        // Цифра V вообще не может быть написана более одного раза
        else if (input.indexOf("V") != input.lastIndexOf("V")) throw new Exception("Нельзя использовать символ \"V\" более одного раза!");
        return true;
    }

    // Возвращает истину, если строка состоит из двух арабских цифр
    static boolean isArabicNumber(String input) throws Exception {
        if (!input.matches("\\d{1,2}")) return false;
        byte number = Byte.parseByte(input);
        if (number > 0 && number <= 10) {
            return true;
        } else {
            throw new Exception("Введёное число выходит за рамки допустимого диапазона!");
        }
    }

    // Ограничивает возможность ввода некорректных римских чисел, не позволяя использовать множество раз символы, которые по правилам не могут быть использованы безграничное количество раз
    // На вход: массив строк с римскими числами, на выходе true/false
    static boolean isCorrectRepeatingDeciDigits(String[] romeNumbers) throws Exception {

        for (String romeNumber : romeNumbers) {
            short iCount = 0;
            short xCount = 0;
            for (byte b = 0; b < romeNumber.length(); b++) {
                if (romeNumber.charAt(b) == 'I') {
                    iCount++;
                } else if (romeNumber.charAt(b) == 'X') {
                    xCount++;
                }

                // Максимум 3, так как из I невозможно что-то вычесть
                if (iCount > 3) throw new Exception("Превышено количество допустимых символов \"I\"");
                    // Максимум 4, ибо 39. В рамках задачи не пригодится, ибо там ограничение X, но так правильно
                else if (xCount > 4)
                    throw new Exception("Превышено количество допустимых символов \"X\"");
            }
        }
        return true;
    }

    // Определяет, в какой системе (арабской или римской) происходит операция и не позволяет смешивать
    // На вход: строковый массив операндов, на выходе перечисление "CalcSystem", соответствующее римской или арабской системе
    static CalcSystem isArabicOrRomeNumbers (String[] operands) throws Exception {
        boolean firstOperandIsArabic = isArabicNumber(operands[0]);
        boolean secondOperandIsArabic = isArabicNumber(operands[1]);
        boolean firstOperandIsRome = !firstOperandIsArabic && isRomeNumber(operands[0]);
        boolean secondOperandIsRome = !secondOperandIsArabic && isRomeNumber(operands[1]);

        if (firstOperandIsArabic && secondOperandIsArabic) {
            return CalcSystem.ARABIC;
        } else if ((firstOperandIsArabic && secondOperandIsRome) || (firstOperandIsRome && secondOperandIsArabic)) {
            throw new Exception("Невозможно проводить операции между арабскими и римскими числами одновременно!");
        } else {
            return  CalcSystem.ROME;
        }
    }

    enum CalcSystem {
        ARABIC, ROME
    }
}
