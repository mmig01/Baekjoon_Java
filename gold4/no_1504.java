
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    private final int vertex;
    private final int minWeight;
    public Pair(int vertex , int minWeight) {
        this.vertex = vertex;
        this.minWeight = minWeight;
    }
    public int getVertex() {
        return vertex;
    }
    @Override
    public int compareTo(Pair o) {
        int result;
        result = Integer.compare(this.minWeight, o.minWeight);
        return result;
    }
}
public class no_1504{
    private static final int INF = 2140000000;
    private int[][] weightArr;
    private int[] defalutDistance;
    private int[] firstElementDistance;
    private int[] secondElementDistance;
    private int vertexNum;
    private int edgeNum;
    private int firstElement;
    private int secondElement;

    private void myDijkstra(int startVertex , int[] distance) {
        Arrays.fill(distance , INF);
        distance[startVertex] = 0;
        Pair pair = new Pair(startVertex , 0);
        PriorityQueue<Pair> myMinPQ = new PriorityQueue<Pair>();
        myMinPQ.add(pair);
        while (!myMinPQ.isEmpty()) {
            int vertex = myMinPQ.peek().getVertex();
            myMinPQ.poll();
            for (int i = 1; i < weightArr[vertex].length; i ++) {
                int weight = weightArr[vertex][i];
                if ((weight != INF) && (distance[vertex] + weight < distance[i])) {
                    distance[i] = distance[vertex] + weight;
                    myMinPQ.add(new Pair(i , distance[i]));
                }
            }
        }
    }
    private void makeArr() {
        weightArr = new int[vertexNum + 1][vertexNum + 1];
        for (int i = 1; i < weightArr.length; i ++) {
            Arrays.fill(weightArr[i] , INF);
        }
        defalutDistance = new int[vertexNum + 1];
        firstElementDistance = new int[vertexNum + 1];
        secondElementDistance = new int[vertexNum + 1];
    }
    private void searchShortRoot() {
        myDijkstra(1 , defalutDistance);
        myDijkstra(firstElement , firstElementDistance);
        myDijkstra(secondElement , secondElementDistance);
        long firstResult = (long)defalutDistance[firstElement] + firstElementDistance[secondElement] + secondElementDistance[secondElementDistance.length - 1];
        long secondResult = (long)defalutDistance[secondElement] + secondElementDistance[firstElement] + firstElementDistance[firstElementDistance.length - 1];
        long result = Math.min(firstResult , secondResult);
        if (result >= INF) {
            result = -1;
        }
        System.out.println(result);
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertexNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        makeArr(); // 인접행렬 , 다익스트라를 사용할 배열 3개 생성
        for (int i = 0; i < edgeNum; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            weightArr[vertex][edge] = weight;
            weightArr[edge][vertex] = weight;
        }

        st = new StringTokenizer(br.readLine());
        firstElement = Integer.parseInt(st.nextToken());
        secondElement = Integer.parseInt(st.nextToken());

        searchShortRoot();
        br.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
