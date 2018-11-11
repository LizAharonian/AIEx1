import java.util.*;

/**
 * Created by lizah on 11/11/2018.
 */
public class BFS extends AbstractAlgo {
    private Queue<BoardState> openList;
    @Override
    public void operateAlgo(BoardState root) {
        super.current = root;
        openList = new LinkedList<BoardState>();
        openList.add(current);
        while (!openList.isEmpty()) {
            current = openList.remove();
            super.closeList.add(current);
            current.print();

            if (current.isGoal()) {
                break;
            } else {
                List<CommonEnumerations.Operators> operators = current.getValidSuccessorsOperations();
                for (CommonEnumerations.Operators op: operators) {
                    BoardState successor = new BoardState(super.current,super.current.getSize(),op,null);
                    System.out.println("ssssss");
                    successor.print();
                    if (!isInCloseList(successor) && !isInOpenList(successor)) {
                        openList.add(successor);
                   }
                }
            }
        }
    }

    private boolean isInCloseList(BoardState successor) {
        boolean isExistInCloseList = false;
        for (BoardState item:super.closeList) {
            if(successor.isEqual(item)) {
                isExistInCloseList = true;
                break;
            }
        }
        return isExistInCloseList;
    }

    private boolean isInOpenList(BoardState successor) {
        boolean isExistInOpenList = false;
        for (BoardState item:this.openList) {
            if(successor.isEqual(item)) {
                isExistInOpenList = true;
                break;
            }
        }
        return isExistInOpenList;
    }




    @Override
    public int getSpacificCharacteristic() {
        return 0;
    }
}
