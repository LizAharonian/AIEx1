import java.util.LinkedList;
import java.util.List;

/**
 * Created by lizah on 11/11/2018.
 */
public abstract class AbstractAlgo implements IAlgo{
    protected List<BoardState> closeList;
    protected int specificCharacteristic;
    protected BoardState current;

    public AbstractAlgo() {
        this.closeList = new LinkedList<>();
    }

    @Override
    public abstract void operateAlgo(BoardState root);

    @Override
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        while (current!=null && current.getParent()!=null) {
            sb.insert(0,CommonEnumerations.getStr(current.getOperation()));
            current = current.getParent();
        }
        return sb.toString();
    }

    @Override
    public int getNumOfClosedListMembers() {
        return this.closeList.size();
    }

    @Override
    public abstract int getSpacificCharacteristic();
}
