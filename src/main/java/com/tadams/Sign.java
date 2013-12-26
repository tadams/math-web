package com.tadams;

public enum Sign {
	POS,NEG;
	
	public Sign apply(Operation oper) {
		if (oper == Operation.SUBSTRACT) {
			return opposite();
		}
		return this;
	}
	
	public Sign opposite() {
		switch(this) {
			case POS : return NEG;
			case NEG : return POS;
		}
		throw new AssertionError();
	}
}
