import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static double balance;
    static boolean isJoker=false;
    static boolean isColor;
    static boolean isNumber;
    static String guess_symbol;
    static boolean flag = true;
    public static final String[] symbol = {"♥", "♦", "♠", "♣","♥", "♦", "♠", "♣","♥", "♦", "♠", "♣" ,"♥", "♦", "♠", "♣","J"};
    public static final String[] number = {"2", "3", "4", "5", "6", "7", "8", "9", "11", "12", "13", "14"};
    static String[] userGuess;

    public static void main(String[] args) {
        loadProperties();

        Card.genCard();
        while (flag) {
            print_rules();

            Scanner bet_scanner = new Scanner(System.in);
            System.out.println("Enter bet:");
            double bet = bet_scanner.nextDouble();
            double temp = balance;

            isColor=false;
            isNumber=false;
            Scanner type_scanner = new Scanner(System.in);
            System.out.println("Enter bet type:");
            String bet_type ;
            bet_type = type_scanner.nextLine();
            switch (bet_type){
                case "color":isColor=true; System.out.println("Your final bet:"+bet);  break;
                case "number":isNumber=true; System.out.println("Your final bet:"+bet); break;
                case "double":isColor=true;isNumber=true;System.out.println("Your final bet:"+bet*2); break;
            }

            Scanner sc = new Scanner(System.in);
            String color_input = null,number_input = null;
        if(isColor&&isNumber){
            color_input=loadDialogColor(sc);
            number_input=loadDialogNumber(sc);
        }
        else if(isColor){
            color_input=loadDialogColor(sc);
            number_input="null";
        }else if(isNumber){
            color_input="null";
            number_input=loadDialogNumber(sc);
        }

            userGuess = new String[]{color_input, number_input};

            if (userGuess[0].equals(Card.card.properties[0])) {
                if (!isJoker) {
                    System.out.println("You guessed the color");
                    System.out.println("You won : "+bet*2);
                    balance =balance+
                            bet*2;
                } else {
                    System.out.println("You guessed the joker");
                    balance =balance + bet *24;
                    System.out.println("You won : "+bet*24);
                }
            }else{
                if(isColor) {
                    System.out.println("(1)You lost: " + bet);
                    balance = balance - bet;
                }
            }

            if(userGuess[1].equals(Card.card.properties[1])||Card.card.properties[1].contains(userGuess[1])||(userGuess[1].equals("11&14")&&Card.card.properties[1].equals("11-14"))){
                switch (number_input) {
                    case "2-9":
                        balance = balance + bet * 1.5;
                        break;
                    case "11-14":
                        balance = balance + bet * 3;
                        break;
                    case "11&14":
                        if (Card.card.properties[1].contains("11") || Card.card.properties[1].contains("14"))
                            balance = balance + bet * 6;
                        break;
                    case "11":
                        if (Card.card.properties[1].contains(number_input)) balance = balance + bet * 12;
                        break;
                }
                if (!isJoker) {
                    System.out.println("You guessed the number");
                    if(isColor) {
                        System.out.println("You won : " + ((balance - bet*2 - temp)));
                    }else{
                        System.out.println("You won : " + ((balance - bet - temp)));
                    }
                }
            }else{
                if(isNumber) {
                    System.out.println("(2)You lost: " + bet);
                    balance = balance - bet;
                }else {
                    if (isJoker && isNumber) {
                        System.out.println("(2)You lost: " + bet);
                        balance = balance - bet;
                    }
                }
            }

            System.out.println("balance :"+balance);
            Card.drawCard(Card.card);

            storeProperties(balance);

            System.out.println("Balance:"+balance);
            System.out.println("continue?");
            String command = sc.nextLine();
            if (command.equals("yes")) {
                Card.genCard();
            }
            if (command.equals("no")) {
                flag = false;
                System.out.println("Thanks for choosing our service");
            }
        }
    }


    private static void print_rules() {
        System.out.println("Rules of HiLo:");
        System.out.println("Choose the color:black or red ( X 2 bet) or Joker ( X 24 bet )");
        System.out.println("Choose numbers : range 2-9 ( X 1.5 bet) or 11-12-13-14 ( X 3 bet )  or 11 & 14 ( X 6 bet ) or 11 ( X12 bet )");

        System.out.println("Types of bets:");
        System.out.println("color bet , number bet , ");
        System.out.println("double bet(takes double your bet  ,bet on color and number range ");
        System.out.println("You will be asked which bet you would like");

        System.out.println("User input rules :");
        System.out.println("Colors: black , red , joker");
        System.out.println("Numbers: 2-9 , 11-14 , 11&14 , 11");
        System.out.println("Bets:color , number , double");
        System.out.println();
        System.out.println();
    }

    private static void loadProperties()  {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get("src/main/resources/prop/balance.hilo")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("data:"+data);
        balance= Double.parseDouble(data);
    }
    private static void storeProperties(double balance)  {
        try
        {
            String filename= "src/main/resources/prop/balance.hilo";
            FileWriter fw = new FileWriter(filename,false);
            fw.write(""+balance);
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    static String loadDialogColor(Scanner sc){
        System.out.println("choose color:");
        String color_input = sc.nextLine().toLowerCase();
        return color_input;
    }

    static String loadDialogNumber(Scanner sc){
        String number_input=null;
        if (!isJoker) {
            System.out.println("choose numbers: ");
            number_input = sc.nextLine();
        } else {
                if(isNumber&&isJoker){
                    number_input = "xxx";
                }
        }

        return number_input;
    }
}


