import java.util.*;

public class Main{

	public static void main(String[] args){
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		solution(tickets);
	}

	static Map<String, List<Integer>> map = new HashMap<>();
	static boolean[] visited;
	static String[] answer;
	static boolean found = false;

	private static String[] solution(String[][] tickets) {
		// 알파벳 배열로 정렬
		Arrays.sort(tickets, (a, b) -> a[0].compareTo(b[0]));

		visited = new boolean[tickets.length];

		for ( int i = 0; i < tickets.length; i++){
			map.computeIfAbsent(tickets[i][0] , k -> new ArrayList<>()).add(i);
		}
		List<String> path = new ArrayList<>();
		path.add("ICN");

		DFS("ICN",tickets,path, 0);

		System.out.println(Arrays.toString(answer));
		return answer;
	}

	public static void DFS(String city, String[][] tickets, List<String> path, int depth){
		if ( found ) return;

		if  ( depth == tickets.length){
			answer = path.toArray(new String[0]);
			found = true;
			return ;
		}

		if ( !map.containsKey(city)) return;

		for ( int ticketIdx : map.get(city)){
			if ( !visited[ticketIdx]){
				visited[ticketIdx] = true ;

				path.add(tickets[ticketIdx][1]);
				DFS(tickets[ticketIdx][1], tickets , path, depth + 1);

				if ( found ) return;

				visited[ticketIdx] = false;
				path.remove(path.size() - 1);
			}
		}
	}
}