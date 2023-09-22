import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardService cardService = new CardService();
        SequenceCheckers sequenceCheckers = new SequenceCheckers();

        List<Card> deck = cardService.getDeckOfCards();
        int i=1;
        List<Card> cards = new ArrayList<>();
        boolean isStraight = false;

        do {
            Collections.shuffle(deck);
            for (int j = 0; j < 7; j++) {
                cards.add(deck.get(j));
            }
            System.out.println("Rozdanie "+ i +": " + cards);
            i++;
            if (sequenceCheckers.isStraightSpecialCase(cards)){
                isStraight = true;
            } else cards.clear();

        }while (!isStraight);









    }
}
