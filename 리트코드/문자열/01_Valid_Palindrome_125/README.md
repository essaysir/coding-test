# Valid Palindrome

## 🔗 문제 URL
- https://leetcode.com/problems/valid-palindrome/

## 🤔 내가 시도한 방식
- **알파벳·숫자만 걸러서 새 문자열 만들기 → 양 끝에서 비교** (최종 제출 → `Solution.java`)
  - `StringBuilder`에 걸러낸 글자만 `append` → `i`와 `len - i - 1`을 비교
  - 시간 O(n), 추가 공간 O(n) (새 문자열을 만드니까)
- **처음 짠 코드의 문제 2개**

```java
if ( charArr[i].isLetterOrDigit()){   // ❌ 컴파일 에러
    sb.append(charArr[i]);            // ❌ 대소문자 처리 없음 → 오답
}
```

```java
if ( Character.isLetterOrDigit(charArr[i])){
    sb.append(Character.toLowerCase(charArr[i]));   // ✅
}
```

  - `char`는 기본형이라 `.`을 찍고 메서드를 부를 수 없음 → `Character` 클래스에 값을 넘겨야 함
  - `"A man, a plan, a canal: Panama"` → 거르기만 하면 `"AmanaplanacanalPanama"` → `'A'`(65) ≠ `'a'`(97) 라서 `false`

## 💡 새로 알게 된 방식
- **비교 전에 기준을 하나로 맞추기**
  - `append`할 때 전부 소문자로 바꿔서 넣으면 `'A'`와 `'a'`가 같은 글자가 됨
  - `toUpperCase`로 통일해도 정답. 하나로만 맞추면 됨
- **투 포인터로 건너뛰기 — 추가 공간 O(1)** (아직 안 짜봄)
  - 새 문자열을 만들지 않고, 양 끝의 `left`, `right`가 가운데로 다가오면서 특수문자를 만나면 그 칸만 건너뜀
  - 두 포인터가 따로 움직이니까 `for`보다 `while (left < right)`가 어울림
  - 비교할 때 `Character.toLowerCase()`로 맞춘 다음 비교

```
 left →           ← right
  "A  ,  b  a  !"
   ↑           ↑
  'A'         '!' ← 특수문자니까 right만 한 칸 이동
```

- **정규식 한 줄 버전**: `s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase()`
  - 짧지만 정규식 처리가 들어가서 직접 거르는 방식보다 느림

## 📚 몰랐던 메서드
- **`char`(기본형) vs `Character`(클래스)**
  - `char`는 값만 담는 상자라 메서드가 없음 → `Character.메서드(c)`처럼 클래스에 값을 넘김
  - `String`은 객체라서 `s.length()`처럼 점을 찍어 호출 가능
- **`Character.isLetterOrDigit(c)`**: 알파벳이나 숫자면 `true`. 공백 `' '`, 쉼표 같은 특수문자는 `false`
- **`Character.toLowerCase(c)`**: 소문자로 바꾼 `char` 반환. 숫자나 이미 소문자인 글자는 그대로 반환
  - 문자열 전체는 `s.toLowerCase()` (String의 메서드)
- **`toCharArray()`**: 문자열을 있는 그대로 한 글자씩 자름. 공백·쉼표·줄바꿈(`'\n'`)도 전부 `char` 하나로 들어감
  - `"a b".toCharArray()` → `['a', ' ', 'b']` (길이 3)
- **`StringBuilder.append(c)`**: 글자를 뒤에 하나씩 붙임
- **`StringBuilder.charAt(i)`**: `toString().toCharArray()`로 배열을 또 만들지 않고 바로 i번째 글자를 꺼낼 수 있음
