import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(solution(N, stairs));
	}

	public static int solution(int N, int[] stairs){
		// dp[i][1] = i번째 계단을 밟았고, 연속 1칸째 (2칸 점프로 옴)
		// dp[i][2] = i번째 계단을 밟았고, 연속 2칸째 (1칸 올라옴)
		if (N == 1) return stairs[1];

		int[][] dp = new int[N + 1][3];
		dp[1][1] = stairs[1];
		dp[1][2] = 0;
		dp[2][1] = stairs[2];
		dp[2][2] = stairs[1] + stairs[2];

		for (int i = 3; i <= N; i++) {
			// 연속 1칸째: i-2 이하에서 2칸 점프로 왔다
			dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
			// 연속 2칸째: i-1에서 1칸 올라왔는데, i-1은 연속 1칸째여야 함
			dp[i][2] = dp[i-1][1] + stairs[i];
		}
		return Math.max(dp[N][1], dp[N][2]);
	}
}