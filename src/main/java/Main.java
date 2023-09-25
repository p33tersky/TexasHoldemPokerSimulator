import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

//        public List<String> suits() {
//            return List.of("♠", "♣", "♦", "♥");
//        }

        DealService dealService = new DealService();

//        dealService.simulateNGames(10);
        Position position1 = new Position(new Card("K", "♠"), new Card("Q", "♣"));
        Position position2 = new Position(new Card("10", "♠"), new Card("10", "♥"));
        dealService.simulate_N_dealsAgainstGivenPositionsAndReturnEachEffectiveness(position1, position2, 1000000);

    }

}
