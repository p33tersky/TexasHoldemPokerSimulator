import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardService cardService = new CardService();
        SequenceCheckers sequenceCheckers = new SequenceCheckers();
        PokerSubsequenceComparison pokerSubsequenceComparison = new PokerSubsequenceComparison();

        List<Card> deck = cardService.getDeckOfCards();

        List<Card> both = List.of(new Card("5", "pik"), new Card("K", "karo"),
                new Card("10", "trefl"), new Card("3", "karo"), new Card("6", "pik"));
        List<Card> cards1 = new ArrayList<>(List.of(new Card("5", "kier"), new Card("7", "kier")));
        cards1.addAll(both);
        List<Card> cards2 = new ArrayList<>(List.of(new Card("5", "kier"), new Card("8", "trefl")));
        cards2.addAll(both);
        System.out.println(pokerSubsequenceComparison.compareIfBothSequencesArePairs(cards1, cards2));
//        int i=1;

//        List<Card> cards = new ArrayList<>();
//        boolean isStraight = false;
//
//        do {
//            Collections.shuffle(deck);
//            for (int j = 0; j < 7; j++) {
//                cards.add(deck.get(j));
//            }
//            System.out.println("Rozdanie "+ i +": " + cards);
//            i++;
//            if (sequenceCheckers.isStraightSpecialCase(cards)){
//                isStraight = true;
//            } else cards.clear();
//
//        }while (!isStraight);


    }
}
