import java.util.*;

/**
 * BFS class.
 * implement of BFS algorithm and extends the abstract class.
 */
public class BFS extends AbstractAlgo {
    //member
    private Queue<BoardState> openList;
    /**
     * operateAlgo function.
     * operate the search algo.
     * @param root - root srate.
     */
    @Override
    public void operateAlgo(BoardState root) {
        super.current = root;
        openList = new LinkedList<BoardState>();
        openList.add(current);
        while (!openList.isEmpty()) {
            current = openList.remove();
            super.closeList.add(current);
            current.print();
            //check if current state is goal
            if (current.isGoal()) {
                super.goal = current;
                break;
            } else {
                List<CommonEnumerations.Operators> operators = current.getValidSuccessorsOperations();
                for (CommonEnumerations.Operators op: operators) {
                    BoardState successor = new BoardState(super.current, super.current.getSize(), op, null);
                    System.out.println("ssssss");
                    successor.print();
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
        return 0;
    }
}
