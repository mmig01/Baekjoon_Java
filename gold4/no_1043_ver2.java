import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// union find 알고리즘으로 구현
// 거짓말
class UnionFind{
    private final int[] parent;
    private final int[] rank;
    public UnionFind(int length) {
        parent = new int[length];
        rank = new int[length];
        clearParent();
        clearRank();
    }
    private void clearParent() {
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
        }
    }
    private void clearRank() {
        Arrays.fill(rank , 1);
    }
    public int find(int u) {
        int result;
        if (u == parent[u]) {
            result = u;
        } else {
            parent[u] = find(parent[u]);
            result = parent[u];
        }
        return result;
    }
    public void union(int u , int v) {
        int longLength = find(u); // u 의 부모 찾기
        int shortLength = find(v); // v 의 부모 찾기
        if (longLength != shortLength) {
            if (rank[longLength] < rank[shortLength]) {
                int temp = longLength;
                longLength = shortLength;
                shortLength = temp;
            } else if (rank[longLength] == rank[shortLength]) {
                rank[longLength] ++;
            }
            parent[shortLength] = longLength;
        }
    }
}
public class no_1043_ver2 {
    public void solution() throws Exception {
        // 사람 수, 파티 수를 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int personNum = Integer.parseInt(st.nextToken());
        int partyNum = Integer.parseInt(st.nextToken());
        UnionFind unionFind = new UnionFind(personNum + 1);
        // 진실을 아는 사람 수와 그 사람들을 union
        ArrayList<Integer> truthPerson = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());
        int truthPersonLength = Integer.parseInt(st.nextToken());
        if (truthPersonLength == 0) {
            truthPerson.add(0);
        } else {
            int head = Integer.parseInt(st.nextToken());
            truthPerson.add(head);
            while (st.hasMoreTokens()) {
                int node;
                node = Integer.parseInt(st.nextToken());
                truthPerson.add(node);
                unionFind.union(head , node);
            }
        }

        // party 에 참여하는 사람들을 union
        int[][] people = new int[partyNum][];
        for (int i = 0; i < partyNum; i ++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            people[i] = new int[length];
            int head = Integer.parseInt(st.nextToken());
            people[i][0] = head;
            for (int j = 1; j < length; j ++) {
                int node;
                node = Integer.parseInt(st.nextToken());
                people[i][j] = node;
                unionFind.union(head , node);
            }
        }
        // 정답 찾기
        int result = 0;
        for (int i = 0; i < partyNum; i ++) {
            int parent = unionFind.find(people[i][0]); // 최적화를 거치는 parent 설정
            int truthPersonParent = unionFind.find(truthPerson.get(0));
            if (parent != truthPersonParent) {
                result ++;
            }
        }
        System.out.println(result);
        br.close();
    }
    public static void no_1043_ver2 (String[] args) throws Exception {
        new no_1043_ver2().solution();
    }
}
