/**
 * CommonEnumerations.
 * contains common enumerations for my search algorithms.
 */
public class CommonEnumerations {

    /**
     * Operators enum.
     * represent movment of the zero element.
     */
    enum Operators {
        Up, Down, Left, Right;
    }

    /**
     * getStr function.
     * @param op - operators enum.
     * @return start string from enum name.
     */
    public static String getStr(Operators op) {
        return op.name().substring(0,1);
    }

    /**
     * Algo enum.
     * represent type of enum.
     */
    enum Algo {
        IDS, BFS, A_STAR;
    }


}
