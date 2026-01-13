import java.util.*;

class Solution {
	// https://school.programmers.co.kr/learn/courses/30/lessons/72411
	public String[] solution(String[] orders, int[] course) {
		List<String> answer = new ArrayList<>();

		Set<Character> menus = new TreeSet<>();  // TreeSet으로 정렬 보장

		for (String order : orders) {
			for (char c : order.toCharArray()) {
				menus.add(c);
			}
		}

		List<Character> menuList = new ArrayList<>(menus);

		for (int courseCount : course) {
			List<String> possibleCourses = combination(courseCount, menuList);

			// 각 조합별 주문 횟수 계산
			Map<String, Integer> countMap = new HashMap<>();
			int maxCount = 0;

			for (String possible : possibleCourses) {
				int count = 0;
				for (String order : orders) {
					if (containsAll(order, possible)) {
						count++;
					}
				}
				if (count >= 2) {
					countMap.put(possible, count);
					maxCount = Math.max(maxCount, count);
				}
			}

			// 최대 횟수인 것만 추가
			for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
				if (entry.getValue() == maxCount) {
					answer.add(entry.getKey());
				}
			}
		}

		Collections.sort(answer);
		return answer.toArray(new String[0]);
	}

	private boolean containsAll(String order, String combo) {
		for (char c : combo.toCharArray()) {
			if (order.indexOf(c) == -1) return false;
		}
		return true;
	}

	private List<String> combination(int R, List<Character> menus) {
		List<String> answer = new ArrayList<>();
		combine(menus, 0, R, "", answer);
		return answer;
	}

	private void combine(List<Character> menus, int start, int R, String current, List<String> answer) {
		if (R == 0) {
			answer.add(current);
			return;
		}
		for (int i = start; i < menus.size(); i++) {
			combine(menus, i + 1, R - 1, current + menus.get(i), answer);
		}
	}
}