public enum PokerSequenceType {

    HIGH_CARD(0, "high card"), PAIR(1, "pair"),
    TWO_PAIRS(2, "two pairs"), THREE_OF_A_KIND(3, "three of a kind"),
    STRAIGHT(4, "straight"), FLUSH(5, "flush"),
    FULL_HOUSE(6, "full house"), FOUR_OF_A_KIND(7, "four of a kind"),
    POKER(8, "poker");

    int sequenceValue;
    String sequenceName;

    PokerSequenceType(int sequenceValue, String sequenceName) {
        this.sequenceValue = sequenceValue;
        this.sequenceName = sequenceName;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public int getSequenceValue() {
        return sequenceValue;
    }


}