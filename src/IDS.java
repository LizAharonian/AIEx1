import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * AbstractAlgo class.
 * represents abstract algorithm unit.
 */
public class IDS extends AbstractAlgo {
    //members
    Stack<BoardState> openList;
    int deep = -1;

    /**
     * operateAlgo function.
     * operate the search algo.
     * @param root - root srate.
     */
    @Override
    public void operateAlgo(BoardState root) {
        int limit = 0;
        while (DFS(root, limit) != true) {
            limit++;
        }
        deep = current.getDepth();
    }

    /**
     * DFS function.
     * runs dfs algo until the specified limit.
     * @param root - state root.
     * @param limit - max depth.
     * @return true if goal was found, false otherwise.
     */
    private boolean DFS(BoardState root, int limit) {
        //initialize containers
        openList = new Stack<>();
        super.closeList = new LinkedList<>();
        super.current = root;
        openList.push(current);
        //run the search
        while (!openList.isEmpty()) {
            current = openList.pop();
            super.closeList.add(current);
            current.print();
            if (current.isGoal()) {
                super.goal = current;
                return true;
            } else {
                if (current.getDepth() == limit) {
                    continue;
                }
                List<CommonEnumerations.Operators> operators = current.getValidSuccessorsOperations();
                for (int i = operators.size() - 1; i >= 0; i--) {
                    CommonEnumerations.Operators op = operators.get(i);
                    BoardState successor = new BoardState(super.current, super.current.getSize(), op, null);
                    openList.push(successor);
                }
            }
        }
        return false;
    }

    /**
     * getSpacificCharacteristic function.
     * @return specified cost.
     */
    @Override
    public int getSpacificCharacteristic() {
        return deep;
    }
}
