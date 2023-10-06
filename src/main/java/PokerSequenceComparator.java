import java.util.List;

public class PokerSequenceComparator {
    SequenceCheckers sequenceCheckers = new SequenceCheckers();
    BestSequenceReturner bestSequenceReturner = new BestSequenceReturner();
    PokerSubsequenceComparison pokerSubsequenceComparison = new PokerSubsequenceComparison();


    public PokerHand pokerHand(List<Card> playerCards) {
        if (sequenceCheckers.isPoker(playerCards))
            return new PokerHand(PokerSequenceType.POKER, bestSequenceReturner.getSequenceIfItIsPoker(playerCards));
        if (sequenceCheckers.isFourOfAKind(playerCards))
            return new PokerHand(PokerSequenceType.FOUR_OF_A_KIND, bestSequenceReturner.getSequenceIfItIsFourOfAKind(playerCards));
        if (sequenceCheckers.isFullHouse(playerCards))
            return new PokerHand(PokerSequenceType.FULL_HOUSE, bestSequenceReturner.getSequenceIfItIsFullHouse(playerCards));
        if (sequenceCheckers.isFlush(playerCards))
            return new PokerHand(PokerSequenceType.FLUSH, bestSequenceReturner.getSequenceIfItIsFlush(playerCards));
        if (sequenceCheckers.isStraight(playerCards))
            return new PokerHand(PokerSequenceType.STRAIGHT, bestSequenceReturner.getSequenceIfItIsStraight(playerCards));
        if (sequenceCheckers.isThreeOfAKind(playerCards))
            return new PokerHand(PokerSequenceType.THREE_OF_A_KIND, bestSequenceReturner.getSequenceIfItIsThreeOfAKind(playerCards));
        if (sequenceCheckers.isTwoPairs(playerCards))
            return new PokerHand(PokerSequenceType.TWO_PAIRS, bestSequenceReturner.getSequenceIfItIsTwoPairs(playerCards));
        if (sequenceCheckers.isPair(playerCards))
            return new PokerHand(PokerSequenceType.PAIR, bestSequenceReturner.getSequenceIfItIsOnePair(playerCards));
        return new PokerHand(PokerSequenceType.HIGH_CARD, bestSequenceReturner.getSequenceIfItIsHighCard(playerCards));
    }


    public int sequenceComparator(PokerSequenceType sequence1, PokerSequenceType sequence2) {
        return Integer.compare(sequence1.sequenceValue, sequence2.sequenceValue);
    }

    int compareSequences(List<Card> firstPlayerCards, List<Card> secondPlayerCards) {
        PokerSequenceType firstPlayerSequence = pokerHand(firstPlayerCards).getPokerSequenceType();
        PokerSequenceType secondPlayerSequence = pokerHand(secondPlayerCards).getPokerSequenceType();

        int compareValue = sequenceComparator(firstPlayerSequence, secondPlayerSequence);
        if (compareValue != 0) return compareValue;
        if (sequenceCheckers.isPoker(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequenceIsPoker(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isFourOfAKind(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequenceIsFourOfAKind(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isFullHouse(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequenceIsFullHouse(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isFlush(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequenceIsFlush(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isStraight(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequencesAreStraight(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isThreeOfAKind(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequencesAreThreeOfAKind(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isTwoPairs(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequencesAreTwoPairs(firstPlayerCards, secondPlayerCards);
        if (sequenceCheckers.isTwoPairs(firstPlayerCards))
            return pokerSubsequenceComparison.compareIfBothSequencesArePairs(firstPlayerCards, secondPlayerCards);
        return pokerSubsequenceComparison.compareIfBothSequencesAreHighCards(firstPlayerCards, secondPlayerCards);
    }

    int[] compareNSequences(List<List<Card>> playersCards) {
        int n = playersCards.size();
        int[] compareTable = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j){
                    if (compareSequences(playersCards.get(i), playersCards.get(j)) == 0) {
                        compareTable[i] = 0;
                        break;
                    }
                    if (compareSequences(playersCards.get(i), playersCards.get(j)) < 0) {
                        compareTable[i] = -1;
                        break;
                    }
                    compareTable[i] = 1;
                }

            }
        }
        return compareTable;
    }
}
