import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {
    private final int vertex;
    private final long weight;

    public Pair(int nextVertex , long weight) {
        this.vertex = nextVertex;
        this.weight = weight;
    }
    public int getVertex() {
        return vertex;
    }
    public long getWeight() {
        return weight;
    }
    @Override
    public int compareTo(Pair o) {
        return Long.compare(this.weight , o.weight);
    }
}
class Node{
    private final LinkedList<Pair> nextVertexAndWeight;
    public Node() {
        nextVertexAndWeight = new LinkedList<Pair>();
    }
    public void add(Pair p) {
        nextVertexAndWeight.add(p);
    }
    public Iterator<Pair> iterator() {
        return nextVertexAndWeight.iterator();
    }
}
public class Main{
    private Node[] inputNode;
    private long[] distance;
    private void makeArr(int length) {
        inputNode = new Node[length];
        distance = new long[length];
        for (int i = 0; i < length; i ++) {
            inputNode[i] = new Node();
        }
        Arrays.fill(distance , Long.MAX_VALUE);
    }
    private void myDijkstra(int startVertex) {
        distance[startVertex] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        pq.add(new Pair(startVertex , 0));
        while (!pq.isEmpty()) {
            int curVertex = pq.peek().getVertex();
            long curWeight = pq.peek().getWeight();
            pq.poll();
            if (curWeight <= distance[curVertex]) {
                Iterator<Pair> it = inputNode[curVertex].iterator();
                while (it.hasNext()) {
                    Pair p = it.next();
                    int nextVertex = p.getVertex();
                    long nextWeight = p.getWeight();
                    if (distance[nextVertex] > distance[curVertex] + nextWeight) {
                        distance[nextVertex] = distance[curVertex] + nextWeight;
                        pq.add(new Pair(nextVertex , distance[nextVertex]));
                    }
                }
            }
        }
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertexCount = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int edgeCount = Integer.parseInt(st.nextToken());
        makeArr(vertexCount + 1);

        for (int i = 0; i < edgeCount; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            inputNode[vertex].add(new Pair(edge , weight));
        }

        st = new StringTokenizer(br.readLine());
        int startVertex = Integer.parseInt(st.nextToken());
        int targetVertex = Integer.parseInt(st.nextToken());

        myDijkstra(startVertex);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Long.toString(distance[targetVertex]));

        br.close();
        bw.flush();
        bw.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
