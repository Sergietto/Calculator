class Calculator {
    // Производит требуемые операции с операндами
    // На вход: строковый массив операндов и операция (перечисление), на выходе строковый результат
    static String computeSomeExpression(String[] operands, Operation operation) throws Exception {
        byte firstOperand = Byte.parseByte(operands[0]);
        byte secondOperand = Byte.parseByte(operands[1]);

        return switch (operation) {
            case ADD -> String.valueOf(firstOperand + secondOperand);
            case SUB -> String.valueOf(firstOperand - secondOperand);
            case MUL -> String.valueOf(firstOperand * secondOperand);
            case DIV -> String.valueOf(firstOperand / secondOperand);
            default -> throw new Exception("\"" + operation + "\"" + " - неверный оператор!");
        };
    }
}
