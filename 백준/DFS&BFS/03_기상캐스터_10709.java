import java.util.*;

public class Main {
	public static void main(String[] args) {
		// y = 3 , x = 4
		int y = 3, x = 4;
		Character[][] map = {
			{'c','.','.','c'},
			{'.','.','c','.'},
			{'.','.','.','.'}
		};
		System.out.println(Arrays.deepToString(solution(y,x,map)));
	}

	public static int[][] solution(int Y,int X,Character[][] map){
		int[][] answer = new int[Y][X];

		for (int i = 0; i < Y; i++) {
			Arrays.fill(answer[i], -1);
		}

		System.out.println(Arrays.deepToString(map));
		for ( int y = 0; y < Y; y++ ){
			for ( int x = 0; x < X; x++ ){
				System.out.println(y + " " + x);
				if ( map[y][x] == 'c' ){
					int count = 0;

					for ( int dx = x; dx < X; dx++){
						int current = answer[y][dx];
						if ( current == -1 || count < current){
							answer[y][dx] = count++;
						}
					}
				}
			}
		}



		return answer;
	}
}