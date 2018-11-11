/**
 * Created by lizah on 11/11/2018.
 */
public class AlgoFactory {
    IAlgo algorithm;
    public AlgoFactory(CommonEnumerations.Algo algo) {
        switch (algo) {
            case IDS:
                algorithm = new IDS();
                break;
            case BFS:
                algorithm = new BFS();
                break;
            case A_STAR:
                algorithm = new AStar();
                break;
        }
    }

    public IAlgo getAlgorithm() {
        return algorithm;
    }
}
