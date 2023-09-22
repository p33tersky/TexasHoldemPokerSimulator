public enum PokerSequence {

    HIGH_CARD(0), PAIR(1),TWO_PAIRS(2),
    THREE_OF_A_KIND(3), STRAIGHT(4), FLUSH(5),
    FULL_HOUSE(6), FOUR_OF_A_KIND(7),POKER(8);

    int sequenceValue;

    PokerSequence(int sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    public int getSequenceValue() {
        return sequenceValue;
    }


}