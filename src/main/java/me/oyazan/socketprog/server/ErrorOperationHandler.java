package me.oyazan.socketprog.server;

public class ErrorOperationHandler {

    public static String evaluate(String expression) {
        // handle empty expression/null
        if (expression == null || expression.trim().isEmpty()) {
            return "Please enter an expression";
        }
        //seperate the input string into 3 parts (firstnum, operation, second)
        String[] parts = expression.trim().split("\\s+");
        //handle format(must be X (OPERATION) Y
        if (parts.length != 3) {
            return "Please enter the expression in the right format, ex: 4 + 7";
        }

        try {
            double firstNum = Double.parseDouble(parts[0]);
            double secondNum = Double.parseDouble(parts[2]);
            String operation = parts[1];

            //return result
            return switch (operation) {
                case "+" -> String.valueOf(firstNum + secondNum);
                case "-" -> String.valueOf(firstNum - secondNum);
                case "*" -> String.valueOf(firstNum * secondNum);
                case "/" -> (secondNum == 0) ? "Can't divide on zero" : String.valueOf(firstNum / secondNum);
                default -> "Invalid operation";
            };

        } catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }
}
