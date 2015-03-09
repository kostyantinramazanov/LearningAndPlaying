import java.util.HashMap;
import java.util.Map;

public class ReverseString {

	public static void main(String[] argv) {
		String str = "Hello beautifull world !";

		// System.out.println(new String(loopReverse(str)));

		// System.out.println(new String(recursiveReverse(str)));

		// System.out.println(new String(invertWords(str)));

		char cstr[] = "He l loolHe llo".toCharArray();
		
//		System.out.println(searchFirstUniqueChar(cstr));
		
		System.out.println(searchLongestSequence(new String(cstr)));
	}

	private static String searchLongestSequence(String str) {
		int longestStart=0;
		int longestEnd=0;
		int currentStart=0;
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == ' ' || i == str.length()-1){
				if((i-currentStart) > (longestEnd - longestStart)){
					longestStart = currentStart;
					longestEnd = i;
				}
				currentStart=i+1;
			}
		}
		return str.substring(longestStart, longestEnd);
	}

	private static char searchFirstUniqueChar(char[] cstr) {
		int[] visited = new int[(int) 'z' - (int) 'A' + 1];
		char[] uniq = new char[(int) 'z' - (int) 'A' + 1];
		int uniqIndex = 0;
		int resultIndex = 0;
		for (char c : cstr) {
			int charIndex = (int) c - (int) 'A';
			visited[charIndex]++;
			if (visited[charIndex] == 1) {
				uniq[uniqIndex] = c;
				uniqIndex++;
			} else {
				if (visited[charIndex] == 2) {
					resultIndex++;
				}
			}
		}

		return uniq[resultIndex];
	}

	private static char[] invertWords(String str) {
		char[] stri = loopReverse(str);
		System.out.println(new String(stri));
		int strLen = str.length();
		int firstLetter = 0;
		int position = 0;
		while (position <= strLen) {
			if (position == strLen || stri[position] == ' ') {
				for (int i = 0; i < (position - firstLetter) / 2; i++) {
					stri[firstLetter + i] ^= stri[position - i - 1];
					stri[position - i - 1] ^= stri[firstLetter + i];
					stri[firstLetter + i] ^= stri[position - i - 1];
				}
				firstLetter = position + 1;
			}
			position++;
		}

		return stri;

	}

	private static char[] recursiveReverse(String str) {
		int strlen = str.length();
		char[] strc = str.toCharArray();

		reverse(strc, 0, strlen - 1);
		return strc;
	}

	private static void reverse(char[] strc, int i, int j) {
		if (i >= j)
			return;
		strc[i] = strc[j];
		reverse(strc, i + 1, j - 1);
		strc[j] = strc[i];

		return;
	}

	private static char[] loopReverse(String str) {
		int strlen = str.length();
		char[] strc = str.toCharArray();

		for (int i = 0; i < strlen / 2; i++) {
			strc[i] = (char) ((int) strc[strlen - i - 1] ^ (int) strc[i]);
			strc[strlen - i - 1] = (char) ((int) strc[strlen - i - 1] ^ (int) strc[i]);
			strc[i] = (char) ((int) strc[strlen - i - 1] ^ (int) strc[i]);
		}
		return strc;
	}

}
