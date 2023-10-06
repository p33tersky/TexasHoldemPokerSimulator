import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BestSequenceReturner {

    CardService cardService = new CardService();
    SequenceCheckers sequenceCheckers = new SequenceCheckers();

    public List<Card> getSequenceIfItIsHighCard(List<Card> cards) {
        return cardService.getNHighestCards(5, cards);
    }

    public List<Card> getSequenceIfItIsOnePair(List<Card> cards) {
        List<Card> pairSequence = new ArrayList<>();
        List<Card> pair = cardService.getPair(cards);
        List<Card> leftovers = cardService.listOfCardsWithRemovedCardsWithGivenPicture(pair.get(0).getPicture(), cards);
        List<Card> threeHighestValuesFromOthers = cardService.getNHighestCards(3, leftovers);

        pairSequence.addAll(pair);
        pairSequence.addAll(threeHighestValuesFromOthers);
        return pairSequence;
    }

    public List<Card> getSequenceIfItIsTwoPairs(List<Card> cards) {
        List<Card> highestPair = cardService.getPair(cards);
        List<Card> cardsWithoutFirstPair = cardService.listOfCardsWithRemovedCardsWithGivenPicture(highestPair.get(0).getPicture(), cards);
        List<Card> secondPair = cardService.getPair(cardsWithoutFirstPair);
        List<Card> leftovers = cardService.listOfCardsWithRemovedCardsWithGivenPicture(secondPair.get(0).getPicture(), cardsWithoutFirstPair);
        List<Card> twoPairsSequence = new ArrayList<>();
        twoPairsSequence.addAll(highestPair);
        twoPairsSequence.addAll(secondPair);
        twoPairsSequence.add(cardService.getCardWithTheHighestValue(leftovers));
        return twoPairsSequence;
    }

    public List<Card> getSequenceIfItIsThreeOfAKind(List<Card> cards) {
        List<Card> threeSameCards = cardService.getHighestNOfAKind(3, cards);
        List<Card> threeOfAKindSequence = new ArrayList<>(threeSameCards);
        List<Card> cardsWithoutThreeSameCards = cardService.listOfCardsWithRemovedCardsWithGivenPicture(threeSameCards.get(0).getPicture(), cards);
        List<Card> sortedLeftovers = cardService.sortedListOfCardsByItsValue(false, cardsWithoutThreeSameCards);
        threeOfAKindSequence.add(sortedLeftovers.get(0));
        threeOfAKindSequence.add(sortedLeftovers.get(1));
        return threeOfAKindSequence;
    }

    public List<Card> returnStraightSpecialCaseSequence(List<Card> cards) {
        List<Card> specialSequenceStraight = new ArrayList<>();
        Card AS = cards.stream().filter(c -> c.getPicture().equals("A")).collect(Collectors.toList()).get(0);
        for (int i = 5; i > 1; i--) {
            int finalI = i;
            specialSequenceStraight.add(cards.stream().filter(c -> c.getPicture().equals(String.valueOf(finalI))).collect(Collectors.toList()).get(0));
        }
        specialSequenceStraight.add(AS);
        return specialSequenceStraight;
    }

    public List<Card> getSequenceIfItIsStraight(List<Card> cards) {
        List<Card> cardsSortedDesc = cardService.sortedListOfCardsByItsValue(false, cards);
        int size = cardsSortedDesc.size();
        for (int i = 0; i < size - 4; i++) {
            List<Card> potentialStraight = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                potentialStraight.add(cardsSortedDesc.get(i + j));
            }
            if (sequenceCheckers.isStraight(potentialStraight)) {
                return potentialStraight;
            } else {
                potentialStraight.clear();
            }
        }
        return returnStraightSpecialCaseSequence(cards);
    }

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
        List<Card> firstTriplet = cardService.getHighestNOfAKind(3, cards);
        List<Card> leftovers = cardService.listOfCardsWithRemovedCardsWithGivenPicture(firstTriplet.get(0).getPicture(), cards);
        if (cardService.isNOfAKind(3, leftovers)) {
            List<Card> secondTriplet = cardService.getHighestNOfAKind(3, leftovers);
            if (cardService.compareValueOfTwoCards(firstTriplet.get(0), secondTriplet.get(0)) < 0) {
                fullHouseSequence.addAll(secondTriplet);
                fullHouseSequence.add(firstTriplet.get(0));
                fullHouseSequence.add(firstTriplet.get(1));
            }
        } else {
            fullHouseSequence.addAll(firstTriplet);
            List<Card> doublet = cardService.getHighestNOfAKind(2, leftovers);
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

    public List<Card> getSequenceIfItIsFourOfAKind(List<Card> cards) {
        List<Card> fourOfAKindSequence = new ArrayList<>(cardService.getHighestNOfAKind(4, cards));
        Card highestCardFromLeftovers = cardService
                .getCardWithTheHighestValue(cardService
                        .listOfCardsWithRemovedCardsWithGivenPicture(fourOfAKindSequence.get(0).getPicture(), cards));
        fourOfAKindSequence.add(highestCardFromLeftovers);
        return fourOfAKindSequence;
    }

    public List<Card> getSequenceIfItIsPoker(List<Card> cards) {
        String suitOfPoker = cardService.flushSuitRecognizer(cards);
        List<Card> pokerSequence = new ArrayList<>(cards);
        return getSequenceIfItIsStraight(pokerSequence.stream().filter(c -> c.getSuit().equals(suitOfPoker)).collect(Collectors.toList()));
    }


}
