import javax.sound.midi.Sequence;
import java.util.*;
import java.util.stream.Collectors;

public class SequenceCheckers {

    CardService cardService = new CardService();

    public int suitCounter(String suit, List<Card> cards) {
        return (int) cards.stream().filter(c -> c.getSuit().equals(suit)).count();
    }


    public Map<String, Boolean> isFlushMap(List<Card> cards) {
        Map<String, Boolean> flushMap = new HashMap<>();
        List<String> suits = cardService.suits();

        for (String suit : suits) {
            int suitChecker = suitCounter(suit, cards);
            flushMap.put(suit, suitChecker > 4);
        }

        return flushMap;
    }

    public boolean isFlush(List<Card> cards) {
        Map<String, Boolean> flashMap = isFlushMap(cards);
        List<String> suits = cardService.suits();

        for (String suit : suits) {
            if (flashMap.get(suit)) {
                return true;
            }
        }
        return false;
    }

    public int pictureCounter(String picture, List<Card> cards) {
        return (int) cards.stream().filter(c -> c.getPicture().equals(picture)).count();
    }

    public boolean isNOfAKind(int n, List<Card> cards) {
        int counter;
        for (int i = 0; i < cards.size(); i++) {
            counter = pictureCounter(cards.get(i).getPicture(), cards);
            if (counter == n) {
                return true;
            }
        }
        return false;
    }

    public boolean isFourOfAKind(List<Card> cards) {
        return isNOfAKind(4, cards);
    }

    public boolean isThreeOfAKind(List<Card> cards) {
        return isNOfAKind(3, cards);
    }

    public boolean isPair(List<Card> cards) {
        return isNOfAKind(2, cards);
    }

    public List<Card> listOfCardsWithRemovedCardsWithGivenPicture(String picture, List<Card> allCards) {
        List<Card> listToBeReduced = new ArrayList<>(allCards);
        listToBeReduced.removeIf(card -> card.getPicture().equals(picture));
        return listToBeReduced;
    }

    public boolean isPairInCardsWithoutNSameCards(int n, List<Card> cards) {
        List<Card> playerCards = new ArrayList<>(cards);

        int counter;
        String picture;

        if (isNOfAKind(n, playerCards)) {
            for (int i = 0; i < playerCards.size(); i++) {
                picture = playerCards.get(i).getPicture();
                counter = pictureCounter(picture, playerCards);
                if (counter == n) {
                    playerCards = listOfCardsWithRemovedCardsWithGivenPicture(picture, playerCards);
                    break;
                }
            }
            return isPair(playerCards);
        }
        return false;
    }

    public boolean isTwoPairs(List<Card> cards) {
        return isPairInCardsWithoutNSameCards(2, cards);
    }

    public boolean isFullHouse(List<Card> cards) {
        return isPairInCardsWithoutNSameCards(3, cards);
    }

    public boolean isValueOfCardAtIndexNOnePointHigherThanItsPrevious(int nCard, List<Card> cards) {
        Card card2 = cards.get(nCard);
        Card card1 = cards.get(nCard - 1);
        int valueOfCard2 = cardService.cardsValue().get(card2.getPicture());
        int valueOfCard1 = cardService.cardsValue().get(card1.getPicture());
        return valueOfCard2 - valueOfCard1 == 1;
    }

    public boolean areFiveCardsConsecutive(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            if (!isValueOfCardAtIndexNOnePointHigherThanItsPrevious(i, cards)) {
                return false;
            }
        }
        return true;
    }


    public boolean isStraightSpecialCase(List<Card> cards) {
        for (int n = 2; n < 6; n++) {
            int finalN = n;
            boolean isN = cards.stream().anyMatch(c -> c.getPicture().equals(String.valueOf(finalN)));
            if (!isN){
                return false;
            }
        }
        return cards.stream().anyMatch(c -> c.getPicture().equals("A"));
    }


    public boolean isStraight(List<Card> cards) {
        List<Card> sortedCardsByValues = cardService.sortedListOfCardsByItsValue(cards);
        List<Card> cardsFrom_I_to_IplusJ = new ArrayList<>();


        for (int i = 0; i < cards.size() - 4; i++) {
            for (int j = 0; j < 5; j++) {
                cardsFrom_I_to_IplusJ.add(sortedCardsByValues.get(i + j));
            }
            if (areFiveCardsConsecutive(cardsFrom_I_to_IplusJ)) {
                return true;
            } else {
                cardsFrom_I_to_IplusJ.clear();
            }
        }
        return isStraightSpecialCase(cards);
    }

    public boolean isPoker(List<Card> cards) {
        if (!isFlush(cards)) {
            return false;
        }
        Map<String, Boolean> flushMap = isFlushMap(cards);
        String flushSuit = "";
        List<String> colors = cardService.suits();
        for (String color : colors) {
            if (flushMap.get(color)) {
                flushSuit = color;
            }
        }
        String finalFlushSuit = flushSuit;
        List<Card> cardsInFlushColor = cards.stream()
                .filter(c -> c.getSuit().equals(finalFlushSuit))
                .collect(Collectors.toList());

        return isStraight(cardsInFlushColor);
    }
}
