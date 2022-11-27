import java.util.Random;

public class Card {

    public static Card card;
    public String card_symbol;
    public String card_number;
    public String card_color;
    public String[] properties;

    public Card(String card_symbol, String card_number) {
        this.card_symbol = card_symbol;
        this.card_number = card_number;
    }

    public String getNumber() {
        return card_number;
    }

    public String getSymbol() {
        return card_symbol;
    }

    public static void genCard() {
        card = new Card(Main.symbol[new Random().nextInt(Main.symbol.length)], Main.number[new Random().nextInt(Main.number.length)]);
        Main.isJoker=false;
        switch (card.getSymbol()) {
            case "♥":Main.guess_symbol ="heart";card.card_color ="red";  break;
            case "♦":Main.guess_symbol ="diamond";card.card_color ="red"; break;
            case "♠":Main.guess_symbol ="spade";card.card_color ="black"; break;
            case "♣":Main.guess_symbol ="club";card.card_color ="black"; break;
            case "J":Main.guess_symbol ="joker";Main.isJoker = true;card.card_color ="joker"; card.card_number=""; break;
            default:
                System.out.println("wrong input");
                break;
        }
        if(!Main.isJoker) {
            if (Integer.parseInt(card.card_number) < 10 && Integer.parseInt(card.card_number) >= 2) {
                card.properties = new String[]{card.card_color, "2-9"};
            }
            if (Integer.parseInt(card.card_number) <= 14 && Integer.parseInt(card.card_number) >= 11) {
                card.properties = new String[]{card.card_color, "11-14"};
            }
        }else{
            card.properties = new String[]{card.card_color, ""};
        }

        System.out.println("Card has been generated =>" +card.card_number +"=> "+card.card_color+"joker:"+Main.isJoker);
    }

    public static void drawCard(Card card){
        int i, j;
        for (i = 1; i <= 9; i++)
        {
            for (j = 1; j <= 12; j++)
            {

                if (i == 1 || i == 9 || j == 1 || j == 12)
                    System.out.print("*");
                else if (i == 5 && j == 6)
                    System.out.print(card.getSymbol());
                else if (((i==2 &&j==3)|| (i == 8 && j == 10))&&Main.isJoker) System.out.print(" ");
                else if(!Main.isJoker && card.getNumber().length()>1){
                    if ((i == 2 && j == 3) || (i == 8 && j == 9)) {
                        System.out.print(card.getNumber().charAt(0));
                    }else if((i == 2 && j == 4) || (i == 8 && j == 10)) {
                        System.out.print(card.getNumber().charAt(1));
                    }else{
                        System.out.print(" ");
                    }
                }else {
                    if ((i == 2 && j == 3) || (i == 8 && j == 10)) {
                        System.out.print(card.getNumber());
                    }
                    else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

}


