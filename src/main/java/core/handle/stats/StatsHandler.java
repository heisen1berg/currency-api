package core.handle.stats;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface StatsHandler {
    LinkedHashMap<String, Long> getMostPopular();

    LinkedList<String> getUsersTotalValueMoreThan(Double value);

    LinkedList<String> getUsersSingleValueMoreThan(Double value);
}
