package com.tadams;

import com.tadams.util.Utils;

import static com.tadams.util.Utils.firstNonNull;

public enum Operation {
	ADD("+ ",true), SUBTRACT("- ",false), MULTIPLY("* ",true), DIVIDE("/ ",false);
	
	private String		symbol;
	private String		regExp;

	private Operation(String symbol, boolean escRegExp) {
		this.symbol = symbol;
		if (escRegExp) {
			regExp = "\\" + symbol;
		}
	}

	public String getRegExp() {
        return firstNonNull(regExp, symbol);
	}
	
	public String getSymbol() {
		return symbol;
	}
}
