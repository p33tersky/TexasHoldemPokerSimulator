import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardService cardService = new CardService();
        SequenceCheckers sequenceCheckers = new SequenceCheckers();
        PokerSubsequenceComparison pokerSubsequenceComparison = new PokerSubsequenceComparison();
        BestSequenceReturner bestSequenceReturner = new BestSequenceReturner();

        List<Card> deck = cardService.getDeckOfCards();

//        List<Card> both = List.of(new Card("Q", "pik"), new Card("8", "karo"),
//                new Card("8", "trefl"), new Card("Q", "karo"), new Card("Q", "pik"),
//                new Card("8","kier"), new Card("J", "kier"));
//
//        List<Card> both2 = List.of(new Card("A", "pik"), new Card("K", "karo"),
//                new Card("8", "trefl"), new Card("10", "karo"), new Card("4", "pik"),
//                new Card("K", "kier"), new Card("3", "kier"));
//        System.out.println(pokerSubsequenceComparison.compareIfBothSequencesArePairs(both,both2));
//        System.out.println(bestSequenceReturner.getSequenceIfItIsThreeOfAKind(both));


        List<Card> p1;
        int j = 0;

        do {
            p1 = new ArrayList<>();
            j++;
            Collections.shuffle(deck);
            for (int i = 0; i < 7; i++) {
                p1.add(deck.get(i));
            }
            System.out.println(j + " :" + p1 + "--> " + sequenceCheckers.isFullHouse(p1));
            if (!sequenceCheckers.isFullHouse(p1)){
                p1.clear();
            } else {
                System.out.println(bestSequenceReturner.getSequenceIfItIsFullHouse(p1));
                break;
            }
        }while (!sequenceCheckers.isFullHouse(p1));




    }
}
