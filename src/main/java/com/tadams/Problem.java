package com.tadams;

import java.util.ArrayList;
import java.util.List;

public class Problem {
	
	private List<String>	steps		= new ArrayList<String>();
	
	private Fraction		answer;
	
	public Problem(String equation) {
		
		Operation operation = calcOper(equation);
		String[] equParts = equation.split(operation.getRegExp());
		
		Fraction f1 = Fraction.valueOf(equParts[0]);
		Fraction f2 = Fraction.valueOf(equParts[1]);
		
		addStep(StepDesc.EQUATION, f1, f2, operation);
		
		if (operation == Operation.MULTIPLY || operation == Operation.DIVIDE) {
		
			if (f1.improperFraction() | f2.improperFraction()) {
				addStep(StepDesc.CONVT_MIX_FRAC, f1, f2, operation);
			}
			
			Fraction f3 = new Fraction(f1);
			Fraction f4 = new Fraction(f2);
			if (simplify(f1, f2)) {
				addStep(StepDesc.SIMPLIFY, f1.toString(f3), f2.toString(f4), operation);
			}
			
			if (operation == Operation.DIVIDE) {
				f2 = f2.reciprocal();
				operation = Operation.MULTIPLY;
				addStep(StepDesc.RECIP, f1, f2, operation);
			}
			
		} else {
		
			int lcd = MathUtil.leastComMulti(f1.getDenominator(), f2.getDenominator());
			if (f1.applyLeastComDenom(lcd) | f2.applyLeastComDenom(lcd)) {
				addStep(StepDesc.COMP_COMM_DENOM, f1, f2, operation);
			}
			
		}
		
		int f1IntNum = f1.getIntNum();
		answer = doOperation(f1, f2, operation);
		if (f1.getIntNum() != f1IntNum) {
			addStep(StepDesc.BORROW, f1, f2, operation);
		}
		
		
		addStep(StepDesc.getDesc(operation), answer.toString());
		
		if (answer.reduce()) {
			addStep(StepDesc.REDUCE, answer.toString());
		}
	}
	
	public Fraction getAnswer() {
		return answer;
	}
	
	private Fraction doOperation(Fraction f1, Fraction f2, Operation oper) {
		
		switch (oper) {
			case ADD : return f1.add(f2);
			case DIVIDE : return f1.divide(f2);
			case MULTIPLY : return f1.multiply(f2);
			case SUBSTRACT : return f1.subtract(f2);
			default:
				throw new UnsupportedOperationException();
		}
	}
	
	private boolean simplify(Fraction f1, Fraction f2) {
		
		boolean factorApplied = false;
		int factor = MathUtil.calcGCF(f1.getNumerator(), f2.getDenominator());
		if (factor != 1) {
			f1.applyFactorNumerator(factor);
			f2.applyFactorDenominator(factor);
			factorApplied = true;
		}
		factor = MathUtil.calcGCF(f2.getNumerator(), f1.getDenominator());
		if (factor != 1) {
			f2.applyFactorNumerator(factor);
			f1.applyFactorDenominator(factor);
			factorApplied = true;
		}
		
		return factorApplied;
	}
	
	private void addStep(StepDesc desc, String s2) {
		steps.add(desc.getDesc() + s2);
	}
	
	private void addStep(StepDesc desc, Object f1, Object f2, Operation oper) {
		steps.add(desc.getDesc() + 
			f1.toString() + " " + oper.getSymbol() + " " + f2.toString());
	}
	
	public List<String> getSteps() {
		return steps;
	}
	
	private Operation calcOper(String equation) {
		for (Operation oper : Operation.values()) {
			if (equation.contains(oper.getSymbol())) {
				return oper;
			}
		}
		throw new IllegalArgumentException("Can't find Operation");
	}
}
