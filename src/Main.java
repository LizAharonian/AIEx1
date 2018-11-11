import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lizah on 09/11/2018.
 */
public class Main {

    public static void main(String [ ] args)
    {
        CommonEnumerations.Algo algorithm = null;
        int n = 0;
        int stateArr[][] = null;
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                // process the line
                if (lineNum == 0) {
                    switch (Integer.valueOf(line.trim())) {
                        case 1:
                            algorithm = CommonEnumerations.Algo.IDS;
                            break;
                        case 2:
                            algorithm = CommonEnumerations.Algo.BFS;
                            break;
                        case 3:
                            algorithm = CommonEnumerations.Algo.A_STAR;
                            break;
                    }
                }  else if (lineNum == 1) {
                    n = Integer.valueOf(line.trim());
                } else if (lineNum == 2) {
                    String[] splittedArr = line.trim().split("-");
                    stateArr = new int[n][n];
                    List<Integer> numbersForInitialize = Arrays.stream(line.split("-"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            stateArr[i][j] = numbersForInitialize.get(n * i + j);
                        }
                    }
                }

                lineNum++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BoardState root = new BoardState(null, n , null, stateArr);
        AlgoFactory algoFactory = new AlgoFactory(algorithm);
        IAlgo algo = algoFactory.getAlgorithm();
        algo.operateAlgo(root);
        String path = algo.getPath();
        System.out.println(path);


    }
}
