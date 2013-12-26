package com.tadams;

public enum StepDesc {
	
	EQUATION("Equation: "),
	CONVT_MIX_FRAC("Convert mixed number to fraction: "),
    COMP_COMM_DENOM("Compute common denominator: "),
    ADD_FRAC("Add fractions: "),
    SUB_FRAC("Subtract fractions: "),
    MULTI_FRAC("Multiply fractions: "),
    RECIP("Calculate reciprocal, change operation to multiply: "),
    SIMPLIFY("Simplify fractions: "),
    BORROW("Borrow: "),
    REDUCE("Reduce answer: ");
    
    private String desc;
    
    private StepDesc(String desc) {
		this.desc = desc;
	}
    
    public String getDesc() {
    	return desc;
    }
    
    public static StepDesc getDesc(Operation oper) {
    	switch (oper) {
    		case ADD : return ADD_FRAC;
    		case SUBTRACT: return SUB_FRAC;
    		case MULTIPLY : return MULTI_FRAC;
    	}
    	throw new AssertionError();
    }

}
