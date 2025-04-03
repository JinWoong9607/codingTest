import java.util.*;
import java.io.BufferedReader;
import java.io.*;

class Jewel {
    int weigh;
    int price;

    public Jewel(int weigh, int price) {
        this.weigh = weigh;
        this.price = price;
    }
}
public class Main implements Comparator<Jewel> {
    @Override
    public int compare(Jewel o1, Jewel o2) {
        if (o1.price == o2.price) {
            return o1.weigh - o2.weigh;
        } else {
            return o2.price - o1.price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int weigh;
        int price;
        PriorityQueue<Jewel> Jewel = new PriorityQueue<>(1, new Main());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weigh = Integer.parseInt(st.nextToken());
            price = Integer.parseInt(st.nextToken());
            Jewel.offer(new Jewel(weigh, price));
        }
        TreeMap<Integer, Integer> bag = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            bag.put(num, bag.getOrDefault(num, 0) + 1);
        }
        long ans = 0;

        for (int i = 0; i < n; i++) {
            Jewel jewel;
            jewel = Jewel.remove();
            Integer suitableBag = bag.ceilingKey(jewel.weigh);

            if (suitableBag != null) {
                ans += jewel.price;
                if (bag.get(suitableBag) > 1) {
                    bag.put(suitableBag, bag.get(suitableBag) - 1);
                } else {
                    bag.remove(suitableBag);
                }
            }

        }
        System.out.print(ans);
    }
}