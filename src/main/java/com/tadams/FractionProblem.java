package com.tadams;

import com.tadams.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class FractionProblem {
	
	private List<String>	steps		= new ArrayList<>();

    Operation       operation;
    Fraction        fraction1;
    Fraction        fraction2;
	Fraction		answer;
	
	public FractionProblem(String equation) {
		
		operation = determineOperation(equation);
		String[] equParts = equation.split(operation.getRegExp());
		
		fraction1 = Fraction.valueOf(equParts[0]);
		fraction2 = Fraction.valueOf(equParts[1]);
		
		addStep(StepDesc.EQUATION, fraction1, fraction2);
		
		if (isMultiplyOrDivideOperation()) {
		
			if (isAnyMixedFractions()) {
				addStep(StepDesc.CONVT_MIX_FRAC, fraction1, fraction2);
			}
			
			Fraction f3 = new Fraction(fraction1);
			Fraction f4 = new Fraction(fraction2);
			if (simplify(fraction1, fraction2)) {
				addStep(StepDesc.SIMPLIFY, fraction1.toString(f3), fraction2.toString(f4));
			}
			
			if (operation == Operation.DIVIDE) {
				fraction2 = fraction2.reciprocal();
				operation = Operation.MULTIPLY;
				addStep(StepDesc.RECIP, fraction1, fraction2);
			}
			
		} else {
		
			int lcd = MathUtil.calcLeastCommonMultiplier(fraction1.getDenominator(), fraction2.getDenominator());
			if (fraction1.applyLeastCommonDenominator(lcd) | fraction2.applyLeastCommonDenominator(lcd)) {
				addStep(StepDesc.COMP_COMM_DENOM, fraction1, fraction2);
			}
			
		}
		
		int f1IntNum = fraction1.getWholeNumber();
		answer = doOperation(fraction1, fraction2, operation);
		if (fraction1.getWholeNumber() != f1IntNum) {
			addStep(StepDesc.BORROW, fraction1, fraction2);
		}
		
		
		addStep(StepDesc.getDesc(operation), answer.toString());
		
		if (answer.reduce()) {
			addStep(StepDesc.REDUCE, answer.toString());
		}
	}

    private boolean isAnyMixedFractions() {
        return fraction1.simplifyMixedFraction() | fraction2.simplifyMixedFraction();
    }

    private boolean isMultiplyOrDivideOperation() {
        return operation == Operation.MULTIPLY || operation == Operation.DIVIDE;
    }

    public Fraction getAnswer() {
		return answer;
	}
	
	private Fraction doOperation(Fraction f1, Fraction f2, Operation oper) {
		
		switch (oper) {
			case ADD : return f1.add(f2);
			case DIVIDE : return f1.divide(f2);
			case MULTIPLY : return f1.multiply(f2);
			case SUBTRACT: return f1.subtract(f2);
			default:
				throw new UnsupportedOperationException();
		}
	}
	
	private boolean simplify(Fraction f1, Fraction f2) {
		
		boolean factorApplied = false;
		int factor = MathUtil.calcGreatestCommonFactor(f1.getNumerator(), f2.getDenominator());
		if (factor != 1) {
			f1.applyFactorNumerator(factor);
			f2.applyFactorDenominator(factor);
			factorApplied = true;
		}
		factor = MathUtil.calcGreatestCommonFactor(f2.getNumerator(), f1.getDenominator());
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
	
	private void addStep(StepDesc desc, Object f1, Object f2) {
		steps.add(desc.getDesc() + 
			f1.toString() + " " + operation.getSymbol() + " " + f2.toString());
	}
	
	public List<String> getSteps() {
		return steps;
	}
	
	protected Operation determineOperation(String equation) {

		for (Operation operation : Operation.values()) {
			if (equation.contains(operation.getSymbol())) {
				return operation;
			}
		}
		throw new IllegalArgumentException("Can't find Operation");
	}
}
