package GameProgram;

import java.util.Comparator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class IntegerComparator implements Comparator<String> {
    Map<String, Integer> scoreMap;

    private static final Logger logger = (Logger) LogManager.getLogger(IntegerComparator.class);

    /**
     * This initializes the IntegerComparator which will be used in concert with our leaderboard class.
     * @param base
     */
    public IntegerComparator(Map<String, Integer> base) {
        this.scoreMap = base;
        logger.info("Integer Comparator initialized.");
    }

    /**
     * Compares two strings with scores for the leaderboard.
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    public int compare(String o1, String o2) {
        if (scoreMap.get(o1) >= scoreMap.get(o2)) {
            return -1;
        } else {
            return 1;
        }
    }
}
