import java.util.List;

public class Deal {

    public Position position1;
    public Position position2;
    public List<Card> cardsOnTheTable;


    public Position getPosition1() {
        return position1;
    }

    public Position getPosition2() {
        return position2;
    }

    public List<Card> getCardsOnTheTable() {
        return cardsOnTheTable;
    }

    public Deal(Position position1, Position position2, List<Card> cardsOnTheTable) {
        this.position1 = position1;
        this.position2 = position2;
        this.cardsOnTheTable = cardsOnTheTable;
    }
}
