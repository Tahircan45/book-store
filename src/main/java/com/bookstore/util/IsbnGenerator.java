package com.bookstore.util;

public class IsbnGenerator implements NumberGenerator {

	@Override
	public String generateNumber() {
		int rnd=(int)(Math.random()*1000);
		int rnd1=(int)(Math.random()*1000);
		int rnd2=(int)(Math.random()*1000);
		return "13-"+rnd+"-"+rnd1+"-"+rnd2;
				
	}
}
