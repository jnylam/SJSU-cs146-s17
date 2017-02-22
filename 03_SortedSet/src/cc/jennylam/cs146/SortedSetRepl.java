package cc.jennylam.cs146;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SortedSetRepl {
    private static final String HELP_MESSAGE =
            "h or help    - this help message\n" +
            "new skip     - create new skiplist\n" +
            "new bst      - create new binary search tree\n" +
            "a [integer]  - add\n" +
            "r [integer]  - remove\n" +
            "c [integer]  - contains\n" +
            "s            - size\n" +
            "f            - first\n" +
            "l            - last\n" +
            "fl [integer] - floor\n" +
            "ce [integer] - ceiling\n" +
            "d            - display contents\n" +
            "q or quit    - quit this program\n";
    private static final String FORMAT_ERROR_MESSAGE = "invalid format - type \"help\" for help";
    private SortedSet<Integer> list;
    private Scanner scanner;
    private boolean done;

    private void evaluateAndPrint(String input) {
        input = input.replaceAll("\\s+", "");
        try {
            if (input.equals("h") || input.equals("help"))
                System.out.println(HELP_MESSAGE);
            else if (input.equals("newskip"))
                list = new SkipList<>();
            else if (input.equals("newbst"))
                list = new BinarySearchTree<>();
            else if (input.charAt(0) == 'a') {
                list.add(Integer.valueOf(input.substring(1)));
                System.out.println(list);
            } else if (input.charAt(0) == 'r') {
                list.remove(Integer.valueOf(input.substring(1)));
                System.out.println(list);
            } else if (input.charAt(0) == 'c' && !Character.isAlphabetic(input.charAt(1)))
                System.out.println(list.contains(Integer.valueOf(input.substring(1))));
            else if (input.equals("s"))
                System.out.println(list.size());
            else if (input.equals("f"))
                System.out.println(list.first());
            else if (input.equals("l"))
                System.out.println(list.last());
            else if (input.length() >= 2 && input.substring(0, 2).equals("fl"))
                System.out.println(list.floor(Integer.valueOf(input.substring(2))));
            else if (input.length() >= 2 && input.substring(0, 2).equals("ce"))
                System.out.println(list.ceiling(Integer.valueOf(input.substring(2))));
            else if (input.equals("d"))
                System.out.println(list);
            else if (input.equals("q") || input.equals("quit"))
                done = true;
            else
                System.out.println(FORMAT_ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(FORMAT_ERROR_MESSAGE);
        } catch (NoSuchElementException e) {
            System.out.println("no such element exists");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void run() {
        System.out.println(HELP_MESSAGE);
        while (!done) {
            System.out.print("> ");
            evaluateAndPrint(scanner.nextLine());
        }
    }

    public SortedSetRepl() {
        list = new SkipList<>();
        scanner = new Scanner(System.in);
        done = false;
    }

    public static void main(String[] args) {
        new SortedSetRepl().run();
    }
}
