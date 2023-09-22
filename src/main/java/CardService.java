import java.util.*;

public class CardService {

    public  List<String> suits(){
        return List.of("♠","♣","♦","♥");
    }

    public List<String> pictures(){
        return List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    }

    public List<Card> getDeckOfCards() {
        List<Card> cards = new ArrayList<>();
        List<String> pictures = pictures() ;
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

    public int compareValueOfTwoCards(Card card1, Card card2){
        return Integer.compare(cardsValue().get(card1.getPicture()), cardsValue().get(card2.getPicture()));
    }
    public Card getCardWithTheHighestValue(List<Card> cards) {
        Card cardWithHighestValue = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            if (compareValueOfTwoCards(cards.get(i),cards.get(i-1))>0) {
                cardWithHighestValue = cards.get(i);
            }
        }
        return cardWithHighestValue;
    }

    public List<Card> getFiveHighestCards(List<Card> cards){
        List<Card> fiveCards = new ArrayList<>();
        List<Card> allCards = new ArrayList<>(cards);
        for (int i = 0; i < 5; i++) {
            Card highestCard = getCardWithTheHighestValue(allCards);
            fiveCards.add(highestCard);
            allCards.remove(highestCard);
        }
        return fiveCards;
    }

    public List<Card> sortedListOfCardsByItsValue(List<Card> cards){
        List<Card> cardsToBeSorted = new ArrayList<>(cards);
        Comparator<Card> cardComparator = (card1, card2) -> {
            int value1 = cardsValue().get(card1.getPicture());
            int value2 = cardsValue().get(card2.getPicture());
            return Integer.compare(value1, value2);
        };
        cardsToBeSorted.sort(cardComparator);
        return cardsToBeSorted;
    }
}
