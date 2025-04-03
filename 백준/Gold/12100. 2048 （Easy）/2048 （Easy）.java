import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.HashMap;

public class Main {
    static ArrayList<Integer> value;
    static int maximum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        value = new ArrayList<>();
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                value.add(n*i+j, Integer.parseInt(st.nextToken()));
            }
        }

        merge(n, value);

        bw.write(String.valueOf(maximum));
        bw.close();

    }

    public static void merge(int n, ArrayList<Integer> value) {
        Queue<ArrayList<Integer>> mergetrial = new LinkedList<>();
        mergetrial.add(value);
        ArrayList<Integer> newmerge;
        int trial = 1;
        while (trial <= 5) {
            Queue<ArrayList<Integer>> newQueue = new LinkedList<>();
            while (!mergetrial.isEmpty()) {
                newmerge = new ArrayList<>(mergetrial.peek());
                for (int i = 0; i < n; i++) {
                    int zerocount = 0;
                    for (int j = 0; j < n; j++) {
                        if (newmerge.get(i * n + j) != 0) {
                            newmerge.set(i * n + zerocount, newmerge.get(i * n + j));
                            zerocount++;
                        }
                    }
                    while (zerocount < n) {
                        newmerge.set(i * n + zerocount, 0);
                        zerocount++;
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        if (newmerge.get(i * n + j).equals(newmerge.get(i * n + j + 1))) {
                            newmerge.set(i * n + j, newmerge.get(i * n + j) * 2);
                            newmerge.set(i * n + j + 1, 0);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int zerocount = 0;
                    for (int j = 0; j < n; j++) {
                        if (newmerge.get(i * n + j) != 0) {
                            newmerge.set(i * n + zerocount, newmerge.get(i * n + j));
                            zerocount++;
                        }
                    }
                    while (zerocount < n) {
                        newmerge.set(i * n + zerocount, 0);
                        zerocount++;
                    }
                }
                newQueue.add(new ArrayList<>(newmerge));
                if (trial == 5) {
                    while (!newmerge.isEmpty()) {
                        int first = newmerge.remove(0);
                        maximum = Math.max(maximum, first);
                    }
                }
                newmerge = new ArrayList<>(mergetrial.peek());

                for (int i = 0; i < n; i++) {
                    int zerocount = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (newmerge.get(i * n + j) != 0) {
                            newmerge.set(i * n + zerocount, newmerge.get(i * n + j));
                            zerocount--;
                        }
                    }
                    while (zerocount >= 0) {
                        newmerge.set(i * n + zerocount, 0);
                        zerocount--;
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j > 0; j--) {
                        if (newmerge.get(i * n + j).equals(newmerge.get(i * n + j - 1))) {
                            newmerge.set(i * n + j, newmerge.get(i * n + j) * 2);
                            newmerge.set(i * n + j - 1, 0);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int zerocount = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (newmerge.get(i * n + j) != 0) {
                            newmerge.set(i * n + zerocount, newmerge.get(i * n + j));
                            zerocount--;
                        }
                    }
                    while (zerocount >= 0) {
                        newmerge.set(i * n + zerocount, 0);
                        zerocount--;
                    }
                }
                newQueue.add(new ArrayList<>(newmerge));
                if (trial == 5) {
                    while (!newmerge.isEmpty()) {
                        int first = newmerge.remove(0);
                        maximum = Math.max(maximum, first);
                    }
                }

                newmerge = new ArrayList<>(mergetrial.peek());

                for (int i = 0; i < n; i++) {
                    int zerocount = 0;
                    for (int j = 0; j < n; j++) {
                        if (newmerge.get(j * n + i) != 0) {
                            newmerge.set(zerocount * n + i, newmerge.get(j * n + i));
                            zerocount++;
                        }
                    }
                    while (zerocount < n) {
                        newmerge.set(zerocount * n + i, 0);
                        zerocount++;
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        if (newmerge.get(j * n + i).equals(newmerge.get((j + 1) * n + i))) {
                            newmerge.set(j * n + i, newmerge.get(j * n + i) * 2);
                            newmerge.set((j + 1) * n + i, 0);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int zerocount = 0;
                    for (int j = 0; j < n; j++) {
                        if (newmerge.get(j * n + i) != 0) {
                            newmerge.set(zerocount * n + i, newmerge.get(j * n + i));
                            zerocount++;
                        }
                    }
                    while (zerocount < n) {
                        newmerge.set(zerocount * n + i, 0);
                        zerocount++;
                    }
                }

                newQueue.add(new ArrayList<>(newmerge));
                if (trial == 5) {
                    while (!newmerge.isEmpty()) {
                        int first = newmerge.remove(0);
                        maximum = Math.max(maximum, first);
                    }
                }

                newmerge = new ArrayList<>(mergetrial.peek());

                for (int i = 0; i < n; i++) {
                    int zerocount = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (newmerge.get(j * n + i) != 0) {
                            newmerge.set(n * zerocount + i, newmerge.get(j * n + i));
                            zerocount--;
                        }
                    }
                    while (zerocount >= 0) {
                        newmerge.set(n * zerocount + i, 0);
                        zerocount--;
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j > 0; j--) {
                        if (newmerge.get(j * n + i).equals(newmerge.get((j - 1) * n + i))) {
                            newmerge.set(j * n + i, newmerge.get(j * n + i) * 2);
                            newmerge.set((j - 1) * n + i, 0);
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    int zerocount = n - 1;
                    for (int j = n - 1; j >= 0; j--) {
                        if (newmerge.get(j * n + i) != 0) {
                            newmerge.set(n * zerocount + i, newmerge.get(j * n + i));
                            zerocount--;
                        }
                    }
                    while (zerocount >= 0) {
                        newmerge.set(n * zerocount + i, 0);
                        zerocount--;
                    }
                }
                newQueue.add(new ArrayList<>(newmerge));
                if (trial == 5) {
                    while (!newmerge.isEmpty()) {
                        int first = newmerge.remove(0);
                        maximum = Math.max(maximum, first);
                    }
                }

                mergetrial.remove();
            }

            if (trial == 5) {
                break;
            }

            mergetrial = newQueue;
            trial++;
        }
    }
}
