import java.util.List;
import java.util.Map;

public class PokerSequenceComparison {

    static CardService cardService;

    public int sequenceComparator(PokerSequence sequence1, PokerSequence sequence2){
        return Integer.compare(sequence1.sequenceValue, sequence2.sequenceValue);
    }


}
