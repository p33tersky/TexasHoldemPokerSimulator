import Exceptions.PictureNotValidException;
import Exceptions.SuitNotValidException;

import java.util.List;

public class Card {
    public String picture; //figura
    public String suit; //kolor

    public String getPicture() {
        return picture;
    }

    public String getSuit() {
        return suit;
    }

    public List<String> suits = List.of("♠", "♣", "♦", "♥");
    public List<String> pictures = List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");



    public Card(String picture, String suit) {
        if (suits.stream().noneMatch(s -> s.equals(suit))){
            throw new SuitNotValidException("Suit is not valid. Use one of this suits: ♠, ♣, ♦, ♥");
        }
        if (pictures.stream().noneMatch(s ->s.equals(picture))){
            throw new PictureNotValidException("Picture is not valid. Use one of this pictures: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A");
        }
        this.picture = picture;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return picture + suit;
    }
}
