import java.util.*;

/**
 * Astar class.
 * implement of Astar algorithm and extends the abstract class.
 */
public class AStar extends AbstractAlgo {
    //menbers
    private PriorityQueue<BoardState> openList =
            new PriorityQueue<BoardState>( new Comparator<BoardState>() {
                @Override
                public int compare(BoardState o1, BoardState o2) {
                    int fn1 = o1.getDepth() + o1.getHuristic();
                    int fn2 = o2.getDepth() + o2.getHuristic();
                    return fn1-fn2;
                }
            });
    /**
     * operateAlgo function.
     * operate the search algo.
     * @param root - root srate.
     */
    @Override
    public void operateAlgo(BoardState root) {
        super.current = root;
        openList.add(current);
        while (!openList.isEmpty()) {
            current = openList.remove();
            super.closeList.add(current);
            //current.print();
            if (current.isGoal()) {
                super.goal = current;
                current.isGoal();
                break;
            } else {
                List<CommonEnumerations.Operators> operators = current.getValidSuccessorsOperations();
                for (CommonEnumerations.Operators op: operators) {
                    BoardState successor = new BoardState(super.current,super.current.getSize(),op,null);
                    //System.out.println("ssssss");
                    //successor.print();
                    openList.add(successor);
                }
            }
        }
    }

    /**
     * getSpacificCharacteristic function.
     * @return specified cost.
     */
    @Override
    public int getSpacificCharacteristic() {
        int val = 0;
        BoardState temp;
        temp = super.goal;
        while (temp!=null) {
            val += temp.getDepth() + temp.getHuristic();
            temp = temp.getParent();
        }
        return val;
    }
}
