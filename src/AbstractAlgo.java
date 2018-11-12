import java.util.LinkedList;
import java.util.List;


/**
 * AbstractAlgo class.
 * represent algorithm unit and contains shared functions.
 */
public abstract class AbstractAlgo implements IAlgo{
    //members
    protected List<BoardState> closeList;
    protected int specificCharacteristic;
    protected BoardState current;
    protected BoardState goal;

    /**
     * ctr.
     * initialize the closed list.
     */
    public AbstractAlgo() {
        this.closeList = new LinkedList<>();
    }

    /**
     * operateAlgo function.
     * operate the search algo.
     * @param root - root srate.
     */
    @Override
    public abstract void operateAlgo(BoardState root);

    /**
     * getPath function.
     * @return the path from root to goal.
     */
    @Override
    public String getPath() {
        BoardState temp;
        temp = this.goal;
        StringBuilder sb = new StringBuilder();
        while (temp!=null && temp.getParent()!=null) {
            sb.insert(0,CommonEnumerations.getStr(temp.getOperation()));
            temp = temp.getParent();
        }
        return sb.toString();
    }

    /**
     * getNumOfClosedListMembers function.
     * @return number of close list members at the end of the algo.
     */
    @Override
    public int getNumOfClosedListMembers() {
        return this.closeList.size();
    }

    /**
     * getSpacificCharacteristic function.
     * @return specified cost.
     */
    @Override
    public abstract int getSpacificCharacteristic();
}
