/**
 * AlgoFactory class.
 * gets enum and return algorithm.
 */
public class AlgoFactory {
    //member.
    IAlgo algorithm;

    /**
     * ctr.
     * @param algo - get specified enum of algo.
     */
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

    /**
     * getAlgorithm function.
     * @return the created algo.
     */
    public IAlgo getAlgorithm() {
        return algorithm;
    }
}
