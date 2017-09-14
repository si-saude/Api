package br.com.saude.api.generic;

public class Helper {
	private static StringBuilder stringBuilder;
	
	public static String filterLike(String str) {
		stringBuilder = new StringBuilder("%");
		stringBuilder.append(str);
		stringBuilder.append("%");
		return stringBuilder.toString();
	}
}
