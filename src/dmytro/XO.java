package dmytro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class XO {

    private static final List<String> DEFALUT_PLACEHOLDERS = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
    private List<String> placeholders = new ArrayList<String>(DEFALUT_PLACEHOLDERS);

    private Set<String> userInputs = new HashSet<String>();
    private Set<String> computerInputs = new HashSet<String>();

    private static final String EXIT = "exit";
    private String deck;

    public XO() {
        String deckPattern = deckPattern();

        deck = deck(deckPattern, DEFALUT_PLACEHOLDERS.toArray());
    }

    public void start() {
        welcome();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            String callToActionMessage = "Your turn. Enter the number of a placeholder to replace" + placeholders + ":";
            System.out.println(callToActionMessage);

            userInput = scanner.nextLine();

            if (valid(userInput)) {
                userInputs.add(userInput);
                deck = deck.replace(userInput, "X");
                placeholders.remove(userInput);
                computerTurn();
            } else {
                String errorMessage = "Your input is not valid. Try one more time.";
                System.out.println(errorMessage);
            }

            System.out.println(deck);

            boolean userWon = isWinner(userInputs);
            boolean computerWon = isWinner(computerInputs);
            if (userWon && computerWon) {
                System.out.println("Draw!!! No winners!");
                break;
            } else if (userWon) {
                System.out.println("Congratulations! You won!!!");
                break;
            } else if (computerWon) {
                System.out.println("Computer won! Try to win one more time!!!");
                break;
            }
        } while (!userInput.toLowerCase().equals(EXIT));
    }

    private boolean isWinner(Collection<String> inputs) {
        List<String> winResult1 = Arrays.asList("1", "2", "3");
        List<String> winResult2 = Arrays.asList("4", "5", "6");
        List<String> winResult3 = Arrays.asList("7", "8", "9");
        List<String> winResult4 = Arrays.asList("1", "4", "7");
        List<String> winResult5 = Arrays.asList("2", "5", "8");
        List<String> winResult6 = Arrays.asList("3", "6", "9");
        List<String> winResult7 = Arrays.asList("1", "5", "9");
        List<String> winResult8 = Arrays.asList("3", "5", "7");
        List<List<String>> winResultsAll = Arrays.asList(winResult1, winResult2, winResult3, winResult4, winResult5,
                winResult6, winResult7, winResult8);

        boolean isWinner = false;
        for (List<String> winResult : winResultsAll) {
            if (inputs.containsAll(winResult)) {
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    private Random random = new Random();

    private void computerTurn() {
        String computerInput = placeholders.get(random.nextInt(placeholders.size()));

        computerInputs.add(computerInput);
        deck = deck.replace(computerInput, "O");
        placeholders.remove(computerInput);

    }

    private boolean valid(String userInput) {
        return placeholders.contains(userInput) || userInput.equals(EXIT);
    }

    private void welcome() {
        String welcomeMessage = "Here is a basic deck:";
        System.out.println(welcomeMessage);
        System.out.println(deck);
    }

    private String deckPattern() {
        StringBuilder patternBuilder = new StringBuilder();
        patternBuilder.append(" %1$s | %2$s | %3$s ");
        patternBuilder.append("\n---+---+---");
        patternBuilder.append("\n %4$s | %5$s | %6$s ");
        patternBuilder.append("\n---+---+---");
        patternBuilder.append("\n %7$s | %8$s | %9$s ");
        return patternBuilder.toString();
    }

    private String deck(String pattern, Object[] values) {
        return String.format(pattern, values);
    }
}
