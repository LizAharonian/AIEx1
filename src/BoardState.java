import java.util.LinkedList;
import java.util.List;

/**
 * Created by lizah on 11/11/2018.
 */
public class BoardState {
    enum Operators {
        Up, Down, Left, Right;
    }

    private int n;
    private Operators operation;
    private BoardState parent;
    private int[][] stateArr;
    private int parentRowIndexZero = -1;
    private int parentColIndexZero = -1;
    private int rowIndexZero = -1;
    private int colIndexZero = -1;

    public BoardState(BoardState parent, int N, Operators op, int[][] stateArr) {

        this.parent = parent;
        this.stateArr = new int[N][N];
        this.operation = op;
        this.n = N;
        if (parent == null) { //root
            this.stateArr = stateArr;
        } else { //successor
            this.initializeBoard();
        }
        this.initializeCenter();

    }

    private void initializeCenter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (stateArr[i][j] == 0) {
                    this.rowIndexZero = i;
                    this.rowIndexZero = j;
                }
            }
        }
    }

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

    private void up() {
        if (this.parentRowIndexZero > 0) {
            int temp = this.stateArr[parentRowIndexZero - 1][parentColIndexZero];
            this.stateArr[parentRowIndexZero - 1][parentColIndexZero] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }

    }

    private void down() {
        if (parentRowIndexZero < n - 1) {
            int temp = this.stateArr[parentRowIndexZero + 1][parentColIndexZero];
            this.stateArr[parentRowIndexZero + 1][parentColIndexZero] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }
    }

    private void left() {
        if (this.parentColIndexZero > 0) {
            int temp = this.stateArr[parentRowIndexZero][parentColIndexZero - 1];
            this.stateArr[parentRowIndexZero][parentColIndexZero - 1] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }

    }

    private void right() {
        if (parentRowIndexZero < n - 1) {
            int temp = this.stateArr[parentRowIndexZero][parentColIndexZero + 1];
            this.stateArr[parentRowIndexZero][parentColIndexZero + 1] = 0;
            this.stateArr[parentRowIndexZero][parentColIndexZero] = temp;
        }
    }

    public Operators getOperation() {
        return operation;
    }

    public void setOperation(Operators operation) {
        this.operation = operation;
    }

    public BoardState getParent() {
        return parent;
    }

    public void setParent(BoardState parent) {
        this.parent = parent;
    }

    public int[][] getStateArr() {
        return stateArr;
    }

    public void setStateArr(int[][] stateArr) {
        this.stateArr = stateArr;
    }


    public List<Operators> getValidSuccessorsOperations() {
        List<Operators> validOperators = new LinkedList<>();
        if (this.rowIndexZero > 0) {
            validOperators.add(Operators.Up);
        }
        if (this.rowIndexZero < n - 1) {
            validOperators.add(Operators.Down);
        }
        if (this.colIndexZero > 0) {
            validOperators.add(Operators.Left);
        }
        if (this.colIndexZero <  n - 1 ) {
            validOperators.add(Operators.Right);
        }
        return  validOperators;
    }
}
