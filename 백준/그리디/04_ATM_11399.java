import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] P = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for ( int i = 0; i < N; i++ ) {
			int cur = Integer.parseInt(st.nextToken());
			P[i] = cur;
		}

		System.out.println(solution(N,P));
	}

	public static int solution(int N, int[] P) {
		// N 은 사람의 수, P 이 각 사람이 돈을 인출하는 데 걸리는 시간.
		// 1 < N < 1000 ,

		// 필요한 최솟값
		Arrays.sort(P);
		int sum = 0;
		int cur = 0;

		for ( int i = 0; i < N; i++) {
			sum += cur + P[i];
			cur += P[i];
		}

		return sum;
	}
}