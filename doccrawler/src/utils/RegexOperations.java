package utils;

public class RegexOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(replaceQuotesForSql("asd'dasd''"));

	}

	public static String replaceQuotesForSql (String str){
		return str.replace("'", "\\'");
	}
}
