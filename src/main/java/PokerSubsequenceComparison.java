import java.util.List;
import java.util.stream.Collectors;

public class PokerSubsequenceComparison {
    CardService cardService = new CardService();
    SequenceCheckers sequenceCheckers = new SequenceCheckers();

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

    public List<Card> getPair(List<Card> cards) {
        String picture = "";
        for (int i = 0; i < cards.size(); i++) {
            String pictureOfICard = cards.get(i).getPicture();
            if (sequenceCheckers.pictureCounter(pictureOfICard, cards) == 2) {
                picture = pictureOfICard;
            }
        }
        String finalPicture = picture;
        return cards.stream().filter(c -> c.getPicture().equals(finalPicture)).collect(Collectors.toList());
    }

    public int compareIfBothSequencesArePairs(List<Card> firstPositionCards, List<Card> secondPositionCards) {
        List<Card> firstHandPair = getPair(firstPositionCards);
        List<Card> secondHandPair = getPair(secondPositionCards);
        String firstHandPairPicture = firstHandPair.get(0).getPicture();
        String secondHandPairPicture = secondHandPair.get(0).getPicture();
        int compareValue = 0;
        int valueOfComparingPairsPictures = cardService.compareValueOfTwoCards(firstHandPair.get(0), secondHandPair.get(0));
        if (valueOfComparingPairsPictures == 0) {
            List<Card> cardsOfFirstPositionWithoutPair = sequenceCheckers.listOfCardsWithRemovedCardsWithGivenPicture(firstHandPairPicture, firstPositionCards);
            List<Card> cardsOfSecondPositionWithoutPair = sequenceCheckers.listOfCardsWithRemovedCardsWithGivenPicture(secondHandPairPicture, secondPositionCards);
            List<Card> threeSortedCards1 = cardService.sortedListOfCardsByItsValue(false, cardsOfFirstPositionWithoutPair);
            List<Card> threeSortedCards2 = cardService.sortedListOfCardsByItsValue(false, cardsOfSecondPositionWithoutPair);
            compareValue = compareNSortedCards(3, threeSortedCards1, threeSortedCards2);
        } else compareValue = valueOfComparingPairsPictures;
        return compareValue;
    }
}
