import java.util.Scanner;

public class Main {
    static boolean isJoker=false;
    static String guess_symbol;
    static boolean flag = true;
    public static final String[] symbol = {"♥", "♦", "♠", "♣", "J"};
    public static final String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "1"};

    public static void main(String[] args) {
        Card.genCard();
        while (flag) {
            Scanner sc = new Scanner(System.in);
            System.out.println("choose symbol:");
            String symbol_input = sc.nextLine().toLowerCase();

            System.out.println("choose number:");
            String number_input = sc.nextLine();

            if (number_input.equals(Card.card.getNumber())&& symbol_input.equals(Card.card.getSymbol())) {
                System.out.println("you guessed both");
                System.out.println("--------------------");
            }else if(number_input.equals(Card.card.getNumber())&& !symbol_input.equals(Card.card.getSymbol())){
                System.out.println("you guessed the number");
                System.out.println("--------------------");
            }else if(!number_input.equals(Card.card.getNumber()) && symbol_input.equals(Card.card.getSymbol())){
                System.out.println("you guessed the number");
                System.out.println("--------------------");
            }else if(!number_input.equals(Card.card.getNumber())&&!symbol_input.equals(Card.card.getSymbol())){
                System.out.println("you did not guess:/");
                System.out.println("--------------------");
            }

            Card.drawCard(Card.card);
                System.out.println("continue?");
                String command = sc.nextLine();
                if (command.equals("yes")) {
                    Card.genCard();
                }
                if (command.equals("no")) {
                    flag = false;
                    System.out.println("Thanks for choosing our service");
                }
                if (command.equals("0")) {
                    System.exit(0);
                }
            }
        }
    }

