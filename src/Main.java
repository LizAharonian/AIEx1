/**
 * Created by lizah on 09/11/2018.
 */
public class Main {

    public static void main(String [ ] args)
    {
        int[] arr = new int[3];
        arr[0] = 0;

        int[] liz = arr.clone();
        liz[0] = 1;
        System.out.println(arr[0]);
        System.out.println(liz[0]);
    }
}
