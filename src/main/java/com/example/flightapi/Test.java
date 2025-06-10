package com.example.flightapi;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String letter = "AAA";
		int sum = 0;
//		"A".charAt(0);
//		(int)("A".charAt(0))
		
		for(int i = 0;i<letter.length();i++) {
//			System.out.println(((int)letter.charAt(i)-64)) ;
//			if() {
			sum  = sum * 26  + (int)letter.charAt(i)-64;
			System.out.println(sum);
		}
//		System.out.println((int)("AA"));

	}

}
