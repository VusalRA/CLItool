import console.Console;

import java.text.ParseException;
import java.util.Scanner;

public class CLITool {

    public static void main(String[] args) throws ParseException {
        Scanner scan = new Scanner(System.in);
        Console console = new Console(scan);
        console.consoleMenu();
    }

}
