import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no_1043 {
    private int[][] party;
    private boolean[] isKnowTruth;
    private boolean[] isPossibleParty;
    private int[] truthPerson;
    private boolean checkContains(int row , int cur) {
        boolean result = false;
        for (int i = 0; i < party[row].length; i++) {
            if (party[row][i] == cur) {
                result = true;
                break;
            }
        }
        return result;
    }
    private void BFS() {
        Queue<Integer> myQueue = new LinkedList<Integer>();
        for (int value : truthPerson) {
            myQueue.add(value);
            while (!myQueue.isEmpty()) {
                int cur = myQueue.peek();
                myQueue.poll();
                isKnowTruth[cur] = true;
                for (int j = 0; j < party.length; j++) {
                    if (checkContains(j, cur) && isPossibleParty[j]) {
                        isPossibleParty[j] = false;
                        for (int k = 0; k < party[j].length; k++) {
                            if (!isKnowTruth[party[j][k]]) {
                                isKnowTruth[party[j][k]] = true;
                                myQueue.add(party[j][k]);
                            }
                        }
                    }
                }
            }
        }
    }
    private void showResult() {
        int result = 0;
        for (boolean expression : isPossibleParty) {
            if (expression) {
                result ++;
            }
        }
        System.out.println(result);
    }
    public void solution() throws Exception {
        int personCount;
        int partyCount;
        int truthPersonCount;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 사람의 수 , 파티 수 입력
        personCount = Integer.parseInt(st.nextToken());
        partyCount = Integer.parseInt(st.nextToken());
        isKnowTruth = new boolean[personCount + 1];
        // 진실을 아는 사람 수와 사람 입력
        st = new StringTokenizer(br.readLine());
        truthPersonCount = Integer.parseInt(st.nextToken());
        truthPerson = new int[truthPersonCount];
        for (int i = 0; i < truthPersonCount; i ++) {
            int num = Integer.parseInt(st.nextToken());
            truthPerson[i] = num;
        }
        // party 배열 입력
        party = new int[partyCount][];
        isPossibleParty = new boolean[partyCount];
        Arrays.fill(isPossibleParty , true);
        for (int i = 0; i < partyCount; i ++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            party[i] = new int[length];
            for (int j = 0; j < length; j ++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        BFS();
        showResult();
        br.close();
        bw.flush();
        bw.close();
    }
    public static void main (String[] args) throws Exception {
            new no_1043().solution();
    }
}
