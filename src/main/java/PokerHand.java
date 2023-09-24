import java.util.List;

public class PokerHand {
    PokerSequenceType pokerSequenceType;
    List<Card> bestSequence;

    public PokerHand(PokerSequenceType pokerSequenceType, List<Card> bestSequence) {
        this.pokerSequenceType = pokerSequenceType;
        this.bestSequence = bestSequence;
    }

    public PokerSequenceType getPokerSequenceType() {
        return pokerSequenceType;
    }

    public List<Card> getBestSequence() {
        return bestSequence;
    }
}
