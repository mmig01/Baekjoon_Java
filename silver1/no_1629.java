import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class no_1629 {
    private int num;
    private int exponent;
    private int mod;
    HashMap<Integer , Integer> hm = new HashMap<Integer , Integer>();
    private long getResult(int length) {
        long result;
        if (length == 0) {
            result = 1;
        } else if (length == 1) {
            if (num < mod) {
                result = num - mod;
            } else {
                result = num % mod;
            }
        } else {
            try {
                result = hm.get(length);
            } catch (NullPointerException e) {
                long curMod = (getResult(length / 2) % mod) * (getResult(length - length / 2)) % mod;
                hm.put(length , (int)(curMod % mod));
                result = curMod;
            }
        }
        return result;
    }
    private void showResult() {
        long result = getResult(exponent);
        if (result < 0) {
            result = mod + result;
        }
        System.out.println(result);
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        exponent = Integer.parseInt(st.nextToken());
        mod = Integer.parseInt(st.nextToken());
        showResult();
        br.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
