package dmytro;

import java.util.Scanner;

public class Main {

    private static final String YES = "yes";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            XO game = new XO();
            game.start();

            System.out.println("Do you want to start new game(yes/no)?");
            userInput = scanner.nextLine();
        } while (userInput.toLowerCase().equals(YES));
        scanner.close();
        System.out.println("Bye!");
    }

}
