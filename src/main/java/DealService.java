import java.util.*;

public class DealService {
    PokerSequenceComparator pokerSequenceComparator = new PokerSequenceComparator();
    CardService cardService = new CardService();
    SequenceCheckers sequenceCheckers = new SequenceCheckers();


    public void simulateNGames(int n) {
        List<Card> deck = cardService.getDeckOfCards();
        List<Card> cardsOnTheTable = new ArrayList<>();

        for (int i = 1; i < n + 1; i++) {
            Collections.shuffle(deck);
            Position position1 = new Position(deck.get(0), deck.get(2));
            Position position2 = new Position(deck.get(1), deck.get(3));
            for (int j = 4; j < 9; j++) {
                cardsOnTheTable.add(deck.get(j));
            }
            List<Card> firstPlayerCards = new ArrayList<>(List.of(position1.getCard1(), position1.getCard2()));
            firstPlayerCards.addAll(cardsOnTheTable);

            List<Card> secondPlayerCards = new ArrayList<>(List.of(position2.getCard1(), position2.getCard2()));
            secondPlayerCards.addAll(cardsOnTheTable);

            System.out.println("Rozdanie " + i + ":");
            System.out.println("Karty Gracza 1:" + position1.getCard1() + ", " + position1.getCard2());
            System.out.println("Karty Gracza 2:" + position2.getCard1() + ", " + position2.getCard2());
            System.out.println("Karty na stole: " + cardsOnTheTable);
            int compareValue = pokerSequenceComparator.compareSequences(firstPlayerCards, secondPlayerCards);


            String firstSequenceName = pokerSequenceComparator.pokerHand(firstPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> firstPlayerSequence = pokerSequenceComparator.pokerHand(firstPlayerCards).getBestSequence();
            String secondSequenceName = pokerSequenceComparator.pokerHand(secondPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> secondPlayerSequence = pokerSequenceComparator.pokerHand(secondPlayerCards).getBestSequence();
            System.out.println("Gracz 1 ma " + firstSequenceName + ": " + firstPlayerSequence);
            System.out.println("Gracz 2 ma " + secondSequenceName + ": " + secondPlayerSequence);
            if (compareValue == 1) {
                System.out.println("Wygrał gracz 1");
            } else if (compareValue == 0) {
                System.out.println("Remis");
            } else {
                System.out.println("Wygrał gracz 2");
            }
            System.out.println();
            System.out.println("****");
            System.out.println();
            firstPlayerCards.clear();
            secondPlayerCards.clear();
            cardsOnTheTable.clear();
        }


    }

    public Map<String, Boolean> sequenceNameToBooleanMap(List<Card> cards){
        Map<String, Boolean> xSequenceMap = new HashMap<>();
        xSequenceMap.put("poker", sequenceCheckers.isPoker(cards));
        xSequenceMap.put("four of a kind", sequenceCheckers.isFourOfAKind(cards));
        xSequenceMap.put("full house", sequenceCheckers.isFullHouse(cards));
        xSequenceMap.put("flush", sequenceCheckers.isFlush(cards));
        xSequenceMap.put("straight", sequenceCheckers.isStraight(cards));
        xSequenceMap.put("three of a kind", sequenceCheckers.isThreeOfAKind(cards));
        xSequenceMap.put("two pairs", sequenceCheckers.isTwoPairs(cards));
        xSequenceMap.put("pair", sequenceCheckers.isPair(cards));
        xSequenceMap.put("high card", sequenceCheckers.isHighCard(cards));
        return xSequenceMap;
    }

    public boolean is_SequenceX_inGivenCards(PokerSequenceType pokerSequenceType, List<Card> cards){
        return sequenceNameToBooleanMap(cards).get(pokerSequenceType.getSequenceName());
    }

    public boolean isSequenceXInAnyoneCards(PokerSequenceType pokerSequenceType, List<Card> firstPlayerCards, List<Card> secondPlayerCards){
        return is_SequenceX_inGivenCards(pokerSequenceType,firstPlayerCards) ||
                is_SequenceX_inGivenCards(pokerSequenceType,secondPlayerCards);
    }


    public void simulateTillOnePlayerHas_XSequence_AndSecondHas_YSequence(PokerSequenceType firstPokerSequenceType, PokerSequenceType secondPokerSequenceType) {
        List<Card> deck = cardService.getDeckOfCards();
        List<Card> cardsOnTheTable = new ArrayList<>();
        int i = 1;
        boolean areGivenSequencesInPlayersHands;
        do {

            Collections.shuffle(deck);
            Position position1 = new Position(deck.get(0), deck.get(2));
            Position position2 = new Position(deck.get(1), deck.get(3));
            for (int j = 4; j < 9; j++) {
                cardsOnTheTable.add(deck.get(j));
            }
            List<Card> firstPlayerCards = new ArrayList<>(List.of(position1.getCard1(), position1.getCard2()));
            firstPlayerCards.addAll(cardsOnTheTable);

            List<Card> secondPlayerCards = new ArrayList<>(List.of(position2.getCard1(), position2.getCard2()));
            secondPlayerCards.addAll(cardsOnTheTable);

            System.out.println("Deal number " + i + ":");
            System.out.println("Player 1 cards: " + position1.getCard1() + ", " + position1.getCard2());
            System.out.println("Player 2 cards: " + position2.getCard1() + ", " + position2.getCard2());
            System.out.println("Cards on the table: " + cardsOnTheTable);
            int compareValue = pokerSequenceComparator.compareSequences(firstPlayerCards, secondPlayerCards);



            String firstSequenceName = pokerSequenceComparator.pokerHand(firstPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> firstPlayerSequence = pokerSequenceComparator.pokerHand(firstPlayerCards).getBestSequence();
            String secondSequenceName = pokerSequenceComparator.pokerHand(secondPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> secondPlayerSequence = pokerSequenceComparator.pokerHand(secondPlayerCards).getBestSequence();


            boolean isSeqXonPos1AndSeqYonPos2 = firstPokerSequenceType.getSequenceName().equals(firstSequenceName) && secondPokerSequenceType.getSequenceName().equals(secondSequenceName);
            boolean isSeqYonPos2AndSeqXonPos1 = firstPokerSequenceType.getSequenceName().equals(secondSequenceName) && secondPokerSequenceType.getSequenceName().equals(firstSequenceName);
            areGivenSequencesInPlayersHands = isSeqXonPos1AndSeqYonPos2 || isSeqYonPos2AndSeqXonPos1;

            System.out.println("Player 1 has " + firstSequenceName + ": " + firstPlayerSequence);
            System.out.println("Player 2 has " + secondSequenceName + ": " + secondPlayerSequence);
            if (compareValue == 1) {
                System.out.println("Player 1 wins");
            } else if (compareValue == 0) {
                System.out.println("Draw");
            } else {
                System.out.println("Player 2 wins");
            }
            System.out.println();
            System.out.println("****");
            System.out.println();
            firstPlayerCards.clear();
            secondPlayerCards.clear();
            cardsOnTheTable.clear();
            i++;


        } while (!(areGivenSequencesInPlayersHands));
    }

    public void simulate_N_dealsAgainstGivenPositionsAndReturnEachEffectiveness(Position position1, Position position2, int n) {
        List<Card> deck = cardService.getDeckOfCards();
        List<Card> playersCards = new ArrayList<>(List.of(position1.getCard1(), position1.getCard2(), position2.getCard1(), position2.getCard2()));

        float firstPlayerWinCounter = 0;
        float secondPlayerWinCounter = 0;
        float drawCounter = 0;


        for (int i = 0; i < n; i++) {
            Collections.shuffle(deck);

            List<Card> cardsOnTheTable = new ArrayList<>();
            int deckCardIndex = 0;
            while (cardsOnTheTable.size() < 5) {
                if (!cardService.isGivenCardInGivenList(playersCards, deck.get(deckCardIndex))) {
                    cardsOnTheTable.add(deck.get(deckCardIndex));
                }
                deckCardIndex++;
            }
            List<Card> firstPlayerCards = new ArrayList<>(List.of(position1.getCard1(), position1.getCard2()));
            firstPlayerCards.addAll(cardsOnTheTable);

            List<Card> secondPlayerCards = new ArrayList<>(List.of(position2.getCard1(), position2.getCard2()));
            secondPlayerCards.addAll(cardsOnTheTable);

            int compareValue = pokerSequenceComparator.compareSequences(firstPlayerCards, secondPlayerCards);

            if (compareValue == 1) {
                firstPlayerWinCounter++;
            } else if (compareValue == 0) {
                drawCounter++;

            } else {
                secondPlayerWinCounter++;

            }

            firstPlayerCards.clear();
            secondPlayerCards.clear();
            cardsOnTheTable.clear();
        }


        float win1Rate = firstPlayerWinCounter / n;
        float win2Rate = secondPlayerWinCounter / n;
        float drawRate = drawCounter / n;

        System.out.println("Win 1 rate: " + win1Rate);
        System.out.println("Win 2 rate: " + win2Rate);
        System.out.println("Draw rate: " + drawRate);

    }
}
