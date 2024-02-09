import java.io.*;
import java.util.*;

class Pair{
    private final int vertex;
    private final int weight;

    public Pair(int vertex , int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    public int getVertex() {
        return vertex;
    }
    public int getWeight() {
        return weight;
    }
}
class Node{
    private final LinkedList<Pair> vertexAndWeight;
    public Node() {
        vertexAndWeight = new LinkedList<Pair>();
    }
    public void add(Pair p) {
        vertexAndWeight.add(p);
    }
    public Iterator<Pair> iterator() {
        return vertexAndWeight.iterator();
    }
}
public class Main{
    private Node[] vertexArr;
    private int[] distance;
    private boolean[] isVisit;
    private int maxLengthVertex;
    private int maxDistance;
    private int elementCount;
    private void makeArr() {
        vertexArr = new Node[elementCount + 1];
        isVisit = new boolean[elementCount + 1];
        distance = new int[elementCount + 1];
        for (int i = 1; i < vertexArr.length; i ++) {
            vertexArr[i] = new Node(); // 노드 객체를 생성해 배열에 저장
        }
    }
    private void DFS(int startVertex) {
        Stack<Integer> myStack = new Stack<Integer>();
        myStack.push(startVertex);
        isVisit[startVertex] = true;
        while (!myStack.isEmpty()) {
            int cur = myStack.peek();
            myStack.pop();
            Iterator<Pair> it = vertexArr[cur].iterator();
            while (it.hasNext()) {
                Pair pair = it.next();
                int nextVertex = pair.getVertex();
                if (!isVisit[nextVertex]) {
                    distance[nextVertex] = distance[cur] + pair.getWeight();
                    isVisit[nextVertex] = true;
                    myStack.push(pair.getVertex());
                    if (distance[nextVertex] > maxDistance) {
                        maxDistance = distance[nextVertex];
                        maxLengthVertex = nextVertex;
                    }
                }
            }
        }
    }
    private void getResult() throws IOException {
        maxDistance = 0;
        maxLengthVertex = 1;
        for (int i = 0; i < 2; i ++) {
            Arrays.fill(isVisit , false);
            Arrays.fill(distance , 0);
            DFS(maxLengthVertex); // DFS 를 통해 최대 길이와 최대 길이를 갖는 노드를 새로 갱신
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(maxDistance));
        bw.flush();
        bw.close();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        elementCount = Integer.parseInt(st.nextToken());
        makeArr();
        for (int i = 0; i < elementCount; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            while (true) {
                int edge = Integer.parseInt(st.nextToken());
                if (edge == -1) {
                    break ;
                } else {
                    int weight = Integer.parseInt(st.nextToken());
                    vertexArr[vertex].add(new Pair(edge , weight));
                }
            }
        }
        getResult();
        br.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
