import java.util.LinkedList;
import java.util.List;

/**
 * BoardState class.
 * represent state unit of my board.
 */
public class BoardState {
    //members
    private int n;
    private CommonEnumerations.Operators operation;
    private BoardState parent;
    private int[][] stateArr;
    private int parentRowIndexZero = -1;
    private int parentColIndexZero = -1;
    private int rowIndexZero = -1;
    private int colIndexZero = -1;
    private int depth;

    /**
     * BoardState ctr
     * @param parent - parent state
     * @param N - board size
     * @param op - operation
     * @param stateArr - double int arr
     */
    public BoardState(BoardState parent, int N, CommonEnumerations.Operators op, int[][] stateArr) {

        this.parent = parent;
        this.stateArr = new int[N][N];
        this.operation = op;
        this.n = N;
        if (parent == null) { //root
            this.stateArr = stateArr;
            this.depth = 0;
        } else { //successor
            this.initializeBoard();
            this.depth = parent.depth + 1;
        }
        this.initializeCenter();
    }

    /**
     * getHuristic function.
     * @return manheten huristic.
     */
    public int getHuristic() {
        int huristic = 0;
        int rightPlaceRow;
        int rightPlaceCol;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = this.stateArr[i][j];
                if (val!=0) {
                    rightPlaceCol= ((val % this.n) + this.n - 1) % this.n;
                    rightPlaceRow = (int)(Math.ceil((float)val/this.n)) - 1;
                } else {
                    rightPlaceRow = this.n - 1;
                    rightPlaceCol = this.n - 1;
                }
                huristic += Math.abs(i - rightPlaceRow) + Math.abs(j - rightPlaceCol);
            }
        }
        return huristic;
    }

    /**
     * getDepth function/
     * @return depth of the state.
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * getSize function.
     * @return size of board.
     */
    public int getSize() {
        return this.n;
    }

    /**
     * initializeCenter function.
     * initialize the state center.
     */
    private void initializeCenter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (stateArr[i][j] == 0) {
                    this.rowIndexZero = i;
                    this.colIndexZero = j;
                }
            }
        }
    }

    /**
     * initializeBoard function.
     * initialize board values acording to the apecified operation.
     */
    private void initializeBoard() {
        if (parent == null) {
            return;
        }
        int[][] parentArr = this.parent.getStateArr();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.stateArr[i][j] = parentArr[i][j];
                if (stateArr[i][j] == 0) {
                    parentRowIndexZero = i;
                    parentColIndexZero = j;
                }
            }
        }
        switch (this.operation) {
            case Up:
                this.up();
                break;
            case Down:
                this.down();
                break;
            case Left:
                this.left();
                break;
            case Right:
                this.right();
                break;
        }
    }

    /**
     * down function.
     * operates down operation.
     */
    private void down() {
        if (this.parentRowIndexZero > 0) {
            int temp = this.stateArr[parentRowIndexZero - 1][parentColIndexZero];
            this.stateArr[parentRowIndexZero - 1][parentColIndexZero] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }
    }

    /**
     * up function.
     * operates up operation.
     */
    private void up() {
        if (parentRowIndexZero < n - 1) {
            int temp = this.stateArr[parentRowIndexZero + 1][parentColIndexZero];
            this.stateArr[parentRowIndexZero + 1][parentColIndexZero] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }
    }

    /**
     * right function.
     * operates right operation.
     */
    private void right() {
        if (this.parentColIndexZero > 0) {
            int temp = this.stateArr[parentRowIndexZero][parentColIndexZero - 1];
            this.stateArr[parentRowIndexZero][parentColIndexZero - 1] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }

    }

    /**
     * left function.
     * operates left operation.
     */
    private void left() {
        if (parentColIndexZero < n - 1) {
            int temp = this.stateArr[parentRowIndexZero][parentColIndexZero + 1];
            this.stateArr[parentRowIndexZero][parentColIndexZero + 1] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }
    }

    /**
     * getOperation function.
     * @return the state operation.
     */
    public CommonEnumerations.Operators getOperation() {
        return operation;
    }

    /**
     * getParent function.
     * gets parent of current state.
     * @return
     */
    public BoardState getParent() {
        return parent;
    }

    /**
     * getStateArr function.
     * @return double int arr represents the state.
     */
    public int[][] getStateArr() {
        return stateArr;
    }

    /**
     * getValidSuccessorsOperations function.
     * @return list of valid succesors operations.
     */
    public List<CommonEnumerations.Operators> getValidSuccessorsOperations() {
        List<CommonEnumerations.Operators> validOperators = new LinkedList<>();
        if (this.rowIndexZero < n - 1) {
            validOperators.add(CommonEnumerations.Operators.Up);
        }
        if (this.rowIndexZero > 0) {
            validOperators.add(CommonEnumerations.Operators.Down);
        }
        if (this.colIndexZero <  n - 1 ) {
            validOperators.add(CommonEnumerations.Operators.Left);
        }
        if (this.colIndexZero > 0) {
            validOperators.add(CommonEnumerations.Operators.Right);
        }
        return  validOperators;
    }

    /**
     * isGoal function.
     * @return true if this state is goal, false otherwise.
     */
    public boolean isGoal() {
        boolean isGoal = true;
        int counter = 1;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.stateArr[i][j] != counter && !(i == this.n -1 && j == this.n -1)) {
                    return false;
                }
             counter++;
            }
        }
        if (this.rowIndexZero!= n-1 || this.colIndexZero!=n-1) {
            isGoal = false;
        }
        return isGoal;
    }

    /**
     * isEqual function.
     * @param other - state obj for compare.
     * @return true if equal, false otherwise.
     */
    public boolean isEqual(BoardState other) {
        boolean isEqual = true;
        for (int i = 0; i < this.n; i ++) {
            for (int j = 0; j < this.n; j ++) {
                if (this.stateArr[i][j] != other.getStateArr()[i][j]) {
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    /**
     * print function.
     * prints the board.
     * only for debug.
     */
    public void print() {
        System.out.println("***********");
        for (int i = 0; i < this.n; i ++) {
            System.out.println();
            for (int j = 0; j < this.n; j ++) {
               System.out.print(this.stateArr[i][j]);
            }
        }
        System.out.println();
        System.out.println("***********");
    }
}
