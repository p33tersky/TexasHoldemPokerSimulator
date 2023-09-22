import java.util.Map;

public class Card {
    public String picture; //figura
    public String suit; //kolor

    public String getPicture() {
        return picture;
    }

    public String getSuit() {
        return suit;
    }

    public Card(String pictureCards, String suit) {
        this.picture = pictureCards;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "picture='" + picture + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
