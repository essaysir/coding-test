# Two Sum

## 🔗 문제 URL
- https://leetcode.com/problems/two-sum/

## 🤔 내가 시도한 방식
- **1차: 이중 for문 (완전탐색) — O(N²)**
  - 모든 쌍 `(i, j)` (i < j) 을 다 더해보고 `target`이 되면 반환
  - 정답은 맞지만, 원소마다 나머지 원소를 전부 다시 훑어서 느림

```java
for (int i = 0; i < nums.length - 1; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[]{i, j};
        }
    }
}
```

- **2차: HashMap — O(N)** (최종 제출 → `Solution.java`)

## 💡 새로 알게 된 방식
- **O(N²) → O(N): "짝을 찾으러 다시 돌지 말고, 지나온 값을 기억해두자"**
  - 지금 값이 `nums[i]`면, 필요한 짝은 `target - nums[i]`
  - 지나온 값들을 `Map<값, 인덱스>`에 저장해두면 짝이 있는지 바로 확인 가능
  - HashMap 조회(`containsKey`, `get`)는 평균 O(1) → 배열을 한 번만 돌면 끝 → O(N)
- **순서가 중요: 먼저 확인하고, 그다음 저장**
  - `containsKey` 확인 → 없으면 `put`
  - 저장부터 하면 `nums[i]` 자기 자신을 짝으로 착각할 수 있음 (예: `target = 6`, `nums[i] = 3`)
- **핵심 아이디어: 시간 ↔ 공간 교환**
  - Map에 최대 N개를 저장하는 메모리(O(N))를 쓰는 대신 시간을 O(N²) → O(N)으로 줄임

## 📚 몰랐던 메서드
- **Map 반복 — `forEach`**

```java
Map<Integer, Integer> map = new HashMap<>();

// 1) key, value 둘 다 쓸 때 — 람다 파라미터 2개
map.forEach((key, value) -> System.out.println(key + " : " + value));

// 2) key만 쓸 때 — keySet()에 forEach
map.keySet().forEach(key -> System.out.println(key));

// 3) value만 쓸 때 — values()에 forEach
map.values().forEach(value -> System.out.println(value));
```

  - `map.forEach`는 람다가 항상 `(key, value)` 두 개를 받음 → 하나만 쓰고 싶으면 `keySet()` / `values()`로 먼저 꺼내서 `forEach`
  - 향상된 for문으로는 `for (Map.Entry<Integer, Integer> e : map.entrySet())` → `e.getKey()`, `e.getValue()`
- **`containsKey(key)`**: 그 key가 Map에 있는지 `true/false`
- **`get(key)`**: key에 해당하는 value 반환 (없으면 `null`)
