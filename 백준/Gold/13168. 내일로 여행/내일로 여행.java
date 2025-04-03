import java.io.*;
import java.util.*;

class Edge {
    String toNode;
    double weight;

    Edge(String toNode, double weight) {
        this.toNode = toNode;
        this.weight = weight;
    }
}

public class Main {

    static Map<String, List<Edge>> graphNailo = new HashMap<>();
    static Map<String, List<Edge>> graphNotNailo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 필요 시 사용

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        double R = Double.parseDouble(st.nextToken());

        // 도시 이름 입력
        st = new StringTokenizer(br.readLine());
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String cityName = st.nextToken();
            cityList.add(cityName);
        }

        // 도시 이름을 인덱스로 매핑
        Map<String, Integer> cityToIndex = new HashMap<>();
        Map<Integer, String> indexToCity = new HashMap<>();
        int index = 0;
        for (String cityName : cityList) {
            if (!cityToIndex.containsKey(cityName)) {
                cityToIndex.put(cityName, index);
                indexToCity.put(index, cityName);
                index++;
            }
        }
        N = index; // 실제 도시의 개수

        // 여행할 도시 입력
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> travel = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String cityName = st.nextToken();
            int cityIndex = cityToIndex.get(cityName);
            travel.add(cityIndex);
        }

        // 교통수단 입력
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String transport = st.nextToken();
            String fromCity = st.nextToken();
            String toCity = st.nextToken();
            double cost = Double.parseDouble(st.nextToken());

            // 양방향 간선 추가
            addEdge(graphNailo, fromCity, toCity, transport, cost, true);
            addEdge(graphNotNailo, fromCity, toCity, transport, cost, false);
        }

        // 가중치 행렬을 ArrayList로 초기화
        List<List<Double>> distNailo = new ArrayList<>();
        List<List<Double>> distNotNailo = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Double> rowNailo = new ArrayList<>(Collections.nCopies(N, Double.POSITIVE_INFINITY));
            List<Double> rowNotNailo = new ArrayList<>(Collections.nCopies(N, Double.POSITIVE_INFINITY));
            rowNailo.set(i, 0.0); // 자기 자신으로의 거리는 0
            rowNotNailo.set(i, 0.0);
            distNailo.add(rowNailo);
            distNotNailo.add(rowNotNailo);
        }

        // 그래프 정보를 행렬에 반영
        for (String fromCity : graphNailo.keySet()) {
            int fromIndex = cityToIndex.get(fromCity);
            for (Edge edge : graphNailo.get(fromCity)) {
                int toIndex = cityToIndex.get(edge.toNode);
                if (distNailo.get(fromIndex).get(toIndex) > edge.weight) {
                    distNailo.get(fromIndex).set(toIndex, edge.weight);
                }
            }
        }

        for (String fromCity : graphNotNailo.keySet()) {
            int fromIndex = cityToIndex.get(fromCity);
            for (Edge edge : graphNotNailo.get(fromCity)) {
                int toIndex = cityToIndex.get(edge.toNode);
                if (distNotNailo.get(fromIndex).get(toIndex) > edge.weight) {
                    distNotNailo.get(fromIndex).set(toIndex, edge.weight);
                }
            }
        }

        // 플로이드-워셜 알고리즘 적용
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distNailo.get(i).get(j) > distNailo.get(i).get(k) + distNailo.get(k).get(j)) {
                        distNailo.get(i).set(j, distNailo.get(i).get(k) + distNailo.get(k).get(j));
                    }
                    if (distNotNailo.get(i).get(j) > distNotNailo.get(i).get(k) + distNotNailo.get(k).get(j)) {
                        distNotNailo.get(i).set(j, distNotNailo.get(i).get(k) + distNotNailo.get(k).get(j));
                    }
                }
            }
        }

        // 총 비용 계산
        double totalCostNailo = 0;
        double totalCostNotNailo = 0;
        for (int i = 0; i < travel.size() - 1; i++) {
            int from = travel.get(i);
            int to = travel.get(i + 1);

            totalCostNailo += distNailo.get(from).get(to);
            totalCostNotNailo += distNotNailo.get(from).get(to);
        }

        totalCostNailo += R; // 내일로 티켓 가격 추가

        // 결과 출력
        if (totalCostNailo < totalCostNotNailo) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static void addEdge(Map<String, List<Edge>> graph, String fromCity, String toCity, String transport, double cost, boolean isNailo) {
        double adjustedCost = cost;
        if (isNailo) { // 내일로 티켓을 사용한 경우
            if (transport.equals("Mugunghwa") || transport.equals("ITX-Saemaeul") || transport.equals("ITX-Cheongchun")) {
                adjustedCost = 0;
            } else if (transport.equals("S-Train") || transport.equals("V-Train")) {
                adjustedCost = cost / 2.0;
            }
            // 나머지 교통수단은 할인이 없음
        }

        // 양방향 간선 추가
        graph.computeIfAbsent(fromCity, k -> new ArrayList<>()).add(new Edge(toCity, adjustedCost));
        graph.computeIfAbsent(toCity, k -> new ArrayList<>()).add(new Edge(fromCity, adjustedCost));
    }
}
