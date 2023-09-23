import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PokerSubsequenceComparison {
    CardService cardService = new CardService();
    BestSequenceReturner bestSequenceReturner = new BestSequenceReturner();

    public int compareNSortedCards(int n, List<Card> cards1, List<Card> cards2) {
        List<Card> nHighestCardsFromPosition1 = cardService.getNHighestCards(n, cards1);
        List<Card> nHighestCardsFromPosition2 = cardService.getNHighestCards(n, cards2);
        int valueOfComparingPictures = 0;
        for (int i = 0; i < n; i++) {
            int compareValue = cardService.compareValueOfTwoCards(nHighestCardsFromPosition1.get(i), nHighestCardsFromPosition2.get(i));
            if (compareValue != 0) {
                valueOfComparingPictures = compareValue;
                break;
            }
        }
        return valueOfComparingPictures;
    }

    public int compareIfBothSequencesAreHighCards(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        return compareNSortedCards(5, firstPositionCards, secondPositionCards);
    }

    public int compareValueOfNOfAKind(List<Card> firstPair, List<Card> secondPair) {
        return cardService.compareValueOfTwoCards(firstPair.get(0), secondPair.get(0));
    }

    public int compareIfBothSequencesArePairs(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsOnePair(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsOnePair(secondPositionCards);
        int compareValue = compareValueOfNOfAKind(firstPlayerSequence, secondPlayerSequence);
        if (compareValue == 0) {
            for (int i = 2; i < 5; i++) {
                int cardsCompareValue = cardService.compareValueOfTwoCards(firstPlayerSequence.get(i), secondPlayerSequence.get(i));
                if (cardsCompareValue != 0) {
                    compareValue = cardsCompareValue;
                    break;
                }
            }
        }
        return compareValue;

    }


    public int compareIfBothSequencesAreTwoPairs(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsTwoPairs(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsTwoPairs(secondPositionCards);
        int compareValue = cardService.compareValueOfTwoCards(firstPlayerSequence.get(0), secondPlayerSequence.get(0));
        if (compareValue == 0) {
            compareValue = cardService.compareValueOfTwoCards(firstPlayerSequence.get(2), secondPlayerSequence.get(2));
            if (compareValue == 0) {
                compareValue = cardService.compareValueOfTwoCards(firstPlayerSequence.get(4), secondPlayerSequence.get(4));
            }
        }
        return compareValue;
    }

    public List<Card> getThreeOfAKind(List<Card> cards) {
        return cardService.getNOfAKind(3, cards);
    }


}
