import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java_ex1 class.
 * contains the main method which runs the program.
 */
public class java_ex1 {
    /**
     * main function.
     * runs the program.
     * @param args
     */

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
                    stateArr = new int[n][n];
                    List<Integer> numbersForInitialize = Arrays.stream(line.trim().split("-"))
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
        //create root
        BoardState root = new BoardState(null, n , null, stateArr);
        //create algo
        AlgoFactory algoFactory = new AlgoFactory(algorithm);
        IAlgo algo = algoFactory.getAlgorithm();
        algo.operateAlgo(root);
        try {
            //write to output file
            writeOutputFile(args[1], algo.getPath(), algo.getNumOfClosedListMembers(), algo.getSpacificCharacteristic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * writeOutputFile function.
     * @param outputFileName - name of output file.
     * @param path - path to goal state.
     * @param numOfClosedList - num of states in closed list.
     * @param specificCharacteristic - cost of each algo.
     * @throws IOException
     */
    public static void writeOutputFile(String outputFileName, String path, int numOfClosedList, int specificCharacteristic) throws IOException {
        File fout = new File(outputFileName);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write(path + " " + String.valueOf(numOfClosedList) + " " +String.valueOf(specificCharacteristic));
        bw.close();
    }
}
