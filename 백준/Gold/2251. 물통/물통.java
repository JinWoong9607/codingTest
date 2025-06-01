import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class node {
        int a;
        int b;
        int c;
        public void setNode(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public void setValue(int index, int value) {
            switch(index) {
                case 0: a = value; break;
                case 1: b = value; break;
                case 2: c = value; break;
            }
        }


        public int getValue(int index) {
            return switch (index) {
                case 0 -> a;
                case 1 -> b;
                case 2 -> c;
                default -> 0;
            };
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            node other = (node) obj;
            return a == other.a && b == other.b && c == other.c;
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        public List<Integer> toList() {
            return Arrays.asList(a, b, c);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] capacity = new int[] {a, b, c};
        node start = new node();
        start.setNode(0, 0, c);
        Queue<node> q = new LinkedList<>();
        Set<node> set = new HashSet<>();
        Set<Integer> ans = new TreeSet<>();
        q.add(start);
        set.add(start);
        while(!q.isEmpty()) {
            node current = q.remove();
            List<Integer> list= current.toList();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (i != j) {
                        int from = list.get(i);
                        int to = list.get(j);
                        int pour = Math.min(from, capacity[j] - to);
                        if (pour > 0) {
                            node newNode = new node();
                            newNode.a = current.a;
                            newNode.b = current.b;
                            newNode.c = current.c;

                            newNode.setValue(i, newNode.getValue(i) - pour);
                            newNode.setValue(j, newNode.getValue(j) + pour);

                            if (!set.contains(newNode)) {
                                q.add(newNode);
                                set.add(newNode);
                            }
                        }
                    }
                }
            }
        }
        for (node result : set) {
            if (result.getValue(0) == 0) {
                ans.add(result.getValue(2));
            }
        }

        for (int result : ans) {
            System.out.print(result + " ");
        }
    }
}
