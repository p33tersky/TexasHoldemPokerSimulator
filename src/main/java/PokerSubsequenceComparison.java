import java.util.List;

public class PokerSubsequenceComparison {
    CardService cardService = new CardService();
    BestSequenceReturner bestSequenceReturner = new BestSequenceReturner();

    public int compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(List<Card> firstPlayerSequence, List<Card> secondPlayerSequence) {
        int compareValue = 0;
        for (int i = 0; i < 5; i++) {
            compareValue = cardService.compareValueOfTwoCards(firstPlayerSequence.get(i), secondPlayerSequence.get(i));
            if (compareValue != 0) {
                break;
            }
        }
        return compareValue;
    }

    public int compareIfBothSequencesAreHighCards(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsHighCard(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsHighCard(secondPositionCards);

        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequencesArePairs(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsOnePair(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsOnePair(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }


    public int compareIfBothSequencesAreTwoPairs(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsTwoPairs(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsTwoPairs(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequencesAreThreeOfAKind(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsThreeOfAKind(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsThreeOfAKind(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequencesAreStraight(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsStraight(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsStraight(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequenceIsFlush(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsFlush(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsFlush(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequenceIsFullHouse(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsFullHouse(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsFullHouse(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequenceIsFourOfAKind(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsFourOfAKind(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsFourOfAKind(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }

    public int compareIfBothSequenceIsPoker(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstPlayerSequence = bestSequenceReturner.getSequenceIfItIsPoker(firstPositionCards);
        List<Card> secondPlayerSequence = bestSequenceReturner.getSequenceIfItIsPoker(secondPositionCards);
        return compareValueIfBestSequenceIsReturnedAndBothSequencesAreTheSame(firstPlayerSequence, secondPlayerSequence);
    }


}
