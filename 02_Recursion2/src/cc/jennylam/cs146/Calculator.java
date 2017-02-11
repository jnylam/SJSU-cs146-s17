package cc.jennylam.cs146;

import java.util.Scanner;

/**
 * This calculator can evaluate strings that represent math expressions in the following simple language:
 * 
 * sum := a sequence of one or more terms, separated by '+'
 * term := a sequence of one or more factors, separated by '*'
 * factor := a number or a sequence of the form '(' sum ')'
 * number := a sequence of digits
 * 
 * Because this language is small, it only recognizes a subset of what we usually call math expressions. 
 * See for yourself! Fire up this calculator, and try some inputs. What kind of numbers can it not recognize?
 * 
 * Suggestion: study the code. 
 * 1. The heart of the logic lies in the evaluate() function. What is its input and what is its output?
 * In other words, what is the function supposed to achieve?
 * 2. The language described above is recursive. To each part of this language, there is a corresponding 
 * function in the code. What is the input and output of each function? In additional to a return value, 
 * each function has a side effect. What is that side effect?
 * 3. A REPL utility is provided for convenience and testing purposes. What does REPL stand for?
 * 
 * Now your turn: add functionality to this calculator.
 * 1. Redefine numbers in this language to allow negative and decimal numbers. Modify the calculator to
 * reflect this change in the language. Don't forget to update the comments accordingly.
 * 2. Expand the language to include differences and quotients. Implement these changes in code.
 * 3. Can you think of other things to throw in? Think of the things you can type on a graphing calculator.
 * 
 * Add variables
 * Example REPL session:
 *   > var a = 10
 *   > a + 8
 *   18
 * Note: so far, the calculator did not need to keep state because evaluate was just a big pipeline converting
 * an input string into an output numerical value. Once variables are introduced, we need to have state.
 * Suggestion: start by refactoring the code to turn this collection of static functions (which is not very
 * object oriented) into true OO-style code, with methods. In other words, remove the word "static"! and 
 * implement a constructor. The constructor would be a good place to call repl().
 *
 * Then, think about how you could implement variables. (Hint: look up symbol tables.)
 * 
 * @author jenny
 *
 */

public class Calculator {
    
    public static int evaluate(String s) throws InputFormatException {
        // remove white space and make string mutable
        StringBuilder input = new StringBuilder(s.replaceAll("\\s+", ""));
        // evaluate
        int value = sum(input);
        // input should be completely consumed
        if (input.length() == 0)
            return value;
        // input was not completely consumed
        throw new InputFormatException();
    }
    
    // sum := a sequence of one or more terms, separated by '+'
    private static int sum(StringBuilder input) throws InputFormatException {
        int result = term(input);
        while (input.length() > 0 && input.charAt(0) == '+') {
            input.deleteCharAt(0);
            result += term(input);
        }
        return result;
    }
    
    // term := a sequence of one or more factors, separated by '*'
    private static int term(StringBuilder input) throws InputFormatException {
        int result = factor(input);
        while (input.length() > 0 && input.charAt(0) == '*') {
            input.deleteCharAt(0);
            result *= factor(input);
        }
        return result;
    }
    
    // factor := a number or a sequence of the form '(' sum ')'
    private static int factor(StringBuilder input) throws InputFormatException {
        if (input.length() == 0)
            throw new InputFormatException();
        if (Character.isDigit(input.charAt(0))) {
            return number(input);
        }
        if (input.charAt(0) == '(') {
            input.deleteCharAt(0);
            int result = sum(input);
            if (input.charAt(0) != ')')
                throw new InputFormatException("expected a ')'");
            input.deleteCharAt(0);
            return result;
        }
        throw new InputFormatException();
    }
    
    // number := a sequence of digits
    private static int number(StringBuilder input) throws InputFormatException {
        int i = 0;
        while (i < input.length() && Character.isDigit(input.charAt(i)))
            i++;
        int nextNumber = Integer.valueOf(input.substring(0, i));
        input.delete(0, i);
        return nextNumber;
    }
    
    @SuppressWarnings("serial")
    public static class InputFormatException extends Throwable {
        public InputFormatException() {this("invalid input");}
        public InputFormatException(String string) {super(string);}
    }
    
    private Calculator() {} // disable instantiation
    
    public static void repl() {
        System.out.println("Hello!");
        Scanner scanner = new Scanner(System.in);
        while (true) {                                   // loop
            System.out.print("> ");
            String input = scanner.nextLine();           // read
            try {
                if (input.equals("quit"))
                    break;
                System.out.println(evaluate(input));     // evaluate, print
            } catch (InputFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("type 'quit' to quit");
            }
        }
        scanner.close();
        System.out.println("Good bye!");
    }
    
    public static void main(String[] args) {
        repl();
    }
}
