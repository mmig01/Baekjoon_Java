import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    private int vertex;
    private int minWeight;
    public Pair(int vertex , int minWeight) {
        this.vertex = vertex;
        this.minWeight = minWeight;
    }
    public int getVertex() {
        return vertex;
    }
    public int getMinWeight() {
        return minWeight;
    }

    @Override
    public int compareTo(Pair o) {
        int result ;
        if (this.minWeight < o.minWeight) {
            result = -1;
        } else if (this.minWeight > o.minWeight){
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }
}
public class Main {
    private final int INT_MAX = 2140000000;
    private int[][] weightArr;
    int[] distance;
    private int[] goMinDistance;
    private int[] backMinDistance;
    private int peopleNum;
    int edgeNum;
    int targetVertex;
    private int myDijkstra(int startVertex , int endVertex) {
        int result;
        Arrays.fill(distance , INT_MAX);
        distance[startVertex] = 0;
        Pair pair = new Pair(startVertex , 0);
        PriorityQueue<Pair> myMinPQ = new PriorityQueue<Pair>();
        myMinPQ.add(pair);
        while (!myMinPQ.isEmpty()) {
            int vertex = myMinPQ.peek().getVertex();
            myMinPQ.poll();
            for (int i = 1; i < weightArr[vertex].length; i ++) {
                int weight = weightArr[vertex][i];
                if ((weight != INT_MAX) && (distance[vertex] + weight < distance[i])) {
                    distance[i] = distance[vertex] + weight;
                    myMinPQ.add(new Pair(i , distance[i]));
                }
            }
        }
        result = distance[endVertex];
        return result;
    }
    private int getMaxWeight() {
        int result = 0;
        int length = goMinDistance.length; // 두 배열은 동일한 길이
        for (int i = 1; i < length; i ++) {
            goMinDistance[i] = myDijkstra(i , targetVertex);
        }
        backMinDistance[i] = myDijkstra(targetVertex , i);
        for (int i = 1; i < goMinDistance.length; i ++) {
            int goBackSum = goMinDistance[i] + backMinDistance[i];
            result = Math.max(result , goBackSum);
        }
        return result;
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        peopleNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        targetVertex = Integer.parseInt(st.nextToken());
        weightArr = new int[peopleNum + 1][peopleNum + 1];
        for (int[] array : weightArr) {
            Arrays.fill(array, INT_MAX);
        }
        distance = new int[peopleNum + 1];
        goMinDistance = new int[peopleNum + 1];
        backMinDistance = new int[peopleNum + 1];
        for (int i = 0; i < edgeNum; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            weightArr[vertex][edge] = weight;
        }
        System.out.println(getMaxWeight());
        br.close();
    }
    public static void main (String[] args) throws Exception {
            new Main().solution();
    }
}
