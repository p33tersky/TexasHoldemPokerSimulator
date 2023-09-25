import java.util.*;
import java.util.stream.Collectors;

public class CardService {


    public List<String> suits() {
        return List.of("♠", "♣", "♦", "♥");
    }

    public List<String> pictures() {
        return List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    }

    public List<Card> getDeckOfCards() {
        List<Card> cards = new ArrayList<>();
        List<String> pictures = pictures();
        List<String> suits = suits();
        for (String suit : suits) {
            for (String picture : pictures) {
                cards.add(new Card(picture, suit));
            }
        }
        return cards;
    }

    public Map<String, Integer> cardsValue() {
        Map<String, Integer> picturesToValueMap = new HashMap<>();
        for (int i = 2; i < 11; i++) {
            picturesToValueMap.put(String.valueOf(i), i);
        }
        picturesToValueMap.put("J", 11);
        picturesToValueMap.put("Q", 12);
        picturesToValueMap.put("K", 13);
        picturesToValueMap.put("A", 14);

        return picturesToValueMap;
    }

    public int suitCounter(String suit, List<Card> cards) {
        return (int) cards.stream().filter(c -> c.getSuit().equals(suit)).count();
    }

    public int pictureCounter(String picture, List<Card> cards) {
        return (int) cards.stream().filter(c -> c.getPicture().equals(picture)).count();
    }

    public int compareValueOfTwoCards(Card card1, Card card2) {
        return Integer.compare(cardsValue().get(card1.getPicture()), cardsValue().get(card2.getPicture()));
    }

    public boolean isValueOfCardAtIndexNOnePointHigherThanItsPrevious(int nCard, List<Card> cards) {
        Card card2 = cards.get(nCard);
        Card card1 = cards.get(nCard - 1);
        int valueOfCard2 = cardsValue().get(card2.getPicture());
        int valueOfCard1 = cardsValue().get(card1.getPicture());
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

    public List<Card> sortedListOfCardsByItsValue(boolean ascending, List<Card> cards) {
        List<Card> cardsToBeSorted = new ArrayList<>(cards);
        Comparator<Card> cardComparator = (card1, card2) -> {
            int value1 = cardsValue().get(card1.getPicture());
            int value2 = cardsValue().get(card2.getPicture());
            if (ascending) return Integer.compare(value1, value2);
            return Integer.compare(value2, value1);
        };
        cardsToBeSorted.sort(cardComparator);
        return cardsToBeSorted;
    }

    public Card getCardWithTheHighestValue(List<Card> cards) {
        return sortedListOfCardsByItsValue(false, cards).get(0);
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

    public List<Card> getHighestNOfAKind(int n, List<Card> cards) {
        List<Card> playerCards = sortedListOfCardsByItsValue(false, cards);
        String picture = "";
        for (int i = 0; i < playerCards.size(); i++) {
            String pictureOfICard = playerCards.get(i).getPicture();
            if (pictureCounter(pictureOfICard, playerCards) == n) {
                picture = pictureOfICard;
                break;
            }
        }
        String finalPicture = picture;
        return cards.stream().filter(c -> c.getPicture().equals(finalPicture)).collect(Collectors.toList());
    }

    public List<Card> getPair(List<Card> cards) {
        return getHighestNOfAKind(2, cards);
    }

    public List<Card> getNHighestCards(int n, List<Card> cards) {
        List<Card> sortedCarts = sortedListOfCardsByItsValue(false, cards);
        List<Card> nCards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nCards.add(sortedCarts.get(i));
        }
        return nCards;
    }

    public List<Card> listOfCardsWithRemovedCardsWithGivenPicture(String picture, List<Card> allCards) {
        List<Card> listToBeReduced = new ArrayList<>(allCards);
        listToBeReduced.removeIf(card -> card.getPicture().equals(picture));
        return listToBeReduced;
    }

    public Map<String, Boolean> isFlushMap(List<Card> cards) {
        Map<String, Boolean> flushMap = new HashMap<>();

        for (String suit : suits()) {
            int suitChecker = suitCounter(suit, cards);
            flushMap.put(suit, suitChecker > 4);
        }

        return flushMap;
    }

    public String flushSuitRecognizer(List<Card> cards) {
        String flushSuit = "";
        List<String> suits = suits();
        Map<String, Boolean> flushMap = isFlushMap(cards);
        for (String suit : suits) {
            if (flushMap.get(suit)) {
                flushSuit = suit;
            }
        }
        return flushSuit;
    }


    public boolean isSameCardRecognizer(Card card1 ,Card card2){
        return card1.getSuit().equals(card2.getSuit()) && card1.getPicture().equals(card2.getPicture());
    }

    public boolean isGivenCardInGivenList(List<Card> cards, Card card){
        for (int i = 0; i < cards.size(); i++) {
            if (isSameCardRecognizer(cards.get(i), card)){
                return true;
            }
        }
        return false;
    }

}
