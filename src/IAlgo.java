/**
 * IAlgo algorithm.
 * represents algo unit functions.
 */
public interface IAlgo {
    /**
     * operateAlgo function.
     * operate the search algo.
     * @param root - root srate.
     */
    public void operateAlgo(BoardState root);

    /**
     * getPath function.
     * @return the path from root to goal.
     */
    public String getPath();

    /**
     * getNumOfClosedListMembers function.
     * @return number of close list members at the end of the algo.
     */
    public int getNumOfClosedListMembers();

    /**
     * getSpacificCharacteristic function.
     * @return specified cost.
     */
    public int getSpacificCharacteristic();
}
