import java.util.List;
import java.util.Map;

public class PokerSequenceComparison {

    static CardService cardService;

    public int sequenceComparator(PokerSequence sequence1, PokerSequence sequence2){
        return Integer.compare(sequence1.sequenceValue, sequence2.sequenceValue);
    }

    public int compareIfBothSequenceIsHighCard(List<Card> firstPositionCards, List<Card> secondPositionCards){
        List<Card> fiveHighestCardsFromPosition1 = cardService.getFiveHighestCards(firstPositionCards);
        List<Card> fiveHighestCardsFromPosition2 = cardService.getFiveHighestCards(secondPositionCards);

        int sumOfComparingValues = 0;
        for (int i = 0; i < 5; i++) {
            sumOfComparingValues = sumOfComparingValues + cardService.compareValueOfTwoCards(fiveHighestCardsFromPosition1.get(i),fiveHighestCardsFromPosition2.get(i));
        }
        return Integer.compare(sumOfComparingValues, 0);
    }

//    public int subsequenceComparator(PokerSequence sequence1, PokerSequence sequence2){
//
//    }



}
