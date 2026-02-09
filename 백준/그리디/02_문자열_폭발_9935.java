import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String str = br.readLine();
			String str2 = br.readLine();

			System.out.println(solution(str,str2));
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static String solution(String str,String str2){
		// str 은 기존 문자열  1 보다 크거나 100만 보다 작거나 같다.
		// str2 는 폭파 시킬 문자열

		int bombLen = str2.length();
		// str2 len 이 2 이면  4 길이에서 3 까지만

		char[] char_arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();

		for ( int i =0; i < str.length(); i++ ){
			sb.append(str.charAt(i));

			if ( sb.length() >= bombLen){
				if (sb.substring(sb.length() - bombLen).equals(str2)){
					sb.delete(sb.length() -  bombLen, sb.length());
				}
			}
		}

		return sb.length() == 0 ? "FRULA" :  sb.toString();
	}



}