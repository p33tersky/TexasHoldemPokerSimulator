import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealService {



    public static Map<Position,Float> calculate(Deal deal){
        Map<Position,Float> chances = new HashMap<>();

        Position position1 = deal.getPosition1();
        Position position2 = deal.getPosition2();

        List<Card> firstPositionCards = List.of(position1.getCard1(), position1.getCard2());
        List<Card> secondPositionCards = List.of(position2.getCard1(), position2.getCard2());

        return chances;
    }




}
