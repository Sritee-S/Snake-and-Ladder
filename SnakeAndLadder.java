import java.util.*;

public class SnakeAndLadder {
    static final int WINNING_POSITION = 100;
    static Map<Integer, Integer> snakes = new HashMap<>();
    static Map<Integer, Integer> ladders = new HashMap<>();

    static {
        // Define Snakes
        snakes.put(99, 7);
        snakes.put(90, 48);
        snakes.put(76, 36);
        snakes.put(54, 19);
        snakes.put(30, 5);

        // Define Ladders
        ladders.put(3, 22);
        ladders.put(8, 26);
        ladders.put(20, 41);
        ladders.put(28, 77);
        ladders.put(50, 91);
    }

    public static int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public static int movePlayer(int position, int diceValue, List<Integer> journey) {
        position += diceValue;
        if (position > WINNING_POSITION) {
            return position - diceValue; // Stay in the same position if it exceeds 100
        }
        if (snakes.containsKey(position)) {
            System.out.println("Oops! Bitten by a snake at " + position);
            position = snakes.get(position);
        } else if (ladders.containsKey(position)) {
            System.out.println("Yay! Climbed a ladder at " + position);
            position = ladders.get(position);
        }
        journey.add(position);
        return position;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playerPosition = 0, computerPosition = 0;
        List<Integer> playerJourney = new ArrayList<>();
        List<Integer> computerJourney = new ArrayList<>();

        System.out.println("Welcome to Snake and Ladder Game!");
        while (playerPosition < WINNING_POSITION && computerPosition < WINNING_POSITION) {
            System.out.println("Press Enter to roll the dice...");
            scanner.nextLine();
            int playerDice = rollDice();
            System.out.println("You rolled: " + playerDice);
            playerPosition = movePlayer(playerPosition, playerDice, playerJourney);
            System.out.println("Your new position: " + playerPosition);

            if (playerPosition == WINNING_POSITION) {
                System.out.println("Congratulations! You won the game!");
                break;
            }

            int computerDice = rollDice();
            System.out.println("Computer rolled: " + computerDice);
            computerPosition = movePlayer(computerPosition, computerDice, computerJourney);
            System.out.println("Computer's new position: " + computerPosition);

            if (computerPosition == WINNING_POSITION) {
                System.out.println("Computer wins! Better luck next time.");
                break;
            }
        }

        System.out.println("Do you want to see your journey, your opponent's journey, or no journey at all? (Enter: yours/opponent/none)");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("yours")) {
            System.out.println("Your journey: " + playerJourney);
        } else if (choice.equals("opponent")) {
            System.out.println("Computer's journey: " + computerJourney);
        } else {
            System.out.println("No journey displayed. Thank you for playing!");
        }

        scanner.close();
    }
}