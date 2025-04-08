import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> route; 
    
    public String[] solution(String[][] tickets) {
        ArrayList<String> airport = new ArrayList<>();
        for (int i = 0; i < tickets.length; i++) {
            if (!airport.contains(tickets[i][0])) {
                airport.add(tickets[i][0]);
            }
            if (!airport.contains(tickets[i][1])) {
                airport.add(tickets[i][1]);
            }
        }
        Collections.sort(airport);
        
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < airport.size(); i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int i = 0; i < tickets.length; i++) {
            int from = airport.indexOf(tickets[i][0]);
            int to = airport.indexOf(tickets[i][1]);
            graph.get(from).add(to);
        }
        
        for (int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }

        int start = start = airport.indexOf("ICN");
        
        route = new ArrayList<>();
        dfs(start);
        
        Collections.reverse(route);
        
        String[] answer = new String[route.size()];
        for (int i = 0; i < route.size(); i++) {
            answer[i] = airport.get(route.get(i));
        }
        return answer;
    }
    
    static public void dfs(int cur) {
        while (!graph.get(cur).isEmpty()) {
            int next = graph.get(cur).remove(0);
            dfs(next);
        }
        route.add(cur);
    }
}
