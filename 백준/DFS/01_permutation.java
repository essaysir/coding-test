import java.util.*;

public class Permutation {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5};
		int r = 2;

		permutation(numbers, r);
	}

	public static void permutation(int[] arr, int r) {
		boolean[] visited = new boolean[arr.length];
		int[] output = new int[r];

		System.out.println(arr.length + "개 중 " + r + "개를 뽑는 순열:");
		permutationHelper(arr, visited, output, 0, r);
	}

	private static void permutationHelper(int[] arr, boolean[] visited, int[] output, int depth, int r) {
		// 기저 조건 , 종료 조건
		if (depth == r) {
			printArray(output);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutationHelper(arr, visited, output, depth + 1, r);
				visited[i] = false;
			}
		}
	}

	private static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
}