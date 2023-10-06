import java.util.List;

public class Deal {

    public List<Position> positions;
    public List<Card> cardsOnTheTable;

    public Deal(List<Position> positions, List<Card> cardsOnTheTable) {
        this.positions = positions;
        this.cardsOnTheTable = cardsOnTheTable;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Card> getCardsOnTheTable() {
        return cardsOnTheTable;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "positions=" + positions +
                ", cardsOnTheTable=" + cardsOnTheTable +
                '}';
    }
}
