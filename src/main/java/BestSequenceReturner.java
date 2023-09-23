import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BestSequenceReturner {

    CardService cardService = new CardService();

    public List<Card> getSequenceIfItIsOnePair(List<Card> cards) {
        List<Card> allCards = new ArrayList<>(cards);
        List<Card> pair = cardService.getPair(allCards);
        allCards.removeIf(c -> c.getPicture().equals(pair.get(0).getPicture()));
        List<Card> threeHighestCardsFromTheOthers = cardService.getNHighestCards(3, allCards);
        List<Card> sequence = new ArrayList<>();
        sequence.addAll(pair);
        sequence.addAll(threeHighestCardsFromTheOthers);
        return sequence;
    }

    public List<Card> getSequenceIfItIsTwoPairs(List<Card> cards) {
        List<Card> firstPair = cardService.getPair(cards);
        List<Card> cardsWithoutFirstPair = cardService.listOfCardsWithRemovedCardsWithGivenPicture(firstPair.get(0).getPicture(), cards);
        List<Card> secondPair = cardService.getPair(cardsWithoutFirstPair);
        List<Card> cardsWithoutSecondPair = cardService.listOfCardsWithRemovedCardsWithGivenPicture(secondPair.get(0).getPicture(), cards);
        List<Card> twoPairsSequence = new ArrayList<>();

        if (cardService.compareValueOfTwoCards(firstPair.get(0), secondPair.get(0)) > 0) {
            twoPairsSequence.addAll(firstPair);
            twoPairsSequence.addAll(secondPair);
        } else {
            twoPairsSequence.addAll(secondPair);
            twoPairsSequence.addAll(firstPair);
        }
        twoPairsSequence.add(cardService.sortedListOfCardsByItsValue(false, cardsWithoutSecondPair).get(0));

        return twoPairsSequence;
    }

    public List<Card> getSequenceIfItIsThreeOfAKind(List<Card> cards) {
        List<Card> threeSameCards = cardService.getNOfAKind(3, cards);
        List<Card> threeOfAKindSequence = new ArrayList<>(threeSameCards);
        List<Card> cardsWithoutThreeSameCards = cardService.listOfCardsWithRemovedCardsWithGivenPicture(threeSameCards.get(0).getPicture(), cards);
        List<Card> sortedLeftovers = cardService.sortedListOfCardsByItsValue(false, cardsWithoutThreeSameCards);
        threeOfAKindSequence.add(sortedLeftovers.get(0));
        threeOfAKindSequence.add(sortedLeftovers.get(1));
        return threeOfAKindSequence;
    }

//    public  List<Card> getSequenceIfItIsStraight(List<Card> cards){
//
//    }

    public List<Card> getSequenceIfItIsFlush(List<Card> cards) {
        String suitOfFlush = cardService.flushSuitRecognizer(cards);
        return cardService
                .getNHighestCards(5, cardService
                        .sortedListOfCardsByItsValue(false, cards
                                .stream()
                                .filter(c -> c.getSuit()
                                        .equals(suitOfFlush))
                                .collect(Collectors.toList())));
    }

    public List<Card> getSequenceIfItIsFullHouse(List<Card> cards) {
        List<Card> fullHouseSequence = new ArrayList<>();
        List<Card> firstTriplet = cardService.getNOfAKind(3, cards);
        List<Card> leftovers = cardService.listOfCardsWithRemovedCardsWithGivenPicture(firstTriplet.get(0).getPicture(), cards);
        if (cardService.isNOfAKind(3, leftovers)) {
            List<Card> secondTriplet = cardService.getNOfAKind(3, leftovers);
            if (cardService.compareValueOfTwoCards(firstTriplet.get(0), secondTriplet.get(0)) < 0) {
                fullHouseSequence.addAll(secondTriplet);
                fullHouseSequence.add(firstTriplet.get(0));
                fullHouseSequence.add(firstTriplet.get(1));
            }
        } else {
            fullHouseSequence.addAll(firstTriplet);
            List<Card> doublet = cardService.getNOfAKind(2, leftovers);
            List<Card> twoLastCards = cardService.listOfCardsWithRemovedCardsWithGivenPicture(doublet.get(0).getPicture(), doublet);
            if (cardService.isNOfAKind(2, twoLastCards)) {
                if (cardService.compareValueOfTwoCards(doublet.get(0), twoLastCards.get(0)) < 0) {
                    fullHouseSequence.addAll(twoLastCards);
                }
            } else {
                fullHouseSequence.addAll(doublet);
            }

        }
        return fullHouseSequence;
    }


}
