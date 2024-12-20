/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(isAnagram("aabbcc", "bbaacc"));

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		
		String str1lower = preProcess(str1);
		String str2lower = preProcess(str2);
		str1lower = nospace(str1lower);
		str2lower = nospace(str2lower);
		if (str1lower.length() != str2lower.length()){
			return false;
		}
		String marked2 = str2lower;
		for (int i = 0; i< str1lower.length(); i++){ //iterate over 1st string
			boolean charMatch = false; //bool for character match
			for (int j = 0; j < marked2.length(); j++){ // iterate over 2nd string
				if(str1lower.charAt(i)==marked2.charAt(j)){ //if characters match, it removes it from the 2nd
					marked2 = marked2.substring(0,j) + '*' + marked2.substring(j+1);
					charMatch = true;
					break;
				}
			}
			if (!charMatch){ // return false of not all characters matched
				return false;
			}
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String lower = str.toLowerCase();
		int length = str.length();
		String proccesed = "";
		for (int i = 0; i<length; i++){
			char c = lower.charAt(i);
			if ((c >= 'a' && c <='z') || c == ' '){
				proccesed = proccesed + c;
			}
		}
		return (proccesed);
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		int length = str.length();
		String anagram = "";
		for (int i=0; i<length; i++){
			int randomIndex = (int)(Math.random()*str.length());
			char rnd = str.charAt(randomIndex);
			anagram = anagram +rnd;
			str = str.substring(0, randomIndex) + str.substring(randomIndex +1);
		}
		return anagram;
	}
	// since ' ' counts as a char, i need a function remove the whitespaces, to then compare strings
	public static String nospace(String str) { 
		String lower = str.toLowerCase();
		int length = str.length();
		String proccesed = "";
		for (int i = 0; i<length; i++){
			char c = lower.charAt(i);
			if ((c >= 'a' && c <='z')){
				proccesed = proccesed + c;
			}
		}
		return (proccesed);
	} 
}
