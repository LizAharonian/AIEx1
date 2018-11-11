/**
 * Created by lizah on 11/11/2018.
 */
public class CommonEnumerations {

    enum Operators {
        Up, Down, Left, Right;
    }
    public static String getStr(Operators op) {
        return op.name().substring(0,1);
    }

    enum Algo {
        IDS, BFS, A_STAR;
    }


}
