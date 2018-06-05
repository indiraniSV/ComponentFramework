package Community;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
public class Characters {
//Return UPPER, Lower and Number only
public static String alphaNumeric(int len)
{
	int length = len;
	String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	             + "abcdefghijklmnopqrstuvwxyz"
	             + "0123456789";
	String str = new Random().ints(length, 0, chars.length())
	                         .mapToObj(i -> "" + chars.charAt(i))
	                         .collect(Collectors.joining());
	System.out.println(str);
	return str;
}
//Return UPPER, Lower, Number and Special letter only
public static String UpperLowerNumbericSpecial(int len)
{
	int length = len;
	String digits = "0123456789";
	String specials = "~=+%^*/()[]{}/!@#$?|";
	String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	           + "abcdefghijklmnopqrstuvwxyz"
	           + digits + specials;
	Random rnd = new Random();
	List<String> result = new ArrayList<>();
	Consumer<String> appendChar = s ->result.add("" + s.charAt(rnd.nextInt(s.length())));
	appendChar.accept(digits);
	appendChar.accept(specials);
	while (result.size() < length)
	appendChar.accept(all);
	Collections.shuffle(result, rnd);
	String str = String.join("", result);
	System.out.println(str);
	return str;
}
//Return lower letter only
public static String lowerCharAlone(int len)
{
	int length = len;
	String chars = "abcdefghijklmnopqrstuvwxyz";
	String str = new Random().ints(length, 0, chars.length())
	                         .mapToObj(i -> "" + chars.charAt(i))
	                         .collect(Collectors.joining());
	System.out.println(str);
	return str;
}
//Return UPPER LETTER only
public static String UpperCharAlone(int len)
{
	int length = len;
	String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String str = new Random().ints(length, 0, chars.length())
	                         .mapToObj(i -> "" + chars.charAt(i))
	                         .collect(Collectors.joining());
	System.out.println(str);
	return str;
}
//Return Number only
public static String NumberAlone(int len)
{
	int length = len;
	String digits = "0123456789";
	String all = digits;
	Random rnd = new Random();
	List<String> result = new ArrayList<>();
	Consumer<String> appendChar = s ->result.add("" + s.charAt(rnd.nextInt(s.length())));
	appendChar.accept(digits);
	while (result.size() < length)
	    appendChar.accept(all);
	Collections.shuffle(result, rnd);
	String str = String.join("", result);
		System.out.println(str);
		return str;
	}
	
}
