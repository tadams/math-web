package com.tadams;

public enum Operation {
	ADD("+ ",true), SUBSTRACT("- ",false), MULTIPLY("* ",true), DIVIDE("/ ",false);
	
	private String		symbol;
	private String		regExp;
	
	private Operation(String symbol, boolean escRegExp) {
		this.symbol = symbol;
		if (escRegExp) {
			regExp = "\\" + symbol;
		}
	}
	
	public boolean equals(String str) {
		return symbol.equals(str);
	}
	
	public String getRegExp() {
		if (regExp == null) {
			return symbol;
		}
		return regExp;
	}
	
	public String getSymbol() {
		return symbol;
	}
}
