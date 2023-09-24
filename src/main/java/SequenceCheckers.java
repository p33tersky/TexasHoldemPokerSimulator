import java.util.*;
import java.util.stream.Collectors;

public class SequenceCheckers {

    CardService cardService = new CardService();


    public boolean isFlush(List<Card> cards) {
        Map<String, Boolean> flashMap = cardService.isFlushMap(cards);
        List<String> suits = cardService.suits();

        for (String suit : suits) {
            if (flashMap.get(suit)) {
                return true;
            }
        }
        return false;
    }



    public boolean isFourOfAKind(List<Card> cards) {
        return cardService.isNOfAKind(4, cards);
    }

    public boolean isThreeOfAKind(List<Card> cards) {
        return cardService.isNOfAKind(3, cards);
    }

    public boolean isPair(List<Card> cards) {
        return cardService.isNOfAKind(2, cards);
    }


    public boolean isPairInCardsWithoutNSameCards(int n, List<Card> cards) {
        List<Card> playerCards = new ArrayList<>(cards);

        int counter;
        String picture;

        if (cardService.isNOfAKind(n, playerCards)) {
            for (int i = 0; i < playerCards.size(); i++) {
                picture = playerCards.get(i).getPicture();
                counter = cardService.pictureCounter(picture, playerCards);
                if (counter == n) {
                    playerCards = cardService.listOfCardsWithRemovedCardsWithGivenPicture(picture, playerCards);
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

    public boolean isStraightSpecialCase(List<Card> cards) {
        for (int n = 2; n < 6; n++) {
            int finalN = n;
            boolean isN = cards.stream().anyMatch(c -> c.getPicture().equals(String.valueOf(finalN)));
            if (!isN) {
                return false;
            }
        }
        return cards.stream().anyMatch(c -> c.getPicture().equals("A"));
    }


    public boolean isStraight(List<Card> cards) {
        List<Card> sortedCardsByValues = cardService.sortedListOfCardsByItsValue(true, cards);
        List<Card> cardsFrom_I_to_IplusJ = new ArrayList<>();


        for (int i = 0; i < cards.size() - 4; i++) {
            for (int j = 0; j < 5; j++) {
                cardsFrom_I_to_IplusJ.add(sortedCardsByValues.get(i + j));
            }
            if (cardService.areFiveCardsConsecutive(cardsFrom_I_to_IplusJ)) {
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
        Map<String, Boolean> flushMap = cardService.isFlushMap(cards);
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
