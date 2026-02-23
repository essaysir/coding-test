import java.util.*;
import java.io.*;

public class Main{

	public static int[] dy = new int[]{-2,-2,0,0,2,2};
	public static int[] dx = new int[]{-1,1,-2,2,-1,1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 크기가 N 인 체스판
		int N = Integer.parseInt(br.readLine()); // 5 이상 200 이하
		// r1.c1 에서 r2,c2 로 이동할 수 있는 최소 이동 횟수

		String secondInput = br.readLine();
		StringTokenizer st = new StringTokenizer(secondInput);

		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{r1,c1,0});
		int[][] visited = new int[N][N];
		visited[r1][c1] = 1;


		boolean flag = false;
		// 최소 횟수를 어떻게 알 지 ?
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			int curR = cur[0];
			int curC = cur[1];
			int curLevel = cur[2];
			// System.out.println("#### ARRAY : " + Arrays.toString(cur));
			if ( curR == r2 && curC == c2 ){
				System.out.println(curLevel);
				flag = true;
				return;
			}

			for ( int i = 0; i < dy.length; i++){
				int ny = curR + dy[i];
				int nx = curC + dx[i];

				if ( ny < 0 || nx < 0 || nx >= N ||  ny >= N || visited[ny][nx] == 1) continue;

				visited[ny][nx] = 1;
				queue.add(new int[]{ny,nx,curLevel+1});
			}
		}
		if ( !flag ){
			System.out.println("-1");
		}
	}
}