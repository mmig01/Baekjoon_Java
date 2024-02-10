import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    private int[][] arr;
    private void clearArr() {
        for (int[] ints : arr) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2); // 플로이드 워셜 알고리즘에서 더할 때의 오버플로우를 방지
        }
    }
    private void myFloyd() {
        for (int m = 1; m < arr.length; m ++) {
            for (int s = 1; s < arr.length; s ++) {
                for (int e = 1; e < arr.length; e ++) {
                    if (arr[s][e] > arr[s][m] + arr[m][e]) {
                        arr[s][e] = arr[s][m] + arr[m][e];
                    }
                }
            }
        }
    }
    private void showResult() {
        String result = "NO";
        for (int k = 1; k < arr.length; k ++) {
            if (arr[k][k] < 0) {
                result = "YES";
                break;
            }
        }
        System.out.println(result);
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testCase; i ++) {
            st = new StringTokenizer(br.readLine());
            int vertexCount = Integer.parseInt(st.nextToken());
            int edgeCount = Integer.parseInt(st.nextToken());
            int wormHoleCount = Integer.parseInt(st.nextToken());
            int vertex;
            int edge;
            int weight;
            arr = new int[vertexCount + 1][vertexCount + 1];
            clearArr();
            for (int j = 0; j < edgeCount; j ++) {
                st = new StringTokenizer(br.readLine());
                vertex = Integer.parseInt(st.nextToken());
                edge = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                if (arr[vertex][edge] > weight) {
                    // 도로 사이의 시간이 가장 짧은 값을 저장
                    arr[vertex][edge] = weight;
                    arr[edge][vertex] = weight;
                }
            }
            for (int j = 0; j < wormHoleCount; j ++) {
                st = new StringTokenizer(br.readLine());
                vertex = Integer.parseInt(st.nextToken());
                edge = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                arr[vertex][edge] = -weight;
            }
            myFloyd();
            showResult();
        }
        br.close();
    }
    public static void main (String[] args) throws Exception {
        new Main().solution();
    }
}
