import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    public void simulateTillOnePlayerHasFourOfAKindAndSecondHasPoker() {
        List<Card> deck = cardService.getDeckOfCards();
        List<Card> cardsOnTheTable = new ArrayList<>();
        int i = 1;
        boolean isPokerAt1 = false;
        boolean isFourOfAKindAt2 = false;
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

            System.out.println("Rozdanie " + i + ":");
            System.out.println("Karty Gracza 1:" + position1.getCard1() + ", " + position1.getCard2());
            System.out.println("Karty Gracza 2:" + position2.getCard1() + ", " + position2.getCard2());
            System.out.println("Karty na stole: " + cardsOnTheTable);
            int compareValue = pokerSequenceComparator.compareSequences(firstPlayerCards, secondPlayerCards);

            isPokerAt1 = sequenceCheckers.isPoker(firstPlayerCards);
            isFourOfAKindAt2 = sequenceCheckers.isFourOfAKind(secondPlayerCards);

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
            i++;


        } while (!(isPokerAt1 && isFourOfAKindAt2));
    }

    public void simulate_N_dealsAgainstGivenPositionsAndReturnEachEffectiveness(Position position1, Position position2, int n) {
        List<Card> deck = cardService.getDeckOfCards();
        List<Card> playersCards = new ArrayList<>(List.of(position1.getCard1(), position1.getCard2(), position2.getCard1(), position2.getCard2()));

        int firstPlayerWinCounter = 0;
        int secondPlayerWinCounter = 0;
        int drawCounter = 0;


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


            String firstSequenceName = pokerSequenceComparator.pokerHand(firstPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> firstPlayerSequence = pokerSequenceComparator.pokerHand(firstPlayerCards).getBestSequence();
            String secondSequenceName = pokerSequenceComparator.pokerHand(secondPlayerCards).getPokerSequenceType().sequenceName;
            List<Card> secondPlayerSequence = pokerSequenceComparator.pokerHand(secondPlayerCards).getBestSequence();

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


        double win1Rate = (double) firstPlayerWinCounter / n;
        double win2Rate = (double) secondPlayerWinCounter / n;
        double drawRate = (double) drawCounter / n;

        System.out.println("Win 1 rate: " + win1Rate);
        System.out.println("Win 2 rate: " + win2Rate);
        System.out.println("Draw rate: " + drawRate);

    }
}
