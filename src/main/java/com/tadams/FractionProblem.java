package com.tadams;

import com.tadams.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

import static com.tadams.util.HtmlFractionFormatter.format;
import static com.tadams.util.MathUtil.calcLeastCommonMultiplier;

public class FractionProblem {
	
	private List<String>	steps		= new ArrayList<>();

    Operation       operation;
    Fraction        fraction1;
    Fraction        fraction2;
	Fraction		answer;
	
	public FractionProblem(String equation) {

        parseEquation(equation);
		addStep(StepDesc.EQUATION, format(fraction1), format(fraction2));
		
		if (operation.isMultiplyOrDivideOperation()) {

            doMultiplyDividePreSteps();

		} else {

            doAddSubtractPreSteps();
		}

        calculateAnswer();
        reduceAnswer();
    }

    private void parseEquation(String equation) {

        operation = determineOperation(equation);
        String[] equationParts = equation.split(operation.getRegExp());

        fraction1 = Fraction.valueOf(equationParts[0]);
        fraction2 = Fraction.valueOf(equationParts[1]);
    }

    private void doMultiplyDividePreSteps() {
        convertToImproperFractions();

        doDividePreSteps();

        simplifyFractions(fraction1, fraction2);
    }

    private void doAddSubtractPreSteps() {
        int lcd = calcLeastCommonMultiplier(fraction1.getDenominator(), fraction2.getDenominator());

        boolean f1LcdApplied = fraction1.applyLeastCommonDenominator(lcd);
        boolean f2LcdApplied = fraction2.applyLeastCommonDenominator(lcd);

        if (f1LcdApplied || f2LcdApplied) {
            addStep(StepDesc.COMP_COMM_DENOM, format(fraction1), format(fraction2));
        }
    }

    private void calculateAnswer() {
        int f1WholeNumber = fraction1.getWholeNumber();

        answer = doOperation(fraction1, fraction2, operation);

        if (fraction1.getWholeNumber() != f1WholeNumber) {
            addStep(StepDesc.BORROW, format(fraction1), format(fraction2));
        }

        addStep(StepDesc.getDesc(operation), format(answer));
    }

    private void reduceAnswer() {
        if (answer.reduce()) {
            addStep(StepDesc.REDUCE, format(answer));
        }
    }

    private void doDividePreSteps() {
        if (operation == Operation.DIVIDE) {
            fraction2 = fraction2.reciprocal();
            operation = Operation.MULTIPLY;
            addStep(StepDesc.RECIP, format(fraction1), format(fraction2));
        }
    }

    private void convertToImproperFractions() {
        boolean f1Converted = fraction1.simplifyMixedFraction();
        boolean f2Converted = fraction2.simplifyMixedFraction();

        if (f1Converted || f2Converted) {
            addStep(StepDesc.CONVT_MIX_FRAC, format(fraction1), format(fraction2));
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
			case SUBTRACT: return f1.subtract(f2);
			default:
				throw new UnsupportedOperationException();
		}
	}
	
	private void simplifyFractions(Fraction f1, Fraction f2) {

        Fraction origFraction1 = new Fraction(fraction1);
        Fraction origFraction2 = new Fraction(fraction2);
		
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

        if (factorApplied) {
            addStep(StepDesc.SIMPLIFY, format(fraction1, origFraction1),
                                       format(origFraction2, fraction2));
        }
	}
	
	private void addStep(StepDesc desc, String s2) {
		steps.add(desc.getDesc() + s2);
	}
	
	private void addStep(StepDesc desc, String f1, String f2) {
		steps.add(desc.getDesc() + 
			      f1 + " " +
                  operation.getSymbol() + " " +
                  f2);
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
