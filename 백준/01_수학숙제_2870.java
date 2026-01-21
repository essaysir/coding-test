import java.util.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		String[] strings = new String[]{
			"lo3za4",
			"01"
		};

		List<BigInteger> answer = solution(strings);
		for (BigInteger s : answer) System.out.println(s);
	}

	private static List<BigInteger> solution(String[] strings) {
		List<BigInteger> answer = new ArrayList<>();

		for (String current : strings) {
			char[] characters = current.toCharArray();
			StringBuilder sb = new StringBuilder();

			for (char c : characters) {
				if (Character.isDigit(c)) {
					sb.append(c);
				} else {
					if (sb.length() > 0) {
						answer.add(new BigInteger(sb.toString()));
						sb = new StringBuilder();
					}
				}
			}
			// 문자열 끝 처리
			if (sb.length() > 0) {
				answer.add(new BigInteger(sb.toString()));
			}
		}

		Collections.sort(answer);
		return answer;
	}
}