import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    static class node { //간선을 정의하고 선언
        int S;
        int E;
        int T;

        public node (int S, int E, int T) {
            this.S = S;
            this.E = E;
            this.T = T;
        }
    }
    static class worm {
        int S;
        int E;
        int T;
        public worm (int S, int E, int T) {
            this.S = S;
            this.E = E;
            this.T = T;
        }
    }
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // br을 통해서 읽어오고
        int trial = Integer.parseInt(br.readLine()); // 첫 문장은 케이스의 갯수이기 때문에 바로 들여옴
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i=0; i<trial; i++) { // 케이스의 갯수에 따라서 포문을 돌림
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()); // 지점의 수 n
            int m = Integer.parseInt(st.nextToken()); // 도로의 수 m
            int w = Integer.parseInt(st.nextToken()); // 웜홀의 갯수 w
            ArrayList<node> edges = new ArrayList<>(); // 앞서 선언한 node를 원소로 하는 arraylist 생성
            long[] dist = new long[n+1]; // 각각의 지점 선언

            for (int j=0; j<m; j++) { //도로 정보 arraylist에 입력 다만 도로는 양방향이기 때문에 반대 방향도 함께 추가
                st = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new node(S,E,T));
                edges.add(new node(E,S,T));
            }
            for (int j=0; j<w; j++) { //웜홀 정보 추가, 웜홀은 단방향이므로 한 방향만 추가
                st = new StringTokenizer(br.readLine(), " ");
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new node(S,E,-T));
            }
            boolean cycle = false; // 음수 사이클을 체크하기 위한 boolean 설정
                Arrays.fill(dist, Integer.MAX_VALUE); // dist를 Integer.MAX_VALUE로 초기화
                for(int k=1; k<=n; k++) { // n번 반복
                    for (node edge: edges) {
                        if (dist[edge.E] > dist[edge.S]+edge.T) {
                            // 간선이 변화가 있을 경우(dist[edge.S]+edge.T가 edge.E보다 최솟값일 때
                            dist[edge.E] = dist[edge.S] + edge.T;// 최솟값을 갱신
                            if (k==n) { // 만일 n번째에도 변화가 있다면(if문 내에 있으니)
                                cycle = true;// 음수 사이클이 존재함 이 이후의 연산은 필요 없으니 break로 탈출
                                break;
                            }
                        }
                        if(cycle) {
                            break;
                        }
                    }
                    if(cycle) {
                        break;
                    }
                }


            if (cycle) {
                bw.write("YES");// 음수 사이클이 존재한다면 Yes 출력
            } else {
                bw.write("NO");// 아닐경우 No 출력
            }

            bw.newLine();
            bw.flush();


        }
        bw.close();
    }
}
