package port;

public class util {
	//TODO
	public static void fc_printf(String format, Object ... message){
	}
	public static String my_snprintf(String format, Object ... message){
		return String.format(format, message);
	}
	public static void freelog(int logError, String string, Object ...args)
	{
	}
	public static void die(String msg){
	}
	public static boolean isLetter(char ch){
		return Character.isLetter(ch);
	}
	public static boolean isLetter(String s){
		for(int i=0; i<=s.length(); i++){
			char ch = s.charAt(i);
			if(! Character.isLetter(ch)) return false;
		}
		return true;
	}
}
