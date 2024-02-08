
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    private final int vertex; // 정점
    private final int weight; // 가중치
    public Pair(int vertex , int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    public int getVertex() { return vertex; }
    public int getWeight() { return weight; }
    @Override
    public int compareTo(Pair o) {
        int result;
        result = Integer.compare(this.weight, o.weight);
        return result;
    }
}
class Node{
    // 각 정점에서 시작하는 간선들을 linkedlist 형태로 저장
    private final LinkedList<Pair> edgeAndWeight;
    public Node() {
        edgeAndWeight = new LinkedList<Pair>();
    }
    public void add(Pair pair) {
        edgeAndWeight.add(pair);
    }
    public Iterator<Pair> iterator() {
        return edgeAndWeight.iterator();
    }
}
public class Main{
    private static final int INF = 2140000000;
    private Node[] vertexArr;
    private int[] distance;
    private int vertexNum;
    private int edgeNum;
    private int startVertex;

    private void myDijkstra(int startVertex , int[] distance) {
        Arrays.fill(distance , INF);
        distance[startVertex] = 0;
        Pair pair = new Pair(startVertex , 0);
        PriorityQueue<Pair> myMinPQ = new PriorityQueue<Pair>();
        myMinPQ.add(pair);
        while (!myMinPQ.isEmpty()) {
            int curVertex = myMinPQ.peek().getVertex();
            myMinPQ.poll();
            Iterator<Pair> it = vertexArr[curVertex].iterator();
            while (it.hasNext()) {
                // 현재 정점에 저장되어있는 linked list 형태의 pair 객체(이동할 노드 , 가중치 정보) 를 순회
                Pair nextPair = it.next();
                int nextWeight = nextPair.getWeight();
                int nextVertex = nextPair.getVertex();
                if (distance[curVertex] + nextWeight < distance[nextVertex]) {
                    distance[nextVertex] = distance[curVertex] + nextWeight;
                    myMinPQ.add(new Pair(nextVertex , distance[nextVertex]));
                }
            }
        }
    }
    private void makeArr() {
        vertexArr = new Node[vertexNum + 1]; // Node 정보를 저장할 배열
        distance = new int[vertexNum + 1]; // 다익스트라에 사용할 distance 배열
        for (int i = 1; i < vertexArr.length; i ++) {
            Node node = new Node();
            vertexArr[i] = node;
        }
    }
    private void showDistance() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < distance.length; i ++) {
            if (distance[i] == INF) {
                bw.write("INF");
                bw.newLine();
            }  else {
                bw.write(Integer.toString(distance[i]));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertexNum = Integer.parseInt(st.nextToken());
        edgeNum = Integer.parseInt(st.nextToken());
        makeArr();
        st = new StringTokenizer(br.readLine());
        startVertex = Integer.parseInt(st.nextToken());

        for (int i = 0; i < edgeNum; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Pair pair = new Pair(edge , weight);
            vertexArr[vertex].add(pair); // 현재 정점에서 갈 수 있는 모든 간선들을 저장 (Linked list)
        }
        myDijkstra(startVertex , distance); // 다익스트라
        showDistance(); // 결과 출력
        br.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
